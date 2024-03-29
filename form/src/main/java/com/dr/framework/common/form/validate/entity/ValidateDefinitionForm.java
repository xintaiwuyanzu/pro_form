package com.dr.framework.common.form.validate.entity;

import com.dr.framework.common.entity.BaseStatusEntity;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.form.validate.model.ValidateDefinition;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;

import java.util.Collection;

@Table(name = Constants.TABLE_PREFIX + "ValidateDefinitionForm", module = Constants.MODULE_NAME, comment = "表单校验")
public class ValidateDefinitionForm extends BaseStatusEntity<String> implements ValidateDefinition {

    @Column(name = "formDefinitionId", comment = "表单定义id")
    private String formDefinitionId;

    @Column(name = "validateCode", comment = "校验编码")
    private String validateCode;

    @Column(name = "validateName", comment = "校验名称")
    private String validateName;

    @Column(name = "validateType", comment = "校验类型")
    private String validateType;

    @Column(name = "validateState", comment = "校验状态")
    private String validateState;

    @Column(name = "description", comment = "校验描述")
    private String description;

    @Column(name = "validateOrder", comment = "顺序号")
    private int validateOrder;

    @Column(name = "version", comment = "版本号")
    private String version;

    /**
     * 字段校验属性
     */
    private Collection<ValidateDefinitionFormField> ValidateDefinitionFieldList;

    @Override
    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    public void setFormDefinitionId(String formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
    }

    @Override
    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    @Override
    public String getValidateName() {
        return validateName;
    }

    public void setValidateName(String validateName) {
        this.validateName = validateName;
    }

    @Override
    public String getValidateType() {
        return validateType;
    }

    public void setValidateType(String validateType) {
        this.validateType = validateType;
    }

    @Override
    public String getValidateState() {
        return validateState;
    }

    public void setValidateState(String validateState) {
        this.validateState = validateState;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getValidateOrder() {
        return validateOrder;
    }

    public void setValidateOrder(int validateOrder) {
        this.validateOrder = validateOrder;
    }

    @Override
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Collection<ValidateDefinitionFormField> getValidateDefinitionFieldList() {
        return ValidateDefinitionFieldList;
    }

    public void setValidateDefinitionFieldList(Collection<ValidateDefinitionFormField> validateDefinitionFieldList) {
        ValidateDefinitionFieldList = validateDefinitionFieldList;
    }


}
