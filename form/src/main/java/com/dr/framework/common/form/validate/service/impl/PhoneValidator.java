package com.dr.framework.common.form.validate.service.impl;

import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.engine.model.core.FieldType;
import com.dr.framework.common.form.validate.model.ValidateDefinitionField;
import com.dr.framework.common.form.validate.model.ValidateResult;
import com.dr.framework.common.form.validate.service.Validator;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collection;

import static com.dr.framework.common.form.util.Constants.isChinaPhoneLegal;
import static com.dr.framework.common.form.util.Constants.isHKPhoneLegal;

public class PhoneValidator implements Validator {

    public static final String NAME = "PHONE";

    @Override
    public Collection<FieldType> acceptTypes() {
        return Arrays.asList(FieldType.STRING);
    }

    @Override
    public <T> ValidateResult validate(FieldModel fieldModel, ValidateDefinitionField definition, T data) {
        try {
            Assert.isTrue(isChinaPhoneLegal(String.valueOf(data)) || isHKPhoneLegal(String.valueOf(data)), "手机号码错误");
            return ValidateResult.success(fieldModel);
        } catch (Exception e) {
            return ValidateResult.fail("身份证号不正确", fieldModel);
        }
    }

    @Override
    public String type() {
        return NAME;
    }

    @Override
    public String description() {
        return "手机号码校验";
    }

}
