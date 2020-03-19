package com.dr.framework.common.form.command.entity;


import com.dr.framework.common.entity.BaseStatusEntity;
import com.dr.framework.common.form.util.Constans;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;

@Table(name = Constans.TABLE_PREFIX + "ProcessType", module = Constans.MODULE_NAME, comment = "流程类型")
public class ProcessType extends BaseStatusEntity<String> {

    @Column(name = "organiseId", comment = "组织id")
    private String organiseId;

    @Column(name = "typeName", comment = "类型名称")
    private String typeName;

    @Column(name = "typeCode", comment = "类型编码")
    private String typeCode;

    @Column(name = "typeDescribe", comment = "类型描述")
    private String typeDescribe;

    @Column(name = "typeOrder", comment = "顺序号")
    private int typeOrder;

    @Column(name = "typeBuiltIn", comment = "是否内置")
    private boolean  typeBuiltIn;

    @Column(name = "remarks", comment = "备注")
    private String remarks;

    public String getOrganiseId() {
        return organiseId;
    }

    public void setOrganiseId(String organiseId) {
        this.organiseId = organiseId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeDescribe() {
        return typeDescribe;
    }

    public void setTypeDescribe(String typeDescribe) {
        this.typeDescribe = typeDescribe;
    }

    public int getTypeOrder() {
        return typeOrder;
    }

    public void setTypeOrder(int typeOrder) {
        this.typeOrder = typeOrder;
    }

    public boolean isTypeBuiltIn() {
        return typeBuiltIn;
    }

    public void setTypeBuiltIn(boolean typeBuiltIn) {
        this.typeBuiltIn = typeBuiltIn;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
