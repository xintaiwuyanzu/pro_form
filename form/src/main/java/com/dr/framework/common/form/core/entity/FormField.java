package com.dr.framework.common.form.core.entity;

import com.dr.framework.common.entity.BaseStatusEntity;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.FieldType;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * 表单字段
 *
 * @author dr
 */
@Table(name = Constants.TABLE_PREFIX + "formField", module = Constants.MODULE_NAME, comment = "表单字段")
public class FormField extends BaseStatusEntity<String> implements Field {

    @Column(name = "formDefinitionId", comment = "表单定义id")
    private String formDefinitionId;

    @Column(name = "fieldCode", comment = "字段编码")
    private String fieldCode;

    @Column(name = "fieldAlias", comment = "字段别名")
    private String fieldAliasStr;

    @Column(name = "fieldType", comment = "字段类型")
    private String fieldTypeStr;

    @Column(name = "fieldLength", comment = "字段长度")
    private int fieldLength;
    @Column(name = "fieldScale", comment = "字段精度")
    private int fieldScale;


    @Column(name = "fieldLabel", comment = "中文名称")
    private String label;

    @Column(name = "description", comment = "字段描述", length = 1000)
    private String description;
    @Column(name = "remarks", comment = "备注", length = 1000)
    private String remarks;

    @Column(name = "dataObjectId", comment = "数据权限")
    private String dataObjectId;

    @Column(name = "version", comment = "版本号")
    private Integer version;


    public FormField() {
    }

    public FormField(Field field) {
        if (field != null) {
            setId(field.getId());

            setDataObjectId(field.getDataObjectId());
            setDescription(field.getDescription());
            setRemarks(field.getRemarks());
            setStatus(field.getFieldState());
            setLabel(field.getLabel());

            setOrder(field.getFieldOrder());
            setVersion(field.getVersion());

            setFieldCode(field.getFieldCode());

            if (field.getFieldAlias() != null) {
                setFieldAliasStr(String.join(",", field.getFieldAlias()));
            }

            setFieldTypeStr(field.getFieldType());
            setFieldLength(field.getFieldLength());
            setFieldScale(field.getFieldScale());
        }
    }


    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    public void setFormDefinitionId(String formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
    }

    @Override
    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    @Override
    public Collection<String> getFieldAlias() {
        return StringUtils.isEmpty(fieldAliasStr) ? Collections.emptySet() :
                new HashSet<>(Arrays.asList(fieldAliasStr.split(",")));
    }

    @Override
    public FieldType getFieldType() {
        return StringUtils.isEmpty(fieldTypeStr) ? null : FieldType.valueOf(this.fieldTypeStr);
    }

    public String getFieldAliasStr() {
        return fieldAliasStr;
    }

    public void setFieldAliasStr(String fieldAliasStr) {
        this.fieldAliasStr = fieldAliasStr;
    }

    public String getFieldTypeStr() {
        return fieldTypeStr;
    }

    public void setFieldTypeStr(FieldType fieldTypeStr) {
        this.fieldTypeStr = fieldTypeStr.name();
    }

    @Override
    public String getFieldState() {
        return getStatus();
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
        return getOrder();
    }

    @Override
    public int getFieldLength() {
        return fieldLength;
    }

    @Override
    public int getFieldScale() {
        return fieldScale;
    }

    public void setFieldScale(int fieldScale) {
        this.fieldScale = fieldScale;
    }

    public void setFieldLength(int fieldLength) {
        this.fieldLength = fieldLength;
    }

    @Override
    public String getDataObjectId() {
        return dataObjectId;
    }

    public void setDataObjectId(String dataObjectId) {
        this.dataObjectId = dataObjectId;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
