package com.dr.framework.common.form.validate.service;

import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.FieldType;
import com.dr.framework.common.form.engine.TypeObject;
import com.dr.framework.common.form.validate.model.ValidateDefinition;
import com.dr.framework.common.form.validate.model.ValidateResult;

import java.util.Collection;

/**
 * 校验器，真正执行校验的实现，可以自定义实现
 *
 * @author dr
 */
public interface Validator extends TypeObject {
    /**
     * 能够校验的字段类型
     *
     * @return
     */
    Collection<FieldType> acceptTypes();

    /**
     * 根据字段定义执行校验，并且返回校验结果
     *
     * @param field
     * @param definition
     * @param data
     * @param <T>
     * @return
     */
    <T> ValidateResult validate(Field field, ValidateDefinition definition, T data);
}
