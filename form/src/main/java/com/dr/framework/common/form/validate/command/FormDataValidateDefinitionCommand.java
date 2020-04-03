package com.dr.framework.common.form.validate.command;

import com.dr.framework.common.form.core.command.FormDefinitionSelectOneCommand;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionForm;
import com.dr.framework.common.form.validate.model.ValidateDefinitionField;
import com.dr.framework.common.form.validate.model.ValidateResults;
import com.dr.framework.common.form.validate.service.Validator;
import com.dr.framework.common.form.validate.service.impl.IdNoValidator;

public class FormDataValidateDefinitionCommand implements Command<FormData> {

    private String formDefinitionId;

    private String validateDefinitionId;

    public FormDataValidateDefinitionCommand(String formDefinitionId, String validateDefinitionId) {
        this.formDefinitionId = formDefinitionId;
        this.validateDefinitionId = validateDefinitionId;
    }

    @Override
    public FormData execute(CommandContext context) {
        CommandExecutor executor = context.getExecutor();
        //根据表单数据查询表单定义
        FormDefinition formDefinition = (FormDefinition) executor.execute(new FormDefinitionSelectOneCommand(formDefinitionId));
        //根据校验定义id查询所有对应的字段校验
        ValidateDefinitionForm validateDefinitionForm = executor.execute(new ValidateDefinitionSelectOneCommand(validateDefinitionId));
        //遍历这一套校验的字段
        for (ValidateDefinitionField validateField : validateDefinitionForm.getValidateDefinitionFieldList()) {
            //获取这个字段的全部属性
            Field fieldDefinition = formDefinition.getFieldByCode(validateField.getFieldCode());
            //从validators 查出ValidateDefinitionField定义的校验类型
            Validator validator = getValidator(context, fieldDefinition, validateField);

        }
        return null;
    }

    public Validator getValidator(CommandContext context, Field field, ValidateDefinitionField validateField) {
        IdNoValidator idNoValidator = context.getApplicationContext().getBean(IdNoValidator.class);
        return idNoValidator;
    }
}
