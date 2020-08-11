package com.dr.framework.common.form.core.command;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.core.model.FormRelationWrapper;
import com.dr.framework.common.form.core.service.SqlBuilder;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;

/**
 * 根据sql语句更新表单数据
 *
 * @author dr
 */
public class FormDataUpdateBySqlCommand extends AbstractFormDataSqlBuilderCommand<Long> {
    /**
     * 是否忽略null
     */
    private final boolean ignoreNull;

    public FormDataUpdateBySqlCommand(String formDefinitionId, boolean autoCheck, SqlBuilder sqlBuilder, boolean ignoreNull) {
        super(formDefinitionId, autoCheck, sqlBuilder);
        this.ignoreNull = ignoreNull;
    }

    public FormDataUpdateBySqlCommand(String formCode, Integer version, boolean autoCheck, SqlBuilder sqlBuilder, boolean ignoreNull) {
        super(formCode, version, autoCheck, sqlBuilder);
        this.ignoreNull = ignoreNull;
    }

    @Override
    protected Long doModifyData(CommandContext context, FormRelationWrapper wrapper) {
        CommonMapper commonMapper = context.getMapper();
        SqlQuery sqlQuery = SqlQuery.from(wrapper.getRelation());
        getSqlBuilder().buildSql(sqlQuery, wrapper);
        if (ignoreNull) {
            return commonMapper.updateIgnoreNullByQuery(sqlQuery);
        } else {
            return commonMapper.updateByQuery(sqlQuery);
        }
    }

    public boolean isIgnoreNull() {
        return ignoreNull;
    }
}
