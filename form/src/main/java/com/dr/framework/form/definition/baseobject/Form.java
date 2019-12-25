package com.dr.framework.form.definition.baseobject;

/**
 * 表单
 */
public interface Form {
    /**
     * 获取表单ID
     *
     * @return
     */
    String getId();

    /**
     * 获取表单编码
     *
     * @return
     */
    String getCode();

    /**
     * 获取表单名称
     *
     * @return
     */
    String getName();

    /**
     * 获取表单类型
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
     * 获取数据库表名
     *
     * @return
     */
    String getTable();

    /**
     * 获取表单描述
     *
     * @return
     */
    String getDescription();

    /**
     * 获取表单顺序号
     *
     * @return
     */
    int getOrder();

    /**
     * 获取数据权限
     *
     * @return
     */
    String getDataobjectId();

    /**
     * 是否启用历史版本
     *
     * @return
     */
    boolean historyVersion();

    /**
     * 获取表单版本
     *
     * @return
     */
    String getVersion();
}