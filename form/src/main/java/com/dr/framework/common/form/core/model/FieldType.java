package com.dr.framework.common.form.core.model;

/**
 * 字段类型
 *
 * @author dr
 */

public enum FieldType {
    /**
     * 字符串，长度超过4000自动变成clob
     */
    STRING,
    /**
     * 无精度，没有小数点数字
     */
    LONG,
    /**
     * 带有小数点数字
     */
    NUMBER,
    /**
     * 日期类型，所有日期类型数据库实际类型是long，数据库中存储毫秒
     */
    DATE,
    /**
     * 二进制，对应blob
     */
    BYTES
}
