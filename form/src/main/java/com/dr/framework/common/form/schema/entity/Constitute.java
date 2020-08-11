package com.dr.framework.common.form.schema.entity;

import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.form.init.model.FormDefault;
import com.dr.framework.common.form.validate.model.ValidateDefinition;

import java.util.HashMap;
import java.util.Map;

public class Constitute {

    private FormModel formModel;

    private ValidateDefinition validateDefinition;

    private FormDefault formDefault;

    public Constitute() {
    }

    public Constitute(FormModel formModel, ValidateDefinition validateDefinition, FormDefault formDefault) {
        this.formModel = formModel;
        this.validateDefinition = validateDefinition;
        this.formDefault = formDefault;
    }

    public FormModel getForm() {
        return formModel;
    }

    public void setForm(FormModel formModel) {
        this.formModel = formModel;
    }

    public ValidateDefinition getValidateDefinition() {
        return validateDefinition;
    }

    public void setValidateDefinition(ValidateDefinition validateDefinition) {
        this.validateDefinition = validateDefinition;
    }

    public FormDefault getFormDefault() {
        return formDefault;
    }

    public void setFormDefault(FormDefault formDefault) {
        this.formDefault = formDefault;
    }

    public Map<String, Object> getSchemaMap() {
        Map<String, Object> schemaMap = new HashMap<>();
        schemaMap.put("definitionId", formModel);
        schemaMap.put("validateId", validateDefinition);
        schemaMap.put("defaultValueId", formDefault);
        return schemaMap;
    }

}
