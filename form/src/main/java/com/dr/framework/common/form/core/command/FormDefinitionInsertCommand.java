package com.dr.framework.common.form.core.command;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormDefinitionInfo;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * 创建表单定义
 *
 * @author dr
 * @author lich
 */
public class FormDefinitionInsertCommand extends AbstractFormDefinitionCommand implements Command<FormDefinition> {
    /**
     * 表单定义
     */
    private final FormModel formModel;
    /**
     * 表字段定义
     */
    private final Collection<FieldModel> fieldModels;
    /**
     * 是否生成表结构
     */
    private final boolean genTable;

    /**
     * 创建表单
     *
     * @param formModel     表单对象
     * @param fieldModels   表单字段
     * @param genTable 是否创建表结构
     */
    public FormDefinitionInsertCommand(FormModel formModel, Collection<FieldModel> fieldModels, boolean genTable) {
        this.formModel = formModel;
        this.fieldModels = fieldModels;
        this.genTable = genTable;
    }

    /**
     * 1、保存表单数据信息，
     * 2、保存表单中各字段的数据信息，(这里的字段根据 表单的大小 决定字段的多少)
     *
     * @param context
     * @return
     */
    @Override
    public FormDefinition execute(CommandContext context) {
        Assert.isTrue(fieldModels != null && !fieldModels.isEmpty(), "表单字段不能为空！");

        //根据参数创建FormDefinition对象
        FormDefinition fd = newFormDefinition(context, formModel);
        fd.setDefault(true);

        //填充默认信息
        fillFormDefinition(fd);

        //code不能为空
        if (StringUtils.isEmpty(fd.getFormCode())) {
            fd.setFormCode(com.dr.framework.common.form.util.StringUtils.generateShortUuid());
        } else {
            Assert.isTrue(!existByCodeAndVersion(context, fd.getFormCode(), null), "指定的表单编码已存在！");
        }

        CommonMapper mapper = context.getMapper();
        //table不能为空
        if (!StringUtils.isEmpty(fd.getFormTable())) {
            Assert.isTrue(!mapper.existsByQuery(
                    SqlQuery.from(FormDefinition.class)
                            .equal(FormDefinitionInfo.FORMTABLE, fd.getFormTable())
                            .notEqual(FormDefinitionInfo.FORMCODE, fd.getFormCode())
            ), "表名已存在" + fd.getFormTable());
        } else {
            if (mapper.existsByQuery(
                    SqlQuery.from(FormDefinition.class).equal(FormDefinitionInfo.FORMTABLE, fd.getFormCode())
            )) {
                fd.setFormTable(com.dr.framework.common.form.util.StringUtils.generateShortUuid());
            } else {
                fd.setFormTable(fd.getFormCode());
            }
        }

        //校验字段基本信息
        fd.setFields(new ArrayList<>());
        fieldModels.forEach(f -> newField(fd, f));
        //排序
        Collections.sort(fd.getFields());

        saveFormDefinition(context, fd);

        //是否生成数据库表 true:生成； false:不生成
        if (genTable) {
            createTable(context, fd);
        }
        return fd;
    }

    private FormField newField(FormDefinition formDefinition, FieldModel fieldModel) {
        FormField formField = newField(fieldModel);
        formField.setFormDefinitionId(formDefinition.getId());

        if (StringUtils.isEmpty(fieldModel.getFieldCode())) {
            formField.setFieldCode(com.dr.framework.common.form.util.StringUtils.generateShortUuid());
        }
        validateFieldBaseInfo(formDefinition, fieldModel);
        formDefinition.getFields().add(formField);
        return formField;
    }

    public FormModel getForm() {
        return formModel;
    }

    public Collection<FieldModel> getFields() {
        return fieldModels;
    }

    public boolean isGenTable() {
        return genTable;
    }
}
