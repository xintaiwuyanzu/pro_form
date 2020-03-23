package com.dr.framework.common.form.core.model;

/**
 * 表单数据对象
 *
 * @author dr
 */
public class FormData {
    private String formDefinitionId;
    private String formDataId;

    public FormData(String formDefinitionId, String formDataId) {
        this.formDefinitionId = formDefinitionId;
        this.formDataId = formDataId;
    }


}
