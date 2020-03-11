package com.dr.framework.common.form.service;

import com.dr.framework.common.form.baseobject.FieldDisplay;
import com.dr.framework.common.form.model.Field;

import java.util.List;

public interface FieldService {
    /**
     * 获取所有字段列表
     *
     * @return
     */
    List<Field> getFieldList();

    /**
     * 获取字段显示方案
     *
     * @param fieldId
     * @return
     */
    FieldDisplay getFieldDisplayByFieldId(String fieldId);
}
