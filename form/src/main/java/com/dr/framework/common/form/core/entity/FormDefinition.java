package com.dr.framework.common.form.core.entity;

import com.dr.framework.common.config.model.MetaMap;
import com.dr.framework.common.entity.BaseStatusEntity;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 表定义
 *
 * @author dr
 */
@Table(name = Constants.TABLE_PREFIX + "FormDefinition", module = Constants.MODULE_NAME, comment = "表单数据")
public class FormDefinition extends BaseStatusEntity<String> implements FormModel {

    @Column(name = "formCode", comment = "表单编码")
    private String formCode;

    @Column(name = "formName", comment = "表单名称")
    private String formName;

    @Column(name = "formType", comment = "表单类型")
    private String formType;

    @Column(name = "formTable", comment = "数据表名")
    private String formTable;

    @Column(name = "description", comment = "表单描述")
    private String description;

    @Column(name = "remarks", comment = "备注")
    private String remarks;


    @Column(name = "organiseId", comment = "组织id")
    private String organiseId;

    @Column(name = "dataObjectId", comment = "数据权限")
    private String dataObjectId;

    @Column(name = "version", comment = "表单版本")
    private Integer version;

    @Column(name = "isDefault", comment = "是否默认版本")
    private boolean isDefault;

    @Column(name = "isBuildIn", comment = "是否内部使用")
    private boolean buildIn;


    private List<FormField> fields;
    private MetaMap meta;

    public FormDefinition() {

    }

    public FormDefinition(FormModel formModel) {
        if (formModel != null) {
            setId(formModel.getId());
            setFormCode(formModel.getFormCode());
            setFormName(formModel.getFormName());
            setFormType(formModel.getFormType());
            setDescription(formModel.getDescription());
            setRemarks(formModel.getRemarks());
            setOrder(formModel.getFormOrder());
            setStatus(formModel.getFormState());
            setDataObjectId(formModel.getDataObjectId());
            setVersion(formModel.getVersion());
            setBuildIn(formModel.isBuildIn());
            if (formModel instanceof FormDefinition) {
                setFormTable(((FormDefinition) formModel).getFormTable());
            }
        }
    }

    /**
     * TODO 这个应该放到hashmap中提高性能
     *
     * @param code
     * @return
     */
    @Override
    @JsonIgnore
    public FormField getFieldByCode(String code) {
        if (fields != null) {
            for (FormField formField : fields) {
                if (formField.getFieldCode().equals(code)) {
                    return formField;
                }
            }
        }
        return null;
    }

    /**
     * TODO 这个应该放到hashmap中提高性能
     * 根据别名获取字段
     *
     * @param alias
     * @return
     */
    @Override
    @JsonIgnore
    public FormField getFieldByAlias(String alias) {
        if (fields != null) {
            for (FormField formField : fields) {
                if (formField.getFieldAlias() != null && formField.getFieldAlias().contains(alias)) {
                    return formField;
                }
            }
        }
        return null;
    }

    /**
     * 获取表单所有字段名称集合
     *
     * @return
     */
    @JsonIgnore
    public Set<String> getFieldNames() {
        return fields == null ? Collections.EMPTY_SET :
                fields.stream()
                        .map(FormField::getFieldCode)
                        .collect(Collectors.toSet());
    }

    /**
     * 获取所有字段别名集合
     *
     * @return
     */
    @JsonIgnore
    public Set<String> getFieldAlias() {
        return fields == null ? Collections.EMPTY_SET :
                fields.stream()
                        .flatMap(formField -> formField.getFieldAlias().stream())
                        .collect(Collectors.toSet());
    }


    @Override
    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getFormTable() {
        return formTable;
    }

    @Override
    public Integer getFormOrder() {
        return getOrder() == null ? 0 : getOrder();
    }

    @Override
    public String getFormState() {
        return getStatus();
    }

    public void setFormTable(String formTable) {
        this.formTable = formTable;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOrganiseId() {
        return organiseId;
    }

    public void setOrganiseId(String organiseId) {
        this.organiseId = organiseId;
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

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public boolean isBuildIn() {
        return buildIn;
    }

    public void setBuildIn(boolean buildIn) {
        this.buildIn = buildIn;
    }

    @Override
    public List<FormField> getFields() {
        return fields;
    }

    public void setFields(List<FormField> fields) {
        this.fields = fields;
    }

    @Override
    public MetaMap getMeta() {
        return meta;
    }

    public void setMeta(MetaMap meta) {
        this.meta = meta;
    }
}
