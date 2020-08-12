package com.dr.framework.common.form.display.command;

import com.dr.framework.common.form.display.entity.FieldDisplayScheme;
import com.dr.framework.common.form.display.entity.FieldDisplaySchemeInfo;
import com.dr.framework.common.form.display.entity.FormDisplayScheme;
import com.dr.framework.common.form.display.entity.FormDisplaySchemeInfo;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.List;

public class FormDisplayFieldSelectOneCommand implements Command<FormDisplayScheme> {
    private final String formDefinitionId;
    private final String formDisplayId;
    private final String schemeCode;

    public FormDisplayFieldSelectOneCommand(String formDefinitionId, String formDisplayId, String schemeCode) {
        this.formDefinitionId = formDefinitionId;
        this.formDisplayId = formDisplayId;
        this.schemeCode = schemeCode;
    }

    /**
     * 查询显示方案对象
     *
     * @param context
     * @return FormDisplayScheme
     */
    @Override
    public FormDisplayScheme execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(formDisplayId) || StringUtils.isNotEmpty(schemeCode), "显示方案主鍵和编码不能全为空");
        SqlQuery<FormDisplayScheme> sqlQuery = SqlQuery.from(FormDisplayScheme.class);
        if (StringUtils.isNotEmpty(formDefinitionId)) {
            sqlQuery.equal(FormDisplaySchemeInfo.FORMDEFINITIONID, formDefinitionId);
        }
        if (StringUtils.isNotEmpty(formDisplayId)) {
            sqlQuery.equal(FormDisplaySchemeInfo.ID, formDisplayId);
        }
        if (StringUtils.isNotEmpty(schemeCode)) {
            sqlQuery.equal(FormDisplaySchemeInfo.SCHEMECODE, schemeCode);
        }
        FormDisplayScheme formDisplayScheme = context.getMapper().selectOneByQuery(sqlQuery);
        Assert.notNull(formDisplayScheme, "查询的显示方案不存在");
        List<FieldDisplayScheme> fieldDisplayList = context.getMapper().selectByQuery(SqlQuery.from(FieldDisplayScheme.class).equal(FieldDisplaySchemeInfo.FORMDISPLAYID, formDisplayScheme.getId()));
        formDisplayScheme.setFields(fieldDisplayList);
        return formDisplayScheme;
    }

}
