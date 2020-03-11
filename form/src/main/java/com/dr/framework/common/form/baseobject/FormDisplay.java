package com.dr.framework.common.form.baseobject;

/**
 * 表单的显示方案
 *
 * @author caor
 */
public interface FormDisplay {
    enum type {}

    /**
     * 获取编码
     *
     * @return
     */
    String getCode();

    /**
     * 获取名称
     *
     * @return
     */
    String getName();

    /**
     * 获取类型
     *
     * @return
     */
    String getType();

    /**
     * 获取脚本
     *
     * @return
     */
    String getJS();

    /**
     * 获取状态
     *
     * @return
     */
    String getState();

    /**
     * 获取列数
     *
     * @return
     */
    int getCol();

    /**
     * 获取位置
     *
     * @return
     */
    String getLocation();

    /**
     * 获取宽度
     *
     * @return
     */
    int getWidth();

    /**
     * 获取高度
     *
     * @return
     */
    int getHeight();

    /**
     * 获取背景色
     *
     * @return
     */
    String getBGColor();

    /**
     * 获取css样式
     *
     * @return
     */
    String getCss();

    /**
     * 获取描述
     *
     * @return
     */
    String getDescription();

    /**
     * 获取顺序号
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
     * 获取版本
     *
     * @return
     */
    String getVersion();
}
