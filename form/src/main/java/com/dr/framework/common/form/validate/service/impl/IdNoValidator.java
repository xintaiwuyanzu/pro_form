package com.dr.framework.common.form.validate.service.impl;

import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.engine.model.core.FieldType;
import com.dr.framework.common.form.validate.model.ValidateDefinitionField;
import com.dr.framework.common.form.validate.model.ValidateResult;
import com.dr.framework.common.form.validate.service.Validator;
import com.dr.framework.common.util.IDNo;

import java.util.Arrays;
import java.util.Collection;

/**
 * 身份证校验
 *
 * @author dr
 */
public class IdNoValidator implements Validator {
    public static final String NAME = "ID";

    @Override
    public Collection<FieldType> acceptTypes() {
        return Arrays.asList(FieldType.STRING);
    }

    @Override
    public <T> ValidateResult validate(FieldModel fieldModel, ValidateDefinitionField definition, T data) {
        try {
            IDNo.from(String.valueOf(data));
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
        return "身份证校验";
    }

}
