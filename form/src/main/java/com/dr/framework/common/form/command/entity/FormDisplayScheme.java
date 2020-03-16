package com.dr.framework.common.form.command.entity;

import com.dr.framework.common.entity.BaseStatusEntity;
import com.dr.framework.common.form.util.Constans;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;

import java.util.List;

@Table(name = Constans.TABLE_PREFIX + "FormDisplayScheme", module = Constans.MODULE_NAME, comment = "表单显示方案")
public class FormDisplayScheme extends BaseStatusEntity<String> {

    @Column(name = "formId", comment = "表单id")
    private String formId;

    @Column(name = "schemeName", comment = "名称")
    private String schemeName;

    @Column(name = "schemeCode", comment = "编码")
    private String schemeCode;

    @Column(name = "schemeType", comment = "类型")
    private String schemeType;

    private List<FieldDisplayScheme> fieldDisplayList;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }

    public List<FieldDisplayScheme> getFieldDisplayList() {
        return fieldDisplayList;
    }

    public void setFieldDisplayList(List<FieldDisplayScheme> fieldDisplayList) {
        this.fieldDisplayList = fieldDisplayList;
    }

}

