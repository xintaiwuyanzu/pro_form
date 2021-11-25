package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.model.FormRelationWrapper;
import com.dr.framework.common.form.core.service.SqlBuilder;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;

import java.util.List;

/**
 * 根据自定义参数和类型查询group by 的统计
 *
 * @author dr
 */
public class FormDataCountSelfBySqlCommand<T> extends AbstractFormDataSqlBuilderCommand<List<T>> {
    public FormDataCountSelfBySqlCommand(String formDefinitionId, boolean autoCheck, SqlBuilder sqlBuilder) {
        super(formDefinitionId, autoCheck, sqlBuilder);
    }

    public FormDataCountSelfBySqlCommand(String formCode, Integer version, boolean autoCheck, SqlBuilder sqlBuilder) {
        super(formCode, version, autoCheck, sqlBuilder);
    }

    @Override
    protected List<T> doModifyData(CommandContext context, FormRelationWrapper wrapper) {
        SqlQuery<T> sqlQuery = SqlQuery.from(wrapper.getRelation(), false);
        getSqlBuilder().buildSql(sqlQuery, wrapper);
        return context.getMapper().selectByQuery(sqlQuery);
    }
}
