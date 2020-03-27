package com.dr.framework.common.form.core.model;

import java.util.HashMap;

/**
 * 表单数据对象
 *
 * @author dr
 */
public class FormData extends HashMap<String, Object> {
    private String formDefinitionId;
    private String formDataId;

    public FormData(String formDefinitionId, String formDataId) {
        this.formDefinitionId = formDefinitionId;
        this.formDataId = formDataId;
    }


}
