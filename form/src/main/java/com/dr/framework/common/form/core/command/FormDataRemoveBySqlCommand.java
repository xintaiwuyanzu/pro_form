package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.model.FormRelationWrapper;
import com.dr.framework.common.form.core.service.SqlBuilder;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;

/**
 * 根据sql语句删除表单数据
 *
 * @author dr
 */
public class FormDataRemoveBySqlCommand extends AbstractFormDataSqlBuilderCommand<Long> {

    public FormDataRemoveBySqlCommand(String formDefinitionId, boolean autoCheck, SqlBuilder sqlBuilder) {
        super(formDefinitionId, autoCheck, sqlBuilder);
    }

    public FormDataRemoveBySqlCommand(String formCode, Integer version, boolean autoCheck, SqlBuilder sqlBuilder) {
        super(formCode, version, autoCheck, sqlBuilder);
    }

    @Override
    protected Long doModifyData(CommandContext context, FormRelationWrapper wrapper) {
        SqlQuery sqlQuery = SqlQuery.from(wrapper.getRelation());
        getSqlBuilder().buildSql(sqlQuery, wrapper);
        return context.getMapper()
                .deleteByQuery(sqlQuery);
    }
}
