package com.dr.framework.common.form.display.model;

import com.dr.framework.common.form.core.model.FieldType;

/**
 * 表单显示控件抽象接口，
 * 用来定义扩展控件属性，需要前后台同时实现
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
