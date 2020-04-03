package com.dr.framework.common.form.validate.service.impl;

import com.dr.framework.common.form.core.command.FieldDataSelectOneCommand;
import com.dr.framework.common.form.core.command.FormDefinitionSelectOneCommand;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.validate.command.ValidateDefinitionSelectOneCommand;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionForm;
import com.dr.framework.common.form.validate.model.ValidateDefinitionField;
import com.dr.framework.common.form.validate.model.ValidateResults;
import com.dr.framework.common.form.validate.service.ValidateService;
import com.dr.framework.common.form.validate.service.Validator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class DefaultValidateService implements ValidateService {

    @Autowired
    CommandExecutor executor;

    List<Validator> defaultValidators = Arrays.asList(
            new IdNoValidator(),
            new PhoneValidator()
    );

    @Autowired
    List<Validator> validators;

    @Override
    public <T extends FormData> ValidateResults<T> validate(String validateDefinitionId, T formData) {
        ValidateResults results = new ValidateResults(formData);

        //根据表单数据查询表单定义
        FormDefinition formDefinition = (FormDefinition) executor.execute(new FormDefinitionSelectOneCommand(formData.getFormDefinitionId()));
        //根据校验定义id查询所有对应的字段校验
        ValidateDefinitionForm validateDefinitionForm = executor.execute(new ValidateDefinitionSelectOneCommand(validateDefinitionId));
        for (ValidateDefinitionField validateField : validateDefinitionForm.getValidateDefinitionFieldList()) {
            //获取指定字段的值
            Object filedValue = formData.get("");
            //按照校验定义规则执行校验
            Field formFieldDefinition = executor.execute(new FieldDataSelectOneCommand(formDefinition.getId(), validateField.getFieldCode()));
            //从validators 查出ValidateDefinition定义的校验类型
            Validator validator = null;

            // ValidateResult result = validator.validate(formFieldDefinition, validate, filedValue);
            //results.addValidateResult(result);
        }
        return results;
    }
}
