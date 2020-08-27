package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
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
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;

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
        //TODO
        FormData formData = new FormData(
                wrapper.getForm().getId(),
                (String) data.get(FormData.FORM_DATA_ID_KEY)
        );
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
        DataBaseService dataBaseService = context.getDataBaseService();
        FormNameGenerator generator = context.getFormNameGenerator();

        Relation relation = dataBaseService.getTableInfo(generator.genTableName(formDefinition), Constants.MODULE_NAME);
        Assert.isTrue(relation != null, "未生成实体表！");
        return new DefaultFormRelationWrapper(context, formDefinition, relation);
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
