package com.dr.framework.common.form.validate.command;

import com.dr.framework.common.form.core.command.FormDefinitionSelectOneCommand;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.FieldType;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionForm;
import com.dr.framework.common.form.validate.model.ValidateDefinitionField;
import com.dr.framework.common.form.validate.model.ValidateResult;
import com.dr.framework.common.form.validate.model.ValidateResults;
import com.dr.framework.common.form.validate.service.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FormDataValidateCommand implements Command<ValidateResults<FormData>> {

    private String validateDefinitionId;
    private List<Validator> validators;
    private FormData formData;

    public FormDataValidateCommand(String validateDefinitionId, FormData formData) {
        this(validateDefinitionId, Collections.emptyList(), formData);
    }

    public FormDataValidateCommand(String validateDefinitionId, List<Validator> defaultValidators, FormData formData) {
        this.validateDefinitionId = validateDefinitionId;
        this.validators = defaultValidators;
        this.formData = formData;
    }

    @Override
    public ValidateResults<FormData> execute(CommandContext context) {
        CommandExecutor executor = context.getExecutor();
        ValidateResults<FormData> validateResults = new ValidateResults<>(formData);
        //根据校验定义id查询所有对应的字段校验
        ValidateDefinitionForm validateDefinitionForm = executor.execute(new ValidateDefinitionSelectOneCommand(validateDefinitionId));
        //根据表单数据查询表单定义
        FormDefinition formDefinition = (FormDefinition) executor.execute(new FormDefinitionSelectOneCommand(validateDefinitionForm.getFormDefinitionId()));
        //遍历这一套校验的字段
        for (ValidateDefinitionField validateField : validateDefinitionForm.getValidateDefinitionFieldList()) {
            //获取这个字段的全部属性
            Field fieldDefinition = formDefinition.getFieldByCode(validateField.getFieldCode());
            //从validators 查出ValidateDefinitionField定义的校验类型
            List<Validator> validators = getValidator(fieldDefinition);
            Object data = formData.getFieldValue(fieldDefinition);
            for (Validator validator : validators) {
                ValidateResult validateResult = validator.validate(fieldDefinition, validateField, data);
                validateResults.addValidateResult(validateResult);
            }
        }
        return validateResults;
    }

    /**
     * 获取匹配的校验方法体
     *
     * @param field
     * @return
     */
    private List<Validator> getValidator(Field field) {
        List<Validator> validatorList = new ArrayList<>();
        for (Validator validator : validators) {
            Collection<FieldType> fieldTypes = validator.acceptTypes();
            for (FieldType fieldType : fieldTypes) {
                //先过滤默认的内置的校验数
                if (!fieldType.equals("default")) {
                    //匹配这个类型的校验数
                    if (fieldType.equals(field.getFieldType())) {
                        validatorList.add(validator);
                    }
                } else {
                    validatorList.add(validator);
                }
            }
        }
        return validatorList;
    }

}
