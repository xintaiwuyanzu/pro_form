package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.model.FormRelationWrapper;
import com.dr.framework.common.form.core.service.SqlBuilder;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;

/**
 * 根据条件count id 命令实现
 *
 * @author dr
 */
public class FormDataCountIdBySqlCommand extends AbstractFormDataSqlBuilderCommand<Long> {
    public FormDataCountIdBySqlCommand(String formDefinitionId, boolean autoCheck, SqlBuilder sqlBuilder) {
        super(formDefinitionId, autoCheck, sqlBuilder);
    }

    public FormDataCountIdBySqlCommand(String formCode, Integer version, boolean autoCheck, SqlBuilder sqlBuilder) {
        super(formCode, version, autoCheck, sqlBuilder);
    }

    @Override
    protected Long doModifyData(CommandContext context, FormRelationWrapper wrapper) {
        SqlQuery<Long> sqlQuery = SqlQuery.from(wrapper.getRelation(), false)
                .column(wrapper.idColumn().count())
                .setReturnClass(Long.class);
        getSqlBuilder().buildSql(sqlQuery, wrapper);
        return context.getMapper().countByQuery(sqlQuery);
    }
}
