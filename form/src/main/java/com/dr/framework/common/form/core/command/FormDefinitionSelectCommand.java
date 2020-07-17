package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormDefinitionInfo;
import com.dr.framework.common.form.core.query.FormDefinitionQuery;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.CacheUtil;
import com.dr.framework.core.orm.sql.support.SqlQuery;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 根据条件查询表单定义，列表
 *
 * @author dr
 */
public class FormDefinitionSelectCommand extends AbstractFormDefinitionQueryCommand implements Command<List<FormDefinition>> {

    public FormDefinitionSelectCommand(FormDefinitionQuery query) {
        super(query);
    }

    /**
     * 多条件查询表单定义
     *
     * @param context
     * @return List
     */
    @Override
    public List<FormDefinition> execute(CommandContext context) {
        SqlQuery<FormDefinition> sqlQuery = SqlQuery.from(FormDefinition.class, false).column(FormDefinitionInfo.ID);
        buildSqlQuery(context, sqlQuery);
        //根据条件查询表单定义数据
        return context.getMapper().selectByQuery(sqlQuery)
                .stream()
                .map(f -> CacheUtil.getFormDefinitionFromCache(context, f.getId()))
                .collect(Collectors.toList());
    }

}
