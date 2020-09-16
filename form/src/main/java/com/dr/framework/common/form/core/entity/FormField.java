package com.dr.framework.common.form.core.entity;

import com.dr.framework.common.config.model.MetaMap;
import com.dr.framework.common.entity.BaseStatusEntity;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.engine.model.core.FieldType;
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
@Table(name = Constants.TABLE_PREFIX + "FormField", module = Constants.MODULE_NAME, comment = "表单字段")
public class FormField extends BaseStatusEntity<String> implements FieldModel {

    @Column(name = "formDefinitionId", comment = "表单定义id")
    private String formDefinitionId;
    private String formDefinitionName;
    private String formDefinitionCode;

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

    @Column(name = "version", comment = "版本号")
    private Integer version;

    private MetaMap meta;

    public FormField() {
    }

    public FormField(FieldModel fieldModel) {
        if (fieldModel != null) {
            setId(fieldModel.getId());

            setDescription(fieldModel.getDescription());
            setRemarks(fieldModel.getRemarks());
            setStatus(fieldModel.getFieldState());
            setLabel(fieldModel.getLabel());

            setOrder(fieldModel.getFieldOrder());
            setVersion(fieldModel.getVersion());

            setFieldCode(fieldModel.getFieldCode());

            if (fieldModel.getFieldAlias() != null) {
                setFieldAliasStr(String.join(",", fieldModel.getFieldAlias()));
            }

            setFieldTypeStr(fieldModel.getFieldType().name());
            setFieldLength(fieldModel.getFieldLength());
            setFieldScale(fieldModel.getFieldScale());
        }
    }

    @Override
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

    @Override
    public Collection<String> getFieldAlias() {
        return StringUtils.isEmpty(fieldAliasStr) ? Collections.emptySet() :
                new HashSet<>(Arrays.asList(fieldAliasStr.split(",")));
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.valueOf(fieldTypeStr);
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
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

    public void setFieldTypeStrEnum(FieldType fieldType) {
        this.fieldTypeStr = fieldType.name();
    }

    public void setFieldTypeStr(String fieldTypeStr) {
        this.fieldTypeStr = fieldTypeStr;
        this.setFieldTypeStrEnum(FieldType.valueOf(fieldTypeStr));
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
    public Integer getFieldOrder() {
        return getOrder() == null ? 0 : getOrder();
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

    @Override
    public MetaMap getMeta() {
        return meta;
    }

    public void setMeta(MetaMap meta) {
        this.meta = meta;
    }

    @Override
    public String getFormDefinitionName() {
        return formDefinitionName;
    }

    public void setFormDefinitionName(String formDefinitionName) {
        this.formDefinitionName = formDefinitionName;
    }

    @Override
    public String getFormDefinitionCode() {
        return formDefinitionCode;
    }

    public void setFormDefinitionCode(String formDefinitionCode) {
        this.formDefinitionCode = formDefinitionCode;
    }
}
