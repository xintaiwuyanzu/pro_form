package com.dr.framework.common.form.core.model;

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
    String getFormCode();

    /**
     * 获取表单名称
     *
     * @return
     */
    String getFormName();

    /**
     * 获取表单类型
     *
     * @return
     */
    String getFormType();

    /**
     * 获取状态
     *
     * @return
     */
    String getFormState();

    /**
     * 获取数据库表名
     *
     * @return
     */
    String getFormTable();

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
    int getFormOrder();

    /**
     * 获取数据权限
     *
     * @return
     */
    String getDataObjectId();

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

    /**
     * 获取备注信息
     *
     * @return
     */
    String getRemarks();
}
