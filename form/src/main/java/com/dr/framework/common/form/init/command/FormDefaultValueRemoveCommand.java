package com.dr.framework.common.form.init.command;

import com.dr.framework.common.form.core.command.AbstractFormDefinitionIdCommand;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.init.entity.FieldDefaultValue;
import com.dr.framework.common.form.init.entity.FieldDefaultValueInfo;
import com.dr.framework.common.form.init.entity.FormDefaultValue;
import com.dr.framework.common.form.init.entity.FormDefaultValueInfo;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class FormDefaultValueRemoveCommand extends AbstractFormDefinitionIdCommand<Long> {
    private String formDefaultValueId;

    public FormDefaultValueRemoveCommand(String formDefaultValueId) {
        super(null);
        this.formDefaultValueId = formDefaultValueId;
    }

    public FormDefaultValueRemoveCommand(String version, String formDefaultValueId) {
        super(version, null);
        this.formDefaultValueId = formDefaultValueId;
    }

    public FormDefaultValueRemoveCommand(Integer version, String formDefinitionId, String formDefaultValueId) {
        super(formDefinitionId, version);
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
