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

    @Column(name = "formDefinitionId", comment = "表单定义id")
    private String formDefinitionId;

    @Column(name = "linkName", comment = "默认环节名称")
    private String linkName;

    @Column(name = "linkCode", comment = "默认环节编码")
    private String linkCode;

    @Column(name = "defaultType", comment = "默认环节类型")
    private String defaultType;

    @Column(name = "description", comment = "描述")
    private String description;

    @Column(name = "defaultState", comment = "默认环节状态")
    private String defaultState;

    @Column(name = "version", comment = "版本号")
    private String version;

    private Collection<FieldDefaultValue> fieldDefaultList;

    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    public void setFormDefinitionId(String formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultState() {
        return defaultState;
    }

    public void setDefaultState(String defaultState) {
        this.defaultState = defaultState;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Collection<FieldDefaultValue> getFieldDefaultList() {
        return fieldDefaultList;
    }

    public void setFieldDefaultList(Collection<FieldDefaultValue> fieldDefaultList) {
        this.fieldDefaultList = fieldDefaultList;
    }
}
