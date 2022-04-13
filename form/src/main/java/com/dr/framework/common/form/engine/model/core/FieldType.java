package com.dr.framework.common.form.engine.model.core;

import java.sql.Types;

/**
 * 字段类型
 *
 * @author dr
 */
public enum FieldType {


    /**
     * 字符串，长度过长会变成clob
     * 数据库类型不同，转换逻辑不同，
     * 具体转换逻辑参考
     * {@link  com.dr.framework.core.orm.database.Dialect#getColumnType(Class, int, int)}
     * <p>
     * {@link  com.dr.framework.core.orm.database.Dialect#registerClass(Class[])}
     * {@link  com.dr.framework.core.orm.database.Dialect#registerClass(int, Class[])}
     */
    STRING(Types.VARCHAR),
    /**
     * 手动声明clob类型
     */
    CLOB(Types.CLOB),
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

    final int sqlType;

    FieldType(int sqlType) {
        this.sqlType = sqlType;
    }

    /**
     * 与数据库对应
     *
     * @return @see java.sql.Types
     */
    public int getSqlType() {
        return sqlType;
    }
}
