package com.dr.framework.common.form.service;

import com.dr.framework.common.form.baseobject.FormDisplay;
import com.dr.framework.common.form.model.Field;

import java.util.List;

public interface FormService {
    /**
     * 根据表单id获取所有字段列表
     *
     * @param formId 表单id
     * @return TODO 添加实现事件
     */
    List<Field> getFieldListByFormId(String formId);

    /**
     * 根据表单编码获取所有字段列表
     *
     * @param formCode 表单编码
     * @return TODO 添加实现事件
     */
    List<Field> getFieldListByFormCode(String formCode);

    /**
     * 根据表单id给指定表单添加字段
     *
     * @param formId 表单id
     * @param field  字段id
     * @return TODO 添加实现事件
     */
    int addFieldByFormId(String formId, Field field);

    /**
     * 根据表单id修改字段
     *
     * @param formId 表单id
     * @param field  字段id
     * @return TODO 添加实现事件
     */
    int updateFieldByFormId(String formId, Field field);

    /**
     * 根据表单id删除字段
     *
     * @param formId 表单id
     * @param field  字段id
     * @return TODO 添加实现事件
     */
    int deleteFieldByFormId(String formId, Field field);

    /**
     * 根据表单id获取表单显示方案
     *
     * @param formId 表单id
     * @return TODO 添加实现事件
     */
    FormDisplay getFormDisplayListByFormId(String formId);

    /**
     * 根据表单code获取表单显示方案
     *
     * @param formCode 表单编码
     * @return TODO 添加实现事件
     */
    FormDisplay getFormDisplayListByFormCode(String formCode);
}
