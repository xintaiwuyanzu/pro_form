package com.dr.framework.common.form.command.entity;

import com.dr.framework.common.entity.BaseStatusEntity;
import com.dr.framework.core.orm.annotations.Column;

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

    @Column(name = "nullAble", comment = "是否为空")
    private boolean nullAble;

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

    public boolean isNullAble() {
        return nullAble;
    }

    public void setNullAble(boolean nullAble) {
        this.nullAble = nullAble;
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
