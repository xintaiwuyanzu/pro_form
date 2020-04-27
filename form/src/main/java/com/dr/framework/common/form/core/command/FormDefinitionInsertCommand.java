package com.dr.framework.common.form.core.command;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormDefinitionInfo;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.plugin.CreateWorkFormPlugin;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.jdbc.Column;
import com.dr.framework.core.orm.jdbc.Relation;
import com.dr.framework.core.orm.jdbc.TrueOrFalse;
import com.dr.framework.core.orm.module.ConfigedRelation;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.sql.Types;
import java.util.*;

/**
 * 创建表单定义
 *
 * @author dr
 * @author lich
 */
public class FormDefinitionInsertCommand implements Command<Form> {
    /**
     * 表单定义
     */
    private Form formData;
    /**
     * 表字段定义
     */
    private Collection<Field> formFieldList;
    /**
     * 是否生成表结构
     */
    private boolean generate;
    /**
     * 是否复制数据
     */
    private boolean copyData = true;

    public FormDefinitionInsertCommand(Form formData, Collection<Field> formFieldList, boolean generate, boolean copyData) {
        this.formData = formData;
        this.formFieldList = formFieldList;
        this.generate = generate;
        this.copyData = copyData;
    }

    public FormDefinitionInsertCommand(Form formData, Collection<Field> formFieldList, boolean generate) {
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
        //根据参数创建workform对象
        FormDefinition formDefinition = getWorkForm(context);
        //根据参数获取fieldList参数
        Collection<FormField> fields = getFormFields(formDefinition);
        //保存定义数据
        CommonMapper mapper = context.getMapper();
        for (FormField formField : fields) {
            //先保存字段数据
            mapper.insert(formField);
        }
        //保存定义数据
        mapper.insert(formDefinition);
        //是否生成数据库表 true:生成； false:不生成
        if (generate) {
            formDefinition.setFormFieldList(fields);
            createTable(context, formDefinition);
        }
        //如果需要复制数据 并且是更新表定义，则执行数据复制
        if (copyData && formDefinition.getId().equalsIgnoreCase(formData.getId())) {
            copyData(context, formData, formDefinition);
        }
        return formDefinition;
    }

    /**
     * 从老表复制数据到新表
     *
     * @param formData 老表结构定义
     * @param formDefinition 新表结构定义
     */
    protected void copyData(CommandContext context, Form formData, FormDefinition formDefinition) {
        //先根据旧的表定义确定旧表单的数据库是那张表
        String tableName = formData.getFormTable();
        //查出来旧表结构定义对象
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        Relation relation = dataBaseService.getTableInfo(tableName, Constants.MODULE_NAME);
        //获取这张表内的所有数据
        SqlQuery sqlQueryObj = SqlQuery.from(relation).equal(relation.getColumn("formId"), formData.getId());
        List<Object> list = context.getMapper().selectByQuery(sqlQueryObj);
        //将所有的数据，全部复制到新的数据库表中
        if (list.size() > 0) {
            for (Object obj : list) {
                //todo 将一条条旧数据插入到新生成的数据库表中
            }
        }
    }

    /**
     * 根据新的workform和传进来的全局formFieldList创建新的表单字段定义信息
     *
     * @param formDefinition
     * @return
     */
    protected Collection<FormField> getFormFields(FormDefinition formDefinition) {
        Collection<FormField> fields = new ArrayList<>();
        if (formFieldList.size() > 0) {
            for (Field field : formFieldList) {
                FormField formField = new FormField();
                formField.setDataObjectId(field.getDataObjectId());
                formField.setDescription(field.getDescription());
                formField.setFieldCode(field.getFieldCode());
                formField.setFieldLength(field.getFieldLength());
                formField.setFieldName(field.getFieldName());
                formField.setFieldOrder(field.getFieldOrder());
                formField.setFieldState(field.getFieldState());
                formField.setFieldType(field.getFieldType());
                formField.setFieldValue(field.getFieldValue() + "");
                formField.setFormDefinitionId(formDefinition.getId());
                formField.setHistoryVersion(field.historyVersion());
                formField.setVersion(field.getVersion());
                formField.setCreateDate(System.currentTimeMillis());
                formField.setId(UUID.randomUUID().toString());
                fields.add(formField);
            }
        }
        return fields;
    }

    protected FormDefinition getWorkForm(CommandContext context) {
        FormDefinition formDefinition = new FormDefinition();
        //1、根据 formData全局变量id判断：如果有id，则查询表单定义，能查询到说明之前定义过，新的表单定义需要更改表单版本号
        if (StringUtils.isNotEmpty(formData.getId())) {
            //todo 进行数据比对， 没更新则不处理表定义

            formDefinition = context.getMapper().selectOneByQuery(SqlQuery.from(FormDefinition.class).equal(FormDefinitionInfo.ID, formData.getId()));
            double version = Integer.valueOf(formDefinition.getVersion()) + 0.1;
            formDefinition.setVersion(version + "");
            formDefinition.setId(UUID.randomUUID().toString());
            formDefinition = oneWorkForm(formData, formDefinition);
        } else {
            //2、根据传进来的 formData创建workForm对象
            formDefinition.setId(UUID.randomUUID().toString());
            formDefinition = oneWorkForm(formData, formDefinition);
        }
        return formDefinition;
    }

    public FormDefinition oneWorkForm(Form formData, FormDefinition formDefinition) {
        formDefinition.setDataObjectId(formData.getDataObjectId());
        formDefinition.setDescription(formData.getDescription());
        formDefinition.setFormCode(formData.getFormCode());
        formDefinition.setFormName(formData.getFormName());
        formDefinition.setFormOrder(formData.getFormOrder());
        formDefinition.setFormState(formData.getFormState());
        formDefinition.setFormTable(formData.getFormTable());
        formDefinition.setFormType(formData.getFormType());
        formDefinition.setHistoryVersion(formData.historyVersion());
        formDefinition.setVersion(formData.getVersion());
        formDefinition.setCreateDate(System.currentTimeMillis());
        formDefinition.setOrder(formData.getFormOrder());
        return formDefinition;
    }

    /**
     * 创建数据库表
     *
     * @param context
     * @param formData
     */
    protected void createTable(CommandContext context, FormDefinition formData) {
        if (CollectionUtils.isEmpty(formData.getFormFieldList())) {
            return;
        }
        //表结构生成器
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        //表名称生成器
        FormNameGenerator formNameGenerator = context.getApplicationContext().getBean(FormNameGenerator.class);
        ConfigedRelation configedRelation = new ConfigedRelation(true);
        configedRelation.setId(formData.getId());
        configedRelation.setName(formNameGenerator.genTableName(formData));
        configedRelation.setModule(Constants.MODULE_NAME);
        formData.getFormFieldList()
                .stream()
                .forEach(field -> {
                    Column column = newColumn(formData, field, formNameGenerator);
                    if ("pk".equals(field.getFieldType())) {
                        configedRelation.addPrimaryKey("pk", formNameGenerator.genFieldName(formData,field), 0);
                    }
                    configedRelation.addColumn(column);
                });
        //通过插件拦截创建表结构行为
        Map<String, CreateWorkFormPlugin> workFormPluginMap = context.getApplicationContext().getBeansOfType(CreateWorkFormPlugin.class);
        for (String beanName : workFormPluginMap.keySet()) {
            CreateWorkFormPlugin plugin = workFormPluginMap.get(beanName);
            if (formData.getFormType().equals(plugin.type())) {
                plugin.beforeCreate(configedRelation);
            }
        }
        dataBaseService.updateTable(configedRelation);
    }

    /**
     * TODO 详细的转换
     *
     * @param formData
     * @param field
     * @param generate
     * @return
     */
    private Column newColumn(FormDefinition formData, FormField field, FormNameGenerator generate) {
        Column column = new Column(formData.getFormTable(), field.getFieldCode(), "");
        column.setName(generate.genFieldName(formData, field));
        column.setTableName(generate.genTableName(formData));
        column.setType(Types.VARCHAR);
        column.setSize(field.getFieldLength());
        column.setNullAble(TrueOrFalse.FALSE);
        return column;
    }

}
