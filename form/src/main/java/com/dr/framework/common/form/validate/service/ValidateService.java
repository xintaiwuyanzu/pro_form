package com.dr.framework.common.form.validate.service;

import com.dr.framework.common.form.validate.model.ValidateResults;

/**
 * 表单校验相关的代码
 *
 * @author dr
 */
public interface ValidateService {
    /**
     * 执行校验
     *
     * @param validateDefinitionId 校验定义Id
     * @param formData             表单数据
     * @param <T>
     * @return
     */
    <T> ValidateResults<T> validate(String validateDefinitionId, T formData);
}
