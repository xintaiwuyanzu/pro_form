package com.dr.framework.common.form.validate.service;

import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.validate.model.ValidateDefinition;
import com.dr.framework.common.form.validate.model.ValidateDefinitionField;
import com.dr.framework.common.form.validate.model.ValidateResults;
import com.dr.framework.common.page.Page;

import java.util.Collection;
import java.util.List;

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
    <T extends FormData> ValidateResults<T> validate(String validateDefinitionId, T formData);

}
