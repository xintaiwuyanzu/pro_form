package com.dr.framework.common.form.core.service;

import com.dr.framework.common.form.core.model.FormRelationWrapper;
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
     * @param formRelationWrapper 表单表结构对象
     */
    void buildSql(SqlQuery sqlQuery, FormRelationWrapper formRelationWrapper);
}
