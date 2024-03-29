package com.dr.framework.common.form.child.model;

public interface SubField {

    /**
     * 获取子表单字段定义Id
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
     * 获取子表单定义Id
     *
     * @return
     */
    String getSubFormId();

    /**
     * 获取字段编码
     *
     * @return
     */
    String getFieldCode();

    /**
     * 获取字段名称
     *
     * @return
     */
    String getFieldName();

    /**
     * 获取字段值
     *
     * @return
     */
    Object getFieldValue();

    /**
     * 获取字段类型
     *
     * @return
     */
    String getFieldType();

    /**
     * 获取状态
     *
     * @return
     */
    String getFieldState();

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
    int getFieldOrder();

    /**
     * 获取字段长度
     *
     * @return
     */
    int getFieldLength();

    /**
     * 获取数据权限
     *
     * @return
     */
    String getDataObjectId();

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
