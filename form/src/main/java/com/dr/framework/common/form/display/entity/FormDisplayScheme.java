package com.dr.framework.common.form.display.entity;

import com.dr.framework.common.entity.BaseStatusEntity;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;

/**
 * 表单显示方案
 *
 * @author dr
 */
@Table(name = Constants.TABLE_PREFIX + "FormDisplayScheme", module = Constants.MODULE_NAME, comment = "表单显示方案")
public class FormDisplayScheme extends BaseStatusEntity<String> {

    @Column(name = "formDefinitionId", comment = "表单定义id")
    private String formDefinitionId;

    @Column(name = "schemeName", comment = "名称")
    private String schemeName;

    @Column(name = "schemeCode", comment = "编码")
    private String schemeCode;

    @Column(name = "schemeType", comment = "类型")
    private String schemeType;

    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    public void setFormDefinitionId(String formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
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

}

