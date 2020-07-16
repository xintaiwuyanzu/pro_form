package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormDefinitionInfo;
import com.dr.framework.common.form.core.query.FormDefinitionQuery;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.CacheUtil;
import com.dr.framework.common.page.Page;
import com.dr.framework.core.orm.sql.support.SqlQuery;

import java.util.stream.Collectors;

/**
 * 根据query查询表单定义
 *
 * @author dr
 */
public class FormDefinitionSelectPageCommand extends AbstractFormDefinitionQueryCommand<Page<FormDefinition>> {
    private int pageIndex;
    private int pageSize;

    public FormDefinitionSelectPageCommand(FormDefinitionQuery query, int pageIndex, int pageSize) {
        super(query);
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    @Override
    public Page<FormDefinition> execute(CommandContext context) {
        SqlQuery<FormDefinition> sqlQuery = SqlQuery.from(FormDefinition.class, false).column(FormDefinitionInfo.ID);
        buildSqlQuery(context, sqlQuery);
        Page<FormDefinition> page = context.getMapper().selectPageByQuery(sqlQuery, pageIndex * pageSize, (pageIndex + 1) * pageSize);

        page.setData(
                page.getData()
                        .stream()
                        .map(f -> CacheUtil.getFormDefinitionFromCache(context, f.getId()))
                        .collect(Collectors.toList())
        );
        return page;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }
}
