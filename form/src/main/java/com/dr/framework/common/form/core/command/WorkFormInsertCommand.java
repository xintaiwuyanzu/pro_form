package com.dr.framework.common.form.core.command;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.WorkForm;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.plugin.CreateWorkFormPlugin;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.Constans;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.jdbc.Column;
import com.dr.framework.core.orm.jdbc.TrueOrFalse;
import com.dr.framework.core.orm.module.ConfigedRelation;
import org.springframework.util.CollectionUtils;

import java.sql.Types;
import java.util.Collection;
import java.util.Map;

/**
 * 创建表单定义
 *
 * @author dr
 * @author lich
 */
public class WorkFormInsertCommand implements Command<Form> {
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

    public WorkFormInsertCommand(Form formData, Collection<Field> formFieldList, boolean generate, boolean copyData) {
        this.formData = formData;
        this.formFieldList = formFieldList;
        this.generate = generate;
        this.copyData = copyData;
    }

    public WorkFormInsertCommand(Form formData, Collection<Field> formFieldList, boolean generate) {
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
        WorkForm workForm = getWorkForm();
        //根据参数获取fieldList参数
        Collection<FormField> fields = getFormFields(workForm);
        //保存定义数据
        CommonMapper mapper = context.getMapper();
        mapper.insert(workForm);
        for (FormField formField : fields) {
            mapper.insert(formField);
        }
        //是否生成数据库表 true:生成； false:不生成
        if (generate) {
            workForm.setFormFieldList(fields);
            createTable(context, workForm);
        }
        //如果需要复制数据 并且是更新表定义，则执行数据复制
        if (copyData && workForm.getId().equalsIgnoreCase(formData.getId())) {
            copyData(formData, workForm);
        }
        return formData;
    }

    /**
     * TOdo 从老表复制数据到新表
     *
     * @param formData 老表结构定义
     * @param workForm 新表结构定义
     */
    protected void copyData(Form formData, WorkForm workForm) {
    }

    /**
     * 根据新的workform和传进来的全局formFieldList创建新的表单字段定义信息
     *
     * @param workForm
     * @return
     */
    protected Collection<FormField> getFormFields(WorkForm workForm) {
        //todo
        return null;
    }

    protected WorkForm getWorkForm() {
        //TODO
        //1、根据 formData全局变量id判断：如果有id，则查询表单定义，能查询到说明之前定义过，新的表单定义需要更改表单版本号
        //2、根据传进来的 formData创建workForm对象
        return null;
    }

    protected void createTable(CommandContext context, WorkForm formData) {
        if (CollectionUtils.isEmpty(formData.getFormFieldList())) {
            return;
        }
        //表结构生成器
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        //表名称生成器
        FormNameGenerator formNameGenerator = context.getApplicationContext().getBean(FormNameGenerator.class);

        ConfigedRelation configedRelation = new ConfigedRelation(true);
        configedRelation.setId(formData.getId());
        //TODO 用nameGener生成主键名称
        configedRelation.addPrimaryKey("pk", "id", 0);

        configedRelation.setName(formNameGenerator.genTableName(formData));
        configedRelation.setModule(Constans.MODULE_NAME);

        formData.getFormFieldList()
                .stream()
                .forEach(field -> {
                    Column column = newColumn(formData, field, formNameGenerator);
                    configedRelation.addColumn(column);
                    //TODO 字段其他属性，用来控制 主键，索引等等
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
    private Column newColumn(WorkForm formData, FormField field, FormNameGenerator generate) {
        Column column = new Column(formData.getFormTable(), field.getFieldCode(), "");
        column.setName(generate.genFieldName(formData, field));
        column.setTableName(generate.genTableName(formData));
        column.setType(Types.VARCHAR);
        column.setSize(field.getFieldLength());
        column.setNullAble(TrueOrFalse.FALSE);
        return column;
    }

}
