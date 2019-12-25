package com.dr.framework.form.definition.baseobject;

/**
 * @author caor
 * @date 2019/12/10 15:02
 */
public interface Button {
    /**
     * 获取编码
     *
     * @return
     */
    String getCode();

    /**
     * 获取方法
     *
     * @return
     */
    String getMethod();

    /**
     * 获取名称
     *
     * @return
     */
    String getName();

    /**
     * 获取链接
     *
     * @return
     */
    String getUrl();

    /**
     * 获取类型
     *
     * @return
     */
    String getType();

    /**
     * 获取顺序号
     *
     * @return
     */
    String getOrder();

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