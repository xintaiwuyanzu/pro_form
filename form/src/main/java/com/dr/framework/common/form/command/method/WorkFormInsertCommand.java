package com.dr.framework.common.form.command.method;

import com.dr.framework.common.form.command.entity.FormField;
import com.dr.framework.common.form.command.entity.WorkForm;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.model.Form;
import com.dr.framework.common.form.util.Constans;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.jdbc.Column;
import com.dr.framework.core.orm.jdbc.TrueOrFalse;
import com.dr.framework.core.orm.module.ConfigedRelation;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorkFormInsertCommand implements Command<Form> {

    private WorkForm formData;
    private List<FormField> formFieldList;
    private boolean generate;

    public WorkFormInsertCommand(WorkForm formData, List<FormField> formFieldList, boolean generate) {
        this.formData = formData;
        this.formFieldList = formFieldList;
        this.generate = generate;
    }

    /**
     * 1、保存表单数据信息，
     * 2、保存表单中各字段的数据信息，(这里的字段根据 表单的大小 决定字段的多少)
     *
     * @param context
     * @return
     */
    @Override
    public Form execute(CommandContext context) {
        Assert.notNull(formData, "表单数据不能为空！");
        if (StringUtils.isEmpty(formData.getId())) {
            formData.setId(UUID.randomUUID().toString());
        }
        //再遍历表单中的字段，将formId插入到字段表中
        if (formFieldList.size() > 0) {
            List<FormField> list = new ArrayList<FormField>();
            for (FormField formField : formFieldList) {
                formField.setFormId(formData.getId());
                if (StringUtils.isEmpty(formField.getId())) {
                    formField.setId(UUID.randomUUID().toString());
                }
                //保存这张表单中各字段的信息
                context.getMapper().insert(formField);
                list.add(formField);
            }
            //再保存表单的数据
            context.getMapper().insert(formData);
            //将字段信息和表单数据返回；
            formData.setFormFieldList(list);
        }
        //是否生成数据库表 true:生成； false:不生成
        if (generate) {
            createTable(context, formData, generate);
        }
        return formData;
    }

    protected void createTable(CommandContext context, WorkForm formData, boolean generate) {
        //是否生成表
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        ConfigedRelation configedRelation = new ConfigedRelation(generate);
        configedRelation.setId(formData.getId());
        configedRelation.addPrimaryKey("pk", "id", 0);
        //TODO 修改获取表名，为按规则生成！
        configedRelation.setName(formData.getFormTable());
        configedRelation.setModule(Constans.MODULE_NAME);
        List<FormField> list = formData.getFormFieldList();
        for (FormField formField : list) {
            Column column = new Column(formData.getFormTable(), formField.getFieldCode(), "");
            column.setName(formField.getFieldCode());
            column.setTableName(formData.getFormTable());
            column.setType(Types.VARCHAR);
            column.setSize(formField.getFieldLength());
            column.setNullAble(TrueOrFalse.FALSE);
            configedRelation.addColumn(column);
        }
        dataBaseService.updateTable(configedRelation);
    }

}
