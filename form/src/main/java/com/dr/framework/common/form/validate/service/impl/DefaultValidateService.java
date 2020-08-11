package com.dr.framework.common.form.validate.service.impl;

import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.validate.command.FormDataValidateCommand;
import com.dr.framework.common.form.validate.model.ValidateResults;
import com.dr.framework.common.form.validate.service.ValidateService;
import com.dr.framework.common.form.validate.service.Validator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * 默认校验实现类
 *
 * @author dr
 */
public class DefaultValidateService implements ValidateService {

    @Autowired
    CommandExecutor executor;

    final List<Validator> defaultValidators = Arrays.asList(
            new IdNoValidator(),
            new PhoneValidator()
    );

    @Autowired(required = false)
    List<Validator> validators;

    /**
     * 获取字段校验结果
     *
     * @param validateDefinitionId 校验定义Id
     * @param formData             表单数据
     * @return
     */
    @Override
    public ValidateResults<FormData> validate(String validateDefinitionId, FormData formData) {
        return executor.execute(new FormDataValidateCommand(validateDefinitionId, defaultValidators, formData));
    }

}
