package com.dr.framework.common.form.display.entity;

import com.dr.framework.common.entity.BaseStatusEntity;
import com.dr.framework.common.form.util.Constans;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;

@Table(name = Constans.TABLE_PREFIX + "FieldDisplayScheme", module = Constans.MODULE_NAME, comment = "表单字段显示方案")
public class FieldDisplayScheme extends BaseStatusEntity<String> {

    @Column(name = "formId", comment = "表单id")
    private String formId;

    @Column(name = "formDisplayId", comment = "表单显示方案ID")
    private String formDisplayId;

    @Column(name = "fieldName", comment = "字段名称")
    private String fieldName;

    @Column(name = "fieldType", comment = "字段类型")
    private String fieldType;

    @Column(name = "display", comment = "显示方案")
    private String display;

    @Column(name = "isNotNull", comment = "是否为空")
    private boolean isNotNull;

    @Column(name = "defaultEnable", comment = "默认启用")
    private boolean defaultEnable;

    @Column(name = "custom", comment = "自定义")
    private String custom;

    @Column(name = "remarks", comment = "备注")
    private String remarks;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFormDisplayId() {
        return formDisplayId;
    }

    public void setFormDisplayId(String formDisplayId) {
        this.formDisplayId = formDisplayId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public boolean isNotNull() {
        return isNotNull;
    }

    public void setNotNull(boolean notNull) {
        isNotNull = notNull;
    }

    public boolean isDefaultEnable() {
        return defaultEnable;
    }

    public void setDefaultEnable(boolean defaultEnable) {
        this.defaultEnable = defaultEnable;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
