package com.dr.framework.common.form.init.service;

import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.init.model.FieldDefault;

import java.io.Serializable;

public interface FieldDefaultManager {

    /**
     * 根据字段定义和 默认值定义计算得出默认值
     *
     * @param field
     * @param fieldDefault
     * @return
     */
    Serializable getDefaultValue(Field field, FieldDefault fieldDefault);

}
