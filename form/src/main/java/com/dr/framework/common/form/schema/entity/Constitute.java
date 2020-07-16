package com.dr.framework.common.form.schema.entity;

import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.init.model.FormDefault;
import com.dr.framework.common.form.validate.model.ValidateDefinition;

import java.util.HashMap;
import java.util.Map;

public class Constitute {

    private Form form;

    private ValidateDefinition validateDefinition;

    private FormDefault formDefault;

    public Constitute() {
    }

    public Constitute(Form form, ValidateDefinition validateDefinition, FormDefault formDefault) {
        this.form = form;
        this.validateDefinition = validateDefinition;
        this.formDefault = formDefault;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
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
        schemaMap.put("definitionId", form);
        schemaMap.put("validateId", validateDefinition);
        schemaMap.put("defaultValueId", formDefault);
        return schemaMap;
    }

}
