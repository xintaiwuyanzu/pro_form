package com.dr.framework.common.form.core.service;

import com.dr.framework.core.orm.jdbc.Relation;
import com.dr.framework.core.orm.sql.support.SqlQuery;

/**
 * 用来拼写sql查询条件
 *
 * @author lc
 */
@FunctionalInterface
public interface SqlBuilder {
    /**
     * 构建sql语句
     *
     * @param sqlQuery
     * @param relation
     */
    void buildSql(SqlQuery sqlQuery, Relation relation);
}
