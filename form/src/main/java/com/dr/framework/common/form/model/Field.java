package com.dr.framework.common.form.model;

/**
 * 字段
 *
 * @author caor
 */
public interface Field {
    /**
     * 获取字段编码
     *
     * @return
     */
    String getCode();

    /**
     * 获取字段名称
     *
     * @return
     */
    String getName();

    /**
     * 获取字段值
     *
     * @return
     */
    Object getValue();

    /**
     * 获取字段类型
     *
     * @return
     */
    String getType();

    /**
     * 获取状态
     *
     * @return
     */
    String getState();

    /**
     * 获取字段描述
     *
     * @return
     */
    String getDescription();

    /**
     * 获取字段顺序号
     *
     * @return
     */
    int getOrder();

    /**
     * 获取字段长度
     *
     * @return
     */
    int getLength();

    /**
     * 获取数据权限
     *
     * @return
     */
    String getDataobjectId();

    /**
     * 是否使用历史版本
     *
     * @return
     */
    boolean historyVersion();

    /**
     * 获取版本号
     *
     * @return
     */
    String getVersion();
}
