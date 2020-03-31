package com.dr.framework.common.form.validate.command;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionForm;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionFormInfo;
import com.dr.framework.common.form.validate.model.ValidateDefinition;
import com.dr.framework.common.page.Page;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class ValidateDefinitionSelectPageCommand implements Command<Page<ValidateDefinition>> {

    private String formDefinitionId;
    private int pageIndex;
    private int pageSize;

    public ValidateDefinitionSelectPageCommand(String formDefinitionId, int pageIndex, int pageSize) {
        this.formDefinitionId = formDefinitionId;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    @Override
    public Page<ValidateDefinition> execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(formDefinitionId), "表单定义id不能为空");
        SqlQuery sqlQuery = SqlQuery.from(ValidateDefinitionForm.class).equal(ValidateDefinitionFormInfo.FORMDEFINITIONID, formDefinitionId);
        Page page = context.getMapper().selectPageByQuery(sqlQuery, pageIndex * pageSize, (pageIndex + 1) * pageSize);
        return page;
    }

}
