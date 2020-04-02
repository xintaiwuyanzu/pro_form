package com.dr.framework.common.form.init.entity;

import com.dr.framework.common.entity.BaseStatusEntity;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.init.model.FieldDefault;
import com.dr.framework.common.form.init.model.FormDefault;
import com.dr.framework.common.form.util.Constans;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;

import java.util.Collection;
import java.util.List;

@Table(name = Constans.TABLE_PREFIX + "FormDefaultValue", module = Constans.MODULE_NAME, comment = "表单默认值")
public class FormDefaultValue extends BaseStatusEntity<String> implements FormDefault {

    @Column(name = "formId", comment = "表单id")
    private String formId;

    @Column(name = "linkName", comment = "环节名称")
    private String linkName;

    @Column(name = "linkCode", comment = "环节编码")
    private String linkCode;

    @Column(name = "defaultType", comment = "环节类型")
    private String defaultType;

    private Collection<FieldDefaultValue> fieldDefaultList;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkCode() {
        return linkCode;
    }

    public void setLinkCode(String linkCode) {
        this.linkCode = linkCode;
    }

    public String getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(String defaultType) {
        this.defaultType = defaultType;
    }

    public Collection<FieldDefaultValue> getFieldDefaultList() {
        return fieldDefaultList;
    }

    public void setFieldDefaultList(Collection<FieldDefaultValue> fieldDefaultList) {
        this.fieldDefaultList = fieldDefaultList;
    }
}
