package com.dr.framework.common.form.command.entity;

import com.dr.framework.common.entity.BaseStatusEntity;
import com.dr.framework.common.form.model.Form;
import com.dr.framework.common.form.util.Constans;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;

import java.util.List;

@Table(name = Constans.TABLE_PREFIX + "WorkForm", module = Constans.MODULE_NAME, comment = "表单数据")
public class WorkForm extends BaseStatusEntity<String> implements Form {

    @Column(name = "organiseId", comment = "组织id")
    private String organiseId;

    @Column(name = "formCode", comment = "表单编码")
    private String formCode;

    @Column(name = "formName", comment = "表单名称")
    private String formName;

    @Column(name = "formType", comment = "表单类型")
    private String formType;

    @Column(name = "formState", comment = "表单状态")
    private String formState;

    @Column(name = "formTable", comment = "数据表名")
    private String formTable;

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

    private List<FormField> formFieldList;

    public String getOrganiseId() {
        return organiseId;
    }

    public void setOrganiseId(String organiseId) {
        this.organiseId = organiseId;
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

    @Override
    public String getFormState() {
        return formState;
    }

    public void setFormState(String formState) {
        this.formState = formState;
    }

    @Override
    public String getFormTable() {
        return formTable;
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
    public int getFormOrder() {
        return formOrder;
    }

    public void setFormOrder(int formOrder) {
        this.formOrder = formOrder;
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

    public List<FormField> getFormFieldList() {
        return formFieldList;
    }

    public void setFormFieldList(List<FormField> formFieldList) {
        this.formFieldList = formFieldList;
    }
}
