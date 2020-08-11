package com.dr.framework.common.form.engine.entity;

import com.dr.framework.common.config.model.MetaMap;
import com.dr.framework.common.entity.BaseDescriptionEntity;
import com.dr.framework.common.form.engine.model.FormRelationModel;
import com.dr.framework.core.orm.annotations.Column;

/**
 * 与表单定义关联的实体类
 *
 * @author dr
 */
public class FormDefinitionRelation extends BaseDescriptionEntity<String> implements FormRelationModel {

    @Column(comment = "表单定义id", length = 100)
    private String formDefinitionId;

    @Column(comment = "表单版本", length = 10)
    private Integer version;
    @Column(name = "dataObjectId", comment = "数据权限", length = 100)
    private String dataObjectId;

    private String formDefinitionName;
    private String formDefinitionCode;
    private MetaMap meta;

    @Override
    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    public void setFormDefinitionId(String formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public MetaMap getMeta() {
        return meta;
    }

    public void setMeta(MetaMap meta) {
        this.meta = meta;
    }

    public String getDataObjectId() {
        return dataObjectId;
    }

    public void setDataObjectId(String dataObjectId) {
        this.dataObjectId = dataObjectId;
    }
}
