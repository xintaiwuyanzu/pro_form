package com.dr.framework.common.form.validate.command;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionForm;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionFormField;
import com.dr.framework.common.form.validate.model.ValidateDefinition;
import com.dr.framework.common.form.validate.model.ValidateDefinitionField;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class ValidateDefinitionInsertCommand implements Command<ValidateDefinition> {

    /**
     * 校验主表
     */
    private ValidateDefinition validateDefinition;

    /**
     * 校验字段
     */
    private Collection<ValidateDefinitionField> ValidateDefinitionFieldList;


    public ValidateDefinitionInsertCommand(ValidateDefinition validateDefinition, Collection<ValidateDefinitionField> ValidateDefinitionFieldList) {
        this.validateDefinition = validateDefinition;
        this.ValidateDefinitionFieldList = ValidateDefinitionFieldList;
    }

    @Override
    public ValidateDefinition execute(CommandContext context) {
        ValidateDefinitionForm validateDefinitionForm = getValidateDefinitionForm(validateDefinition);
        //保存定义数据
        CommonMapper mapper = context.getMapper();
        Collection<ValidateDefinitionFormField> ValidateDefinitionFields = getValidateDefinitionFormFields(validateDefinitionForm);
        if (ValidateDefinitionFields.size() > 0) {
            for (ValidateDefinitionFormField validateDefinitionField : ValidateDefinitionFields) {
                if (StringUtils.isEmpty(validateDefinitionField.getId())) {
                    validateDefinitionField.setId(UUID.randomUUID().toString());
                }
                validateDefinitionField.setValidateFormId(validateDefinitionForm.getId());
                //保存字段数据
                mapper.insert(validateDefinitionField);
            }
        }
        validateDefinitionForm.setValidateDefinitionFieldList(ValidateDefinitionFields);
        //保存主表数据
        mapper.insert(validateDefinitionForm);
        return validateDefinitionForm;
    }

    public ValidateDefinitionForm getValidateDefinitionForm(ValidateDefinition validateDefinition) {
        ValidateDefinitionForm validateDefinitionForm = new ValidateDefinitionForm();
        validateDefinitionForm.setId(validateDefinition.getId());
        validateDefinitionForm.setDescription(validateDefinition.getDescription());
        validateDefinitionForm.setFormDefinitionId(validateDefinition.getFormDefinitionId());
        validateDefinitionForm.setValidateCode(validateDefinition.getValidateCode());
        validateDefinitionForm.setValidateName(validateDefinition.getValidateName());
        validateDefinitionForm.setValidateOrder(validateDefinition.getValidateOrder());
        validateDefinitionForm.setValidateState(validateDefinition.getValidateState());
        validateDefinitionForm.setValidateType(validateDefinition.getValidateType());
        validateDefinitionForm.setVersion(validateDefinition.getVersion());
        validateDefinitionForm.setCreateDate(System.currentTimeMillis());
        return validateDefinitionForm;
    }

    public Collection<ValidateDefinitionFormField> getValidateDefinitionFormFields(ValidateDefinitionForm validateDefinitionForm) {
        Collection<ValidateDefinitionFormField> validateDefinitionFormFields = new ArrayList<>();
        if (ValidateDefinitionFieldList.size() > 0) {
            for (ValidateDefinitionField validateDefinitionField : ValidateDefinitionFieldList) {
                ValidateDefinitionFormField validateDefinitionFormField = new ValidateDefinitionFormField();
                validateDefinitionFormField.setId(validateDefinitionField.getId());
                validateDefinitionFormField.setValidateFormId(validateDefinitionForm.getId());
                validateDefinitionFormField.setCheckConfig(validateDefinitionField.getCheckConfig());
                validateDefinitionFormField.setCheckConfigThree(validateDefinitionField.getCheckConfigThree());
                validateDefinitionFormField.setCheckConfigTwo(validateDefinitionField.getCheckConfigTwo());
                validateDefinitionFormField.setCustom(validateDefinitionField.getCustom());
                validateDefinitionFormField.setFieldName(validateDefinitionField.getFieldName());
                validateDefinitionFormField.setFieldType(validateDefinitionField.getFieldType());
                validateDefinitionFormField.setFormDefinitionId(validateDefinitionField.getFormDefinitionId());
                validateDefinitionFormField.setNotNull(validateDefinitionField.isNotNull());
                validateDefinitionFormField.setText1(validateDefinitionField.getText1());
                validateDefinitionFormField.setText2(validateDefinitionField.getText2());
                validateDefinitionFormField.setText3(validateDefinitionField.getText3());
                validateDefinitionFormField.setCreateDate(System.currentTimeMillis());
                validateDefinitionFormFields.add(validateDefinitionFormField);
            }
        }
        return validateDefinitionFormFields;
    }

}
