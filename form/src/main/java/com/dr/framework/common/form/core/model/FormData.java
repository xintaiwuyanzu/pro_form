package com.dr.framework.common.form.core.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 表单数据对象
 *
 * @author dr
 */
public class FormData extends HashMap<String, Serializable> {
    private String formDefinitionId;
    private String formDataId;
    private String fieldName;
    private String fieldCode;

    public FormData(String formDefinitionId, String formDataId) {
        this(formDefinitionId, formDataId, null, null);
    }

    public FormData(String formDefinitionId, String formDataId, String fieldName, String fieldCode) {
        this.formDefinitionId = formDefinitionId;
        this.formDataId = formDataId;
        this.fieldName = fieldName;
        this.fieldCode = fieldCode;
    }

    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    public String getFormDataId() {
        return formDataId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public Object getFieldValue(Field field) {
        return null;
    }

}
