package com.dr.framework.common.form.baseobject;

public interface FormExtend {
    /**
     * 获取key
     *
     * @return
     */
    String getKey();

    /**
     * 获取名称
     *
     * @return
     */
    String getName();

    /**
     * 获取值
     *
     * @return
     */
    String getValue();

    /**
     * 获取类型
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
     * 获取数据权限
     *
     * @return
     */
    String getDataobjectId();

    /**
     * 获取顺序号
     *
     * @return
     */
    int getOrder();

    /**
     * 获取描述
     *
     * @return
     */
    String getDescription();

    /**
     * 是否启用历史版本
     *
     * @return
     */
    boolean historyVersion();

    /**
     * 获取版本
     *
     * @return
     */
    String getVersion();
}
