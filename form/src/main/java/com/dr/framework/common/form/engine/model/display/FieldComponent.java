package com.dr.framework.common.form.engine.model.display;

import com.dr.framework.common.form.engine.model.core.FieldType;

/**
 * 表单显示控件抽象接口，
 * 用来定义扩展控件属性，需要前后台同时实现
 * <p>
 * 大文本域
 * 复选
 * 日期时间
 * 显示域
 * 下拉
 * 编辑域
 * 隐藏
 * 单选
 * 选择域
 * 特殊编辑域
 * 评论域
 * 文本框
 * 文本域
 * 按钮
 *
 * @author dr
 */
public interface FieldComponent {
    /**
     * 获取字段类型
     *
     * @return
     */
    FieldType getFieldType();

    /**
     * 获取控件编码
     *
     * @return
     */
    String getCode();

    /**
     * 获取控件中文名称
     *
     * @return
     */
    String getLabel();

    /**
     * 获取控件描述
     *
     * @return
     */
    String getDescription();
}
