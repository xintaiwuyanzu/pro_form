package com.dr.framework.common.form.core.model;

import java.sql.Types;

/**
 * 字段类型
 *
 * @author dr
 */

public enum FieldType {
    /**
     * 字符串，长度超过4000自动变成clob
     */
    STRING(Types.VARCHAR),
    /**
     * 无精度，没有小数点数字
     */
    LONG(Types.BIGINT),
    /**
     * 带有小数点数字
     */
    NUMBER(Types.FLOAT),
    /**
     * 日期类型，所有日期类型数据库实际类型是long，数据库中存储毫秒
     */
    DATE(Types.BIGINT),
    /**
     * 二进制，对应blob
     */
    BYTES(Types.BLOB);

    int sqlType;

    FieldType(int sqlType) {
        this.sqlType = sqlType;
    }

    public int getSqlType() {
        return sqlType;
    }
}
