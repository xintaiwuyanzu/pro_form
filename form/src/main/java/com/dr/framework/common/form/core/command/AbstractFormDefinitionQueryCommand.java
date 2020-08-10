package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormDefinitionInfo;
import com.dr.framework.common.form.core.query.FormDefinitionQuery;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 抽象query表单定义查询类
 *
 * @author dr
 */
public abstract class AbstractFormDefinitionQueryCommand extends AbstractFormDefinitionCommand {
    private FormDefinitionQuery query;

    public AbstractFormDefinitionQueryCommand(FormDefinitionQuery query) {
        this.query = query;
    }

    /**
     * 构造查询条件
     *
     * @param context
     * @param sqlQuery
     */
    protected void buildSqlQuery(CommandContext context, SqlQuery<FormDefinition> sqlQuery) {
        Assert.notNull(query, "查询条件不能为空!");
        if (!StringUtils.isEmpty(query.getCodeLike())) {
            sqlQuery.like(FormDefinitionInfo.FORMCODE, query.getCodeLike());
        }
        if (!StringUtils.isEmpty(query.getCodeEqual())) {
            sqlQuery.equal(FormDefinitionInfo.FORMCODE, query.getCodeEqual());
        }
        if (!StringUtils.isEmpty(query.getName())) {
            sqlQuery.like(FormDefinitionInfo.FORMNAME, query.getName());
        }
        if (!StringUtils.isEmpty(query.getTypeLike())) {
            sqlQuery.like(FormDefinitionInfo.FORMTYPE, query.getTypeLike());
        }
        if (!StringUtils.isEmpty(query.getTypeEqual())) {
            sqlQuery.equal(FormDefinitionInfo.FORMTYPE, query.getTypeEqual());
        }
        if (!StringUtils.isEmpty(query.getStatus())) {
            sqlQuery.equal(FormDefinitionInfo.STATUS, query.getStatus());
        }
        if (query.getVersion() == null) {
            if (!query.isVersionAll()) {
                sqlQuery.equal(FormDefinitionInfo.ISDEFAULT, true);
            }
        } else {
            sqlQuery.equal(FormDefinitionInfo.VERSION, query.getVersion());
        }
    }

    public FormDefinitionQuery getQuery() {
        return query;
    }
}

