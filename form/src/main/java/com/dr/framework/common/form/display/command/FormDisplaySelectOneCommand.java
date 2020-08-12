package com.dr.framework.common.form.display.command;

import com.dr.framework.common.form.display.entity.FormDisplayScheme;
import com.dr.framework.common.form.display.entity.FormDisplaySchemeInfo;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 根据显示方案id查询单个显示方案
 *
 * @author dr
 */
public class FormDisplaySelectOneCommand extends AbstractFormDisplayQueryCommand implements Command<FormDisplayScheme> {
    private String displayCode;

    public FormDisplaySelectOneCommand(String formDefinitionId, String displayType, String displayCode) {
        super(formDefinitionId, displayType);
        this.displayCode = displayCode;
    }

    public FormDisplaySelectOneCommand(String formDefinitionCode, Integer version, String displayType, String displayCode) {
        super(formDefinitionCode, version, displayType);
        this.displayCode = displayCode;
    }

    @Override
    public FormDisplayScheme execute(CommandContext context) {
        return selectOne(context);
    }

    @Override
    protected SqlQuery<FormDisplayScheme> buildSqlQuery(CommandContext context) {
        Assert.isTrue(!StringUtils.isEmpty(displayCode), "显示编码不能为空");
        return super.buildSqlQuery(context)
                .equal(FormDisplaySchemeInfo.CODE, displayCode);
    }
}
