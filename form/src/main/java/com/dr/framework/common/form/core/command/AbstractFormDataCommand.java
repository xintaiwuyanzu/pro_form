package com.dr.framework.common.form.core.command;

import com.dr.framework.common.entity.IdEntity;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.model.FormRelationWrapper;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.jdbc.Column;
import com.dr.framework.core.orm.jdbc.Relation;
import com.dr.framework.core.orm.jdbc.TrueOrFalse;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 表单数据命令抽象类
 *
 * @author dr
 */
public abstract class AbstractFormDataCommand<T> extends AbstractFormDefinitionIdCommand {
    /**
     * 是否自动检测并创建表结构
     */
    private final boolean autoCheck;

    private Map<String, FormRelation> formRelationMap = Collections.synchronizedMap(new HashMap<>());


    public AbstractFormDataCommand(String formDefinitionId, boolean autoCheck) {
        super(formDefinitionId);
        this.autoCheck = autoCheck;
    }

    public AbstractFormDataCommand(String formCode, Integer version, boolean autoCheck) {
        super(formCode, version);
        this.autoCheck = autoCheck;
    }

    public T execute(CommandContext context) {
        FormDefinition formDefinition = getFormDefinition(context);
        Assert.notNull(formDefinition, FORM_NOT_DEFINITION_ERROR);
        Assert.isTrue(
                checkAndGen(context, formDefinition),
                "未生成表结构！"
        );
        FormRelationWrapper wrapper = getFormRelation(context, formDefinition);
        return doModifyData(context, wrapper);
    }

    /**
     * 校验并且创建表结构
     *
     * @param context
     * @param formDefinition
     */
    protected boolean checkAndGen(CommandContext context, FormDefinition formDefinition) {
        if (!tableExist(context, formDefinition)) {
            if (autoCheck) {
                createTable(context, formDefinition);
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 真正执行sql语句
     *
     * @param context
     * @param wrapper
     * @return
     */
    protected abstract T doModifyData(CommandContext context, FormRelationWrapper wrapper);


    protected FormData mapFormData(FormRelationWrapper wrapper, HashMap<String, Serializable> data) {
        String id = (String) data.get(FormData.FORM_DATA_ID_KEY);
        if (StringUtils.isEmpty(id)) {
            id = (String) data.get(FormData.FORM_DATA_ID_KEY);
        }
        //TODO
        FormData formData = new FormData(wrapper.getForm().getId(), id);
        formData.putAll(data);

        FormModel formModel = wrapper.getForm();
        if (formModel != null) {
            formData.setFormDefinitionName(formModel.getFormName());
            formData.setFormDefinitionCode(formModel.getFormCode());
            formData.setVersion(formModel.getVersion());
        }
        return formData;
    }

    /**
     * 获取表单定义的Relation对象
     *
     * @param context
     * @param formDefinition
     * @return
     */
    protected FormRelationWrapper getFormRelation(CommandContext context, FormDefinition formDefinition) {
        Assert.isTrue(formDefinition != null, FORM_CAN_NOT_BE_NULL_ERROR);
        Relation relation = getRelation(context, formDefinition);
        Assert.isTrue(relation != null, "未生成实体表！");
        return new DefaultFormRelationWrapper(context, formDefinition, relation);
    }

    protected Relation getRelation(CommandContext context, FormDefinition formDefinition) {
        FormNameGenerator generator = context.getFormNameGenerator();
        String tableName = generator.genTableName(formDefinition);
        if (formRelationMap.containsKey(tableName)) {
            return formRelationMap.get(tableName);
        } else {
            FormRelation relation = doGetRelation(context, tableName, formDefinition);
            formRelationMap.put(tableName, relation);
            return relation;
        }
    }

    protected synchronized FormRelation doGetRelation(CommandContext context, String tableName, FormDefinition formDefinition) {
        DataBaseService dataBaseService = context.getDataBaseService();
        Relation relation = dataBaseService.getTableInfo(tableName, Constants.MODULE_NAME);
        return new FormRelation(formDefinition, relation, context.getFormNameGenerator());
    }


    static class FormRelation extends Relation {
        FormDefinition formModel;
        Relation tableRelation;

        public FormRelation(FormDefinition formModel, Relation tableRelation, FormNameGenerator formNameGenerator) {
            super(true);
            this.formModel = formModel;
            this.tableRelation = tableRelation;
            for (FormField field : formModel.getFields()) {
                String columnName = formNameGenerator.genFieldName(formModel, field);
                Column column = tableRelation.getColumn(columnName);
                if (column != null) {
                    addColumn(new FormColumn(column, columnName, this));
                }
            }
            //主键
            Column idColumn = tableRelation.getColumn(IdEntity.ID_COLUMN_NAME);
            if (idColumn != null) {
                FormColumn formColumn = new FormColumn(idColumn, IdEntity.ID_COLUMN_NAME, this);
                addColumn(formColumn);
                addPrimaryKey("", formColumn.getName(), 0);
            }
        }

        @Override
        public String getCreateSql() {
            return tableRelation.getCreateSql();
        }

        @Override
        public String getRemark() {
            return tableRelation.getRemark();
        }

        @Override
        public String getModule() {
            return tableRelation.getModule();
        }

        @Override
        public String getName() {
            return tableRelation.getName();
        }
    }

    static class FormColumn extends Column {
        Column column;
        FormRelation formRelation;

        public FormColumn(Column column, String columnName, FormRelation formRelation) {
            super(column.getTableName(), column.getName(), columnName);
            this.column = column;
            this.formRelation = formRelation;
        }

        @Override
        public int getDecimalDigits() {
            return column.getDecimalDigits();
        }

        @Override
        public int getPosition() {
            return column.getPosition();
        }

        @Override
        public int getSize() {
            return column.getSize();
        }

        @Override
        public int getType() {
            return column.getType();
        }

        @Override
        public String getTypeName() {
            return column.getTypeName();
        }

        @Override
        public String getRemark() {
            return column.getRemark();
        }

        @Override
        public TrueOrFalse getNullAble() {
            return column.getNullAble();
        }

        @Override
        public String getDefaultValue() {
            return column.getDefaultValue();
        }

        @Override
        public TrueOrFalse getAutoIncrement() {
            return column.getAutoIncrement();
        }

        @Override
        public String getName() {
            return column.getName();
        }

        @Override
        public String getTableName() {
            return column.getTableName();
        }

        @Override
        public Relation getRelation() {
            return formRelation;
        }
    }

    static class DefaultFormRelationWrapper implements FormRelationWrapper {
        private final CommandContext commandContext;
        private final FormDefinition formDefinition;
        private final Relation relation;

        public DefaultFormRelationWrapper(CommandContext commandContext, FormDefinition formDefinition, Relation relation) {
            this.commandContext = commandContext;
            this.formDefinition = formDefinition;
            this.relation = relation;
        }

        @Override
        public FormModel getForm() {
            return formDefinition;
        }

        @Override
        public Relation getRelation() {
            return relation;
        }

        @Override
        public CommandContext getContext() {
            return commandContext;
        }

        @Override
        public Column getColumn(String fieldCodeOrAlias) {
            Assert.isTrue(!StringUtils.isEmpty(fieldCodeOrAlias), "字段名称不能为空！");
            FieldModel fieldModel = formDefinition.getFieldByCode(fieldCodeOrAlias);
            if (fieldModel == null) {
                fieldModel = formDefinition.getFieldByAlias(fieldCodeOrAlias);
            }
            String columnName = fieldCodeOrAlias;
            if (fieldModel != null) {
                columnName = fieldModel.getFieldCode();
            } else {
                Command.logger.warn("未找到指定的字段,使用默认参数查询列：{}", fieldCodeOrAlias);
            }
            return relation.getColumn(columnName);
        }
    }

}
