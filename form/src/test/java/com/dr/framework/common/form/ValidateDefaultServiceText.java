package com.dr.framework.common.form;

import com.dr.framework.common.form.validate.entity.ValidateDefinitionForm;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionFormField;
import com.dr.framework.common.form.validate.model.ValidateDefinition;
import com.dr.framework.common.form.validate.model.ValidateDefinitionField;
import com.dr.framework.common.form.validate.service.ValidateDefaultService;
import com.dr.framework.common.form.validate.service.ValidateService;
import com.dr.framework.common.form.validate.service.Validator;
import com.dr.framework.common.page.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class ValidateDefaultServiceText extends AbstractApplicationTest {
    @Autowired
    ValidateService validator;
    @Autowired
    Validator validators;
    @Autowired
    ValidateDefaultService validateDefaultService;

    @Test
    public void addValidateDefinitionForm() {
        ValidateDefinitionForm validateDefinitionForm = new ValidateDefinitionForm();
        validateDefinitionForm.setId(UUID.randomUUID().toString());
        validateDefinitionForm.setDescription("1");
        validateDefinitionForm.setFormDefinitionId("1");
        validateDefinitionForm.setValidateCode("default");
        validateDefinitionForm.setValidateName("张斌");
        validateDefinitionForm.setValidateOrder(1);
        validateDefinitionForm.setValidateState("1");
        validateDefinitionForm.setValidateType("1");
        validateDefinitionForm.setVersion("1");
        Collection<ValidateDefinitionField> validateDefinitionFormFields = new ArrayList<>();
        Collection<ValidateDefinitionFormField> list = new ArrayList<>();
        ValidateDefinitionFormField validateDefinitionFormField = new ValidateDefinitionFormField();
        validateDefinitionFormField.setId(UUID.randomUUID().toString());
        validateDefinitionFormField.setFormDefinitionId("bin");
        validateDefinitionFormField.setCheckConfig("1");
        validateDefinitionFormField.setCheckConfigTwo("1");
        validateDefinitionFormField.setFieldCode("default");
        validateDefinitionFormField.setCheckConfigThree("1");
        validateDefinitionFormField.setCustom("1");
        validateDefinitionFormField.setFieldName("1");
        validateDefinitionFormField.setFieldType("1");
        validateDefinitionFormField.setNotNull(true);
        validateDefinitionFormField.setText1("张斌");
        validateDefinitionFormField.setText2("1");
        validateDefinitionFormField.setText3("1");
        validateDefinitionFormFields.add(validateDefinitionFormField);
        validateDefinitionForm.setValidateDefinitionFieldList(list);
        validateDefaultService.addValidateDefinitionForm(validateDefinitionForm, validateDefinitionFormFields);
        //validateDefaultService.updateValidateDefinitionForm(validateDefinitionForm, validateDefinitionFormFields);

    }

    @Test
    public void select(){
        // ValidateDefinition validateDefinition = validateDefaultService.SelectOneValidateDefinitionForm("11fd6860-5e95-4e1c-8978-c46f05d5eaed");
        List<ValidateDefinition> validateDefinitions = validateDefaultService.SelectValidateDefinitionForm("1", "1");
        System.out.println(validateDefinitions.toString());
    }
    @Test
    public void page(){
        Page<ValidateDefinition> validateDefinitionPage = validateDefaultService.SelectPageValidateDefinitionForm("1", 1, 1);
        System.out.println(validateDefinitionPage.getData().toString());
    }

    @Test
    public void delete(){
        validateDefaultService.removeValidateDefinitionForm("11fd6860-5e95-4e1c-8978-c46f05d5eaed");
    }


}
