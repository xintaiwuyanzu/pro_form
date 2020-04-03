package com.dr.framework.common.form.validate.service.impl;

import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.FieldType;
import com.dr.framework.common.form.validate.model.ValidateDefinitionField;
import com.dr.framework.common.form.validate.model.ValidateResult;
import com.dr.framework.common.form.validate.service.Validator;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collection;

import static com.dr.framework.common.form.util.Constans.isChinaPhoneLegal;
import static com.dr.framework.common.form.util.Constans.isHKPhoneLegal;

public class PhoneValidator implements Validator {

    public static final String NAME = "PHONE";

    @Override
    public Collection<FieldType> acceptTypes() {
        return Arrays.asList(FieldType.STRING);
    }

    @Override
    public <T> ValidateResult validate(Field field, ValidateDefinitionField definition, T data) {
        try {
            Assert.isTrue(isChinaPhoneLegal(String.valueOf(data)) || isHKPhoneLegal(String.valueOf(data)), "手机号码错误");
            return ValidateResult.success(field);
        } catch (Exception e) {
            return ValidateResult.fail("身份证号不正确", field);
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
