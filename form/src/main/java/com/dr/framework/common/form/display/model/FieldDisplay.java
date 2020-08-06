package com.dr.framework.common.form.display.model;

import com.dr.framework.common.form.engine.model.FormCodeNameModel;

/**
 * 字段的显示方案
 *
 * @author lic
 * @author dr
 */
public interface FieldDisplay extends FormCodeNameModel {
    enum type {
        /**
         * 大文本域
         */
        bigtext,
        /**
         * 复选
         */
        check,
        /**
         * 日期时间
         */
        datetime,
        /**
         * 显示域
         */
        display,
        /**
         * 下拉
         */
        dropdown,
        /**
         * 编辑域
         */
        editfield,
        /**
         * 隐藏
         */
        hidden,
        /**
         * 单选
         */
        radio,
        /**
         * 选择域
         */
        selectfield,
        /**
         * 特殊编辑域
         */
        specialedit,
        /**
         * 评论域
         */
        suggest,
        /**
         * 文本框
         */
        text,
        /**
         * 文本域
         */
        textarea,
        /**
         * 按钮
         */
        button
    }

    /**
     * 获取域标题（显示名称）
     *
     * @return
     */
    String getTitle();

    /**
     * 获取值
     *
     * @return
     */
    String getValue();

    /**
     * 是否为空
     */
    boolean isNull();

    /**
     * 获取状态
     *
     * @return
     */
    String getState();

    /**
     * 获取宽度
     *
     * @return
     */
    int getWidth();

    /**
     * 获取提示信息
     *
     * @return
     */
    String getTip();

    /**
     * 获取默认值
     *
     * @return
     */
    String getDefaultVal();

    /**
     * 获取保留域
     *
     * @return
     */
    String getRetentionDomain();

    /**
     * 获取约束条件
     *
     * @return
     */
    String getConstraintCondition();

    /**
     * 获取域下标
     *
     * @return
     */
    int getDomainSubscript();
}
