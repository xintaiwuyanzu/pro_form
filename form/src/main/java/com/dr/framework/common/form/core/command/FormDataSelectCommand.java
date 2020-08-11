package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.model.FormRelationWrapper;
import com.dr.framework.common.form.core.service.SqlBuilder;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 表单查询语句
 *
 * @author dr
 */
public class FormDataSelectCommand extends AbstractFormDataSqlBuilderCommand<List<FormData>> {
    /**
     * 是否查询所有的列
     */
    private final boolean allColumn;

    public FormDataSelectCommand(String formDefinitionId, boolean autoCheck, SqlBuilder sqlBuilder, boolean allColumn) {
        super(formDefinitionId, autoCheck, sqlBuilder);
        this.allColumn = allColumn;
    }

    public FormDataSelectCommand(String formCode, Integer version, boolean autoCheck, SqlBuilder sqlBuilder, boolean allColumn) {
        super(formCode, version, autoCheck, sqlBuilder);
        this.allColumn = allColumn;
    }

    @Override
    protected List<FormData> doModifyData(CommandContext context, FormRelationWrapper wrapper) {
        SqlQuery<HashMap<String, Serializable>> sqlQuery = SqlQuery.from(wrapper.getRelation(), allColumn);
        sqlQuery.setReturnClass(HashMap.class);
        getSqlBuilder().buildSql(sqlQuery, wrapper);
        return context.getMapper().selectByQuery(sqlQuery)
                .stream()
                .map(d -> mapFormData(wrapper, d))
                .collect(Collectors.toList());
    }

    public boolean isAllColumn() {
        return allColumn;
    }
}
