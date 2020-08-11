package com.dr.framework.common.form.init.service.impl;

import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.init.model.FieldDefault;
import com.dr.framework.common.form.init.service.FieldDefaultManager;

import java.io.Serializable;

public class FieldDefaultManagerImpl implements FieldDefaultManager {

    /**
     * 根据字段定义和 默认值定义计算得出默认值
     *
     * @param fieldModel
     * @param fieldDefault
     * @return
     */
    @Override
    public Serializable getDefaultValue(FieldModel fieldModel, FieldDefault fieldDefault) {
        //TODO
        return null;
    }
}
