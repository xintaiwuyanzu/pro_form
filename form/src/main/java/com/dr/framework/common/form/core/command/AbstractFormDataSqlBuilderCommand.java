package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.service.SqlBuilder;
import com.dr.framework.common.form.engine.Command;

/**
 * 使用sqlbuild的命令，
 * 用来操作数据
 *
 * @author dr
 */
public abstract class AbstractFormDataSqlBuilderCommand<T> extends AbstractFormDataCommand<T> implements Command<T> {
    /**
     * sql语句构造器
     */
    private final SqlBuilder sqlBuilder;

    public AbstractFormDataSqlBuilderCommand(String formDefinitionId, boolean autoCheck, SqlBuilder sqlBuilder) {
        super(formDefinitionId, autoCheck);
        this.sqlBuilder = sqlBuilder;
    }

    public AbstractFormDataSqlBuilderCommand(String formCode, Integer version, boolean autoCheck, SqlBuilder sqlBuilder) {
        super(formCode, version, autoCheck);
        this.sqlBuilder = sqlBuilder;
    }

    public SqlBuilder getSqlBuilder() {
        return sqlBuilder;
    }

}
