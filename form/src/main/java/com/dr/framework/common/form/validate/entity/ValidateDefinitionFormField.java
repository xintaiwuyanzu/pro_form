package com.dr.framework.common.form.validate.entity;

import com.dr.framework.common.entity.BaseStatusEntity;
import com.dr.framework.common.form.util.Constans;
import com.dr.framework.common.form.validate.model.ValidateDefinitionField;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;

@Table(name = Constans.TABLE_PREFIX + "ValidateDefinitionFormField", module = Constans.MODULE_NAME, comment = "表单校验字段")
public class ValidateDefinitionFormField extends BaseStatusEntity<String> implements ValidateDefinitionField {

    @Column(name = "formDefinitionId", comment = "表单定义id")
    private String formDefinitionId;

    @Column(name = "ValidateFormId", comment = "校验主表id")
    private String ValidateFormId;

    @Column(name = "fieldName", comment = "字段名称")
    private String fieldName;

    @Column(name = "fieldCode", comment = "字段编码")
    private String fieldCode;

    @Column(name = "fieldType", comment = "字段类型")
    private String fieldType;

    @Column(name = "isNotNull", comment = "是否为空")
    private boolean isNotNull;

    @Column(name = "checkConfig", comment = "校验匹配1")
    private String checkConfig;

    @Column(name = "checkConfigTwo", comment = "校验匹配2")
    private String checkConfigTwo;

    @Column(name = "checkConfigThree", comment = "校验匹配3")
    private String checkConfigThree;

    @Column(name = "custom", comment = "自定义")
    private String custom;

    @Column(name = "remarks", comment = "备注")
    private String remarks;

    @Column(name = "text1", comment = "备用字段1")
    private String text1;

    @Column(name = "text2", comment = "备用字段2")
    private String text2;

    @Column(name = "text3", comment = "备用字段3")
    private String text3;

    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    public void setFormDefinitionId(String formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
    }

    public String getValidateFormId() {
        return ValidateFormId;
    }

    public void setValidateFormId(String validateFormId) {
        ValidateFormId = validateFormId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

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

    public boolean isNotNull() {
        return isNotNull;
    }

    public void setNotNull(boolean notNull) {
        isNotNull = notNull;
    }

    public String getCheckConfig() {
        return checkConfig;
    }

    public void setCheckConfig(String checkConfig) {
        this.checkConfig = checkConfig;
    }

    public String getCheckConfigTwo() {
        return checkConfigTwo;
    }

    public void setCheckConfigTwo(String checkConfigTwo) {
        this.checkConfigTwo = checkConfigTwo;
    }

    public String getCheckConfigThree() {
        return checkConfigThree;
    }

    public void setCheckConfigThree(String checkConfigThree) {
        this.checkConfigThree = checkConfigThree;
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

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }
}
