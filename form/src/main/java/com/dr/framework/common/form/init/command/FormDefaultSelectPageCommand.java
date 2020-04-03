package com.dr.framework.common.form.init.command;

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

public class FormDefaultSelectPageCommand implements Command<Page> {

    private String formDefinitionId;
    private int pageIndex;
    private int pageSize;

    public FormDefaultSelectPageCommand(String formDefinitionId, int pageIndex, int pageSize) {
        this.formDefinitionId = formDefinitionId;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    @Override
    public Page execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(formDefinitionId), "表单定义id不能为空");
        SqlQuery sqlQuery = SqlQuery.from(FormDefaultValue.class).equal(FormDefaultValueInfo.FORMDEFINITIONID, formDefinitionId);
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
