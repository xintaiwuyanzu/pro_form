package com.dr.framework.common.form.command.method;

import com.dr.framework.common.form.command.entity.FieldDisplayScheme;
import com.dr.framework.common.form.command.entity.FieldDisplaySchemeInfo;
import com.dr.framework.common.form.command.entity.FormDisplayScheme;
import com.dr.framework.common.form.command.entity.FormDisplaySchemeInfo;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class FormDisplayRemoveCommand implements Command<Long> {

    private String formDisplayId;

    public void FormDisplayRemoveCommand(String formDisplayId) {
        this.formDisplayId = formDisplayId;
    }

    /**
     * 删除显示方案
     *
     * @param context
     * @return long
     */
    @Override
    public Long execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(formDisplayId), "显示方案主键不能为空");
        //根据显示方案主键删除各字段显示方案设定的值
        context.getMapper().deleteByQuery(SqlQuery.from(FieldDisplayScheme.class).equal(FieldDisplaySchemeInfo.FORMDISPLAYID, formDisplayId));
        //然后删除主表信息
        return context.getMapper().deleteByQuery(SqlQuery.from(FormDisplayScheme.class).equal(FormDisplaySchemeInfo.ID, formDisplayId));
    }
}
