package com.dr.framework.common.form.core.command;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.util.CacheUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 添加字段定义
 *
 * @author dr
 */
public class FormDefinitionFieldAddCommand extends AbstractFormDefinitionChangeCommand implements Command<Collection<FormField>> {

    public FormDefinitionFieldAddCommand(String formDefinitionId, boolean updateTable, boolean copyData, FieldModel... fieldModel) {
        super(formDefinitionId, updateTable, copyData, fieldModel);
    }

    public FormDefinitionFieldAddCommand(String formCode, Integer version, boolean updateTable, boolean copyData, FieldModel... fieldModel) {
        super(formCode, version, updateTable, copyData, fieldModel);
    }

    /**
     * 执行业务操作但是不更新版本号
     *
     * @param context
     * @param old
     * @return
     */
    @Override
    protected List<FormField> executeWithOutUpdateVersion(CommandContext context, FormDefinition old) {
        //复制新的表单对象，这个表单只是临时使用的，万一校验失败会把原来的表单定义污染掉
        FormDefinition newFormDefinition = copyFormDefinition(context, old);

        List<FormField> formFieldList = new ArrayList<>();
        for (FieldModel fieldModel : getField()) {
            //转换添加的字段
            FormField formField = newField(fieldModel);
            //TODO 判断新增加表单是否存在旧的表单中
            //校验字段定义格式正确
            validateFieldBaseInfo(newFormDefinition, fieldModel);
            newFormDefinition.getFields().add(formField);
            formFieldList.add(formField);
        }

        //直接插入数据
        for (FormField formField : formFieldList) {
            CommonMapper mapper = context.getMapper();
            formField.setVersion(newFormDefinition.getVersion());
            formField.setFormDefinitionId(newFormDefinition.getId());
            mapper.insert(formField);
        }
        //数据库保存成功之后再保存到内存中
        old.getFields().addAll(formFieldList);
        //插入成功之后删除缓存
        CacheUtil.removeFormDefinitionCache(context, old.getId());
        //更新表结构
        if (isUpdateTable()) {
            //创建表结构
            createTable(context, old);
        }
        return formFieldList;
    }

    /**
     * 执行业务操作并且更新版本号
     *
     * @param context
     * @param old
     * @return
     */
    @Override
    protected List<FormField> executeWithUpdateVersion(CommandContext context, FormDefinition old) {
        //复制新的表单对象
        FormDefinition newFormDefinition = copyFormDefinition(context, old);
        newFormDefinition.setDefault(true);
        List<FormField> formFieldList = new ArrayList<>();
        //转换添加的字段
        for (FieldModel fieldModel : getField()) {
            //转换添加的字段
            FormField formField = newField(fieldModel);
            //校验字段定义格式正确
            validateFieldBaseInfo(newFormDefinition, fieldModel);

            newFormDefinition.getFields().add(formField);

            formFieldList.add(formField);
        }

        //保存字段定义到数据库
        saveFormDefinition(context, newFormDefinition);
        //更新表结构
        if (isUpdateTable()) {
            //创建表结构
            createTable(context, newFormDefinition);
            if (isCopyData()) {
                copyTable(context, old, newFormDefinition);
            }
        }
        return formFieldList;
    }

}
