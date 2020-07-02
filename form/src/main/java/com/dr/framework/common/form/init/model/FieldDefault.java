package com.dr.framework.common.form.init.model;

public interface FieldDefault {

    /**
     * 获取字段默认值
     *
     * @return
     */
    String getId();

    /**
     * 获取表单定义Id
     *
     * @return
     */
    String getFormDefaultValueId();

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
     * 获取字段类型
     *
     * @return
     */
    String getFieldType();

    /**
     * 字段默认值
     *
     * @return
     */
    String getDefaultValue();

    /**
     * 自定义
     *
     * @return
     */
    String getCustom();

}
