package com.dr.framework.common.form.service;

import com.dr.framework.common.form.model.Field;

import java.util.List;

/**
 * @author caor
 * @date 2019/12/12 16:16
 */
public interface FormTabService {
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
}
