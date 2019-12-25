package com.dr.framework.form.definition.service;

import com.dr.framework.form.definition.baseobject.Field;
import com.dr.framework.form.definition.baseobject.FieldDisplay;

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
