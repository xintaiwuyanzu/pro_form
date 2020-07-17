package com.dr.framework.common.form.init.command;

import com.dr.framework.common.form.core.command.AbstractFormDefinitionIdCommand;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.init.entity.FieldDefaultValue;
import com.dr.framework.common.form.init.entity.FieldDefaultValueInfo;
import com.dr.framework.common.form.init.entity.FormDefaultValue;
import com.dr.framework.common.form.init.entity.FormDefaultValueInfo;
import com.dr.framework.common.page.Page;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class FormDefaultSelectPageCommand extends AbstractFormDefinitionIdCommand implements Command<Page> {
    private int pageIndex;
    private int pageSize;


    public FormDefaultSelectPageCommand(String formDefinitionId, int pageIndex, int pageSize) {
        super(formDefinitionId);
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public FormDefaultSelectPageCommand(Integer version, String formDefinitionId, int pageIndex, int pageSize) {
        super(formDefinitionId, version);
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    @Override
    public Page execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(getFormDefinitionId()), "表单定义id不能为空");
        SqlQuery sqlQuery = SqlQuery.from(FormDefaultValue.class).equal(FormDefaultValueInfo.FORMDEFINITIONID, getFormDefinitionId());
        Page page = context.getMapper().selectPageByQuery(sqlQuery, pageIndex * pageSize, (pageIndex + 1) * pageSize);
        List<FormDefaultValue> list = page.getData();
        if (list.size() > 0) {
            List<FormDefaultValue> listFormDefaultValue = new ArrayList<>();
            for (FormDefaultValue formDefaultValue : list) {
                List<FieldDefaultValue> listFiledDefault = context.getMapper().selectByQuery(SqlQuery.from(FieldDefaultValue.class).equal(FieldDefaultValueInfo.FORMDEFAULTVALUEID, formDefaultValue.getId()));
                formDefaultValue.setFieldDefaultList(listFiledDefault);
                listFormDefaultValue.add(formDefaultValue);
            }
            page.setData(listFormDefaultValue);
        }
        return page;
    }

}
