package com.dr.framework.common.form.command.method;

import com.dr.framework.common.form.command.entity.FieldDefaultValue;
import com.dr.framework.common.form.command.entity.FieldDefaultValueInfo;
import com.dr.framework.common.form.command.entity.FormDefaultValue;
import com.dr.framework.common.form.command.entity.FormDefaultValueInfo;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class FormDefaultValueRemoveCommand implements Command<Long> {
    private String formDefaultValueId;

    public FormDefaultValueRemoveCommand(String formDefaultValueId) {
        this.formDefaultValueId = formDefaultValueId;
    }

    /**
     * 删除单个环节的默认值
     *
     * @param context
     * @return
     */
    @Override
    public Long execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(formDefaultValueId), "默认值主键不能为空");
        //刪除表单下所有字段的默认值
        context.getMapper().deleteByQuery(SqlQuery.from(FieldDefaultValue.class).equal(FieldDefaultValueInfo.FORMDEFAULTVALUEID, formDefaultValueId));
        //删除表单设定的默认值
        return context.getMapper().deleteByQuery(SqlQuery.from(FormDefaultValue.class).equal(FormDefaultValueInfo.ID, formDefaultValueId));
    }

}
