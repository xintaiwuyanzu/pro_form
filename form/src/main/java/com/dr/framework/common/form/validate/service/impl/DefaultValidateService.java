package com.dr.framework.common.form.validate.service.impl;

import com.dr.framework.common.form.validate.model.ValidateResults;
import com.dr.framework.common.form.validate.service.ValidateService;

public class DefaultValidateService implements ValidateService {

    @Override
    public <T> ValidateResults<T> validate(String validateDefinitionId, T formData) {
        return null;
    }
}
