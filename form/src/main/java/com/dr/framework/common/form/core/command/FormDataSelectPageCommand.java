package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.model.FormRelationWrapper;
import com.dr.framework.common.form.core.service.SqlBuilder;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.page.Page;
import com.dr.framework.core.orm.sql.support.SqlQuery;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分页查询表单数据
 *
 * @author dr
 */
public class FormDataSelectPageCommand extends AbstractFormDataSqlBuilderCommand<Page<FormData>> {

    private final int pageIndex;
    private final int pageSize;

    public FormDataSelectPageCommand(String formDefinitionId, boolean autoCheck, SqlBuilder sqlBuilder, int pageIndex, int pageSize) {
        super(formDefinitionId, autoCheck, sqlBuilder);
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public FormDataSelectPageCommand(String formCode, Integer version, boolean autoCheck, SqlBuilder sqlBuilder, int pageIndex, int pageSize) {
        super(formCode, version, autoCheck, sqlBuilder);
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    @Override
    protected Page<FormData> doModifyData(CommandContext context, FormRelationWrapper wrapper) {
        SqlQuery<HashMap<String, Serializable>> sqlQuery = SqlQuery.from(wrapper.getRelation());
        sqlQuery.setReturnClass(HashMap.class);
        getSqlBuilder().buildSql(sqlQuery, wrapper);
        long count = context.getMapper().countByIdWithQuery(sqlQuery);
        int start = pageIndex * pageSize;
        int end = (pageIndex + 1) * pageSize;
        List<HashMap<String, Serializable>> data =
                context.getMapper().selectLimitByQuery(sqlQuery, start, end);
        return new Page<>(
                start,
                pageSize,
                count,
                data.stream()
                        .map(d -> mapFormData(wrapper, d))
                        .collect(Collectors.toList())
        );
    }

}
