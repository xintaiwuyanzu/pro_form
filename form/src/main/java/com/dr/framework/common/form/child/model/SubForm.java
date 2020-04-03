package com.dr.framework.common.form.child.model;

public interface SubForm {

    /**
     * 获取子表单定义主键
     *
     * @return
     */
    String getId();

    /**
     * 获取表单定义Id
     *
     * @return
     */
    String getFormDefinitionId();

    /**
     * 获取表单编码
     *
     * @return
     */
    String getSubFormCode();

    /**
     * 获取子表单名称
     *
     * @return
     */
    String getSubFormName();

    /**
     * 获取子表单类型
     *
     * @return
     */
    String getSubFormType();

    /**
     * 获取子表单状态
     *
     * @return
     */
    String getSubFormState();

    /**
     * 获取字表单数据表
     *
     * @return
     */
    String getSubFormTable();

    /**
     * 获取子表单描述
     *
     * @return
     */
    String getDescription();

    /**
     * 获取顺序号
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

}
