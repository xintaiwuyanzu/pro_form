package com.dr.framework.common.form.schema.entity;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.init.entity.FormDefaultValue;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionForm;

public class Constitute {

    private FormDefinition formDefinition;

    private ValidateDefinitionForm validateDefinitionForm;

    private FormDefaultValue formDefaultValue;



    public FormDefinition getFormDefinition() {
        return formDefinition;
    }

    public void setFormDefinition(FormDefinition formDefinition) {
        this.formDefinition = formDefinition;
    }

    public ValidateDefinitionForm getValidateDefinitionForm() {
        return validateDefinitionForm;
    }

    public void setValidateDefinitionForm(ValidateDefinitionForm validateDefinitionForm) {
        this.validateDefinitionForm = validateDefinitionForm;
    }

    public FormDefaultValue getFormDefaultValue() {
        return formDefaultValue;
    }

    public void setFormDefaultValue(FormDefaultValue formDefaultValue) {
        this.formDefaultValue = formDefaultValue;
    }
}
