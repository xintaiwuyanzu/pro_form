package com.dr.framework.common.form.schema.entity;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.init.entity.FormDefaultValue;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionForm;

import java.util.HashMap;
import java.util.Map;

public class Constitute {

    private FormDefinition formDefinition;

    private ValidateDefinitionForm validateDefinitionForm;

    private FormDefaultValue formDefaultValue;

    public Constitute(FormDefinition formDefinition, ValidateDefinitionForm validateDefinitionForm, FormDefaultValue formDefaultValue) {
        this.formDefinition = formDefinition;
        this.validateDefinitionForm = validateDefinitionForm;
        this.formDefaultValue = formDefaultValue;
    }

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

    public Map<String, Object> getSchemaMap() {
        Map<String, Object> schemaMap = new HashMap<>();
        schemaMap.put("definitionId", formDefinition);
        schemaMap.put("validateId", validateDefinitionForm);
        schemaMap.put("defaultValueId", formDefaultValue);
        return schemaMap;
    }

}
