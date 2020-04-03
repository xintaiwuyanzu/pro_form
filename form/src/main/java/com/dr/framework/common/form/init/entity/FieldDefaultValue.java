package com.dr.framework.common.form.init.entity;

import com.dr.framework.common.entity.BaseStatusEntity;
import com.dr.framework.common.form.init.model.FieldDefault;
import com.dr.framework.common.form.util.Constans;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;

@Table(name = Constans.TABLE_PREFIX + "FieldDefaultValue", module = Constans.MODULE_NAME, comment = "字段默认值")
public class FieldDefaultValue extends BaseStatusEntity<String> implements FieldDefault {

    @Column(name = "formDefinitionId", comment = "表单定义id")
    private String formDefinitionId;

    @Column(name = "formDefaultValueId", comment = "表单默认值Id")
    private String formDefaultValueId;

    @Column(name = "fieldName", comment = "字段名称")
    private String fieldName;

    @Column(name = "fieldCode", comment = "字段编码")
    private String fieldCode;

    @Column(name = "fieldType", comment = "字段类型")
    private String fieldType;

    @Column(name = "defaultValue", comment = "默认值")
    private String defaultValue;

    @Column(name = "custom", comment = "自定义")
    private String custom;

    @Column(name = "remarks", comment = "备注")
    private String remarks;

    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    public void setFormDefinitionId(String formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
    }

    public String getFormDefaultValueId() {
        return formDefaultValueId;
    }

    public void setFormDefaultValueId(String formDefaultValueId) {
        this.formDefaultValueId = formDefaultValueId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
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
