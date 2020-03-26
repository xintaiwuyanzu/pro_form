package com.dr.framework.common.form.core.entity;

import com.dr.framework.common.entity.BaseStatusEntity;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.util.Constans;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;

@Table(name = Constans.TABLE_PREFIX + "formField", module = Constans.MODULE_NAME, comment = "表单字段")
public class FormField extends BaseStatusEntity<String> implements Field {

    @Column(name = "formId", comment = "表单ID")
    private String formId;

    @Column(name = "fieldCode", comment = "字段编码")
    private String fieldCode;

    @Column(name = "fieldName", comment = "字段名称")
    private String fieldName;

    @Column(name = "fieldValue", comment = "字段值")
    private String fieldValue;

    @Column(name = "fieldType", comment = "字段类型")
    private String fieldType;

    @Column(name = "fieldState", comment = "状态")
    private String fieldState;

    @Column(name = "description", comment = "字段描述")
    private String description;

    @Column(name = "fieldOrder", comment = "顺序号")
    private int fieldOrder;

    @Column(name = "fieldLength", comment = "字段长度")
    private int fieldLength;

    @Column(name = "dataObjectId", comment = "数据权限")
    private String dataObjectId;

    @Column(name = "historyVersion", comment = "是否使用历史版本")
    private boolean historyVersion;

    @Column(name = "version", comment = "版本号")
    private String version;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    @Override
    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public String getFieldState() {
        return fieldState;
    }

    public void setFieldState(String fieldState) {
        this.fieldState = fieldState;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getFieldOrder() {
        return fieldOrder;
    }

    public void setFieldOrder(int fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    @Override
    public int getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(int fieldLength) {
        this.fieldLength = fieldLength;
    }

    @Override
    public String getDataObjectId() {
        return dataObjectId;
    }

    @Override
    public boolean historyVersion() {
        return false;
    }

    public void setDataObjectId(String dataObjectId) {
        this.dataObjectId = dataObjectId;
    }

    public boolean isHistoryVersion() {
        return historyVersion;
    }

    public void setHistoryVersion(boolean historyVersion) {
        this.historyVersion = historyVersion;
    }

    @Override
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
