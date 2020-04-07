package com.dr.framework.common.form.child.entity;

import com.dr.framework.common.entity.BaseStatusEntity;
import com.dr.framework.common.form.child.model.SubForm;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;

import java.util.Collection;

@Table(name = Constants.TABLE_PREFIX + "SubForm", module = Constants.MODULE_NAME, comment = "子表单定义")
public class SubFormDefinition extends BaseStatusEntity<String> implements SubForm {

    @Column(name = "formDefinitionId", comment = "表单定义Id")
    private String formDefinitionId;

    @Column(name = "subFormCode", comment = "子表单编号")
    private String subFormCode;

    @Column(name = "subFormName", comment = "子表单名称")
    private String subFormName;

    @Column(name = "subFormType", comment = "字表单类型")
    private String subFormType;

    @Column(name = "subFormState", comment = "子表单状态")
    private String subFormState;

    @Column(name = "subFormTable", comment = "数据表名")
    private String subFormTable;

    @Column(name = "description", comment = "表单描述")
    private String description;

    @Column(name = "formOrder", comment = "顺序号")
    private int formOrder;

    @Column(name = "dataObjectId", comment = "数据权限")
    private String dataObjectId;

    @Column(name = "historyVersion", comment = "是否使用历史版本")
    private boolean historyVersion;

    @Column(name = "version", comment = "表单版本")
    private String version;

    private Collection<SubFormField> subFormFieldList;

    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    public void setFormDefinitionId(String formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
    }

    public String getSubFormCode() {
        return subFormCode;
    }

    public void setSubFormCode(String subFormCode) {
        this.subFormCode = subFormCode;
    }

    public String getSubFormType() {
        return subFormType;
    }

    public void setSubFormType(String subFormType) {
        this.subFormType = subFormType;
    }

    public String getSubFormName() {
        return subFormName;
    }

    public void setSubFormName(String subFormName) {
        this.subFormName = subFormName;
    }

    public String getSubFormState() {
        return subFormState;
    }

    public void setSubFormState(String subFormState) {
        this.subFormState = subFormState;
    }

    public String getSubFormTable() {
        return subFormTable;
    }

    public void setSubFormTable(String subFormTable) {
        this.subFormTable = subFormTable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFormOrder() {
        return formOrder;
    }

    public void setFormOrder(int formOrder) {
        this.formOrder = formOrder;
    }

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Collection<SubFormField> getSubFormFieldList() {
        return subFormFieldList;
    }

    public void setSubFormFieldList(Collection<SubFormField> subFormFieldList) {
        this.subFormFieldList = subFormFieldList;
    }
}
