package com.dr.framework.common.form.core.service;

import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.Form;

import java.util.Collection;
import java.util.List;

/**
 * 表单定义service，用来抽象封装表单定义相关service
 *
 * @author dr
 */
public interface FormDefinitionService {

    /**
     * 添加表单定义
     *
     * @param form        表基本信息定义
     * @param fields      表字段定义
     * @param createTable 是否创建表结构
     */
    Form addFormDefinition(Form form, Collection<Field> fields, boolean createTable);
    //更新表单定义 TODO
    //查询表单定义 TODO
    //删除表单定义 TODO
    /**
     * =========================================
     * 下面都是之前写的方法，先留着
     *
     * =========================================
     */
    /**
     * 根据tabId获取所有字段列表
     *
     * @param tabId
     * @return TODO 添加实现事件
     */
    List<Field> getFieldListByTabId(String tabId);

    /**
     * 根据tabCode获取所有字段列表
     *
     * @param tabCode
     * @return TODO 添加实现事件
     */
    List<Field> getFieldListByTabCode(String tabCode);

    /**
     * 根据tabId 获取下级TabList
     *
     * @param tabId
     * @return
     */
    List<Field> getTabListByTabId(String tabId);

    /**
     * 获取所有字段列表
     *
     * @return
     */
    List<Field> getFieldList();

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

}
