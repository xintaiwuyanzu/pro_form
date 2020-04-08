package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormDefinitionInfo;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.FormFieldInfo;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.page.Page;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FormDefinitionSelectPageCommand extends AbstractFormDefinitionIdCommand<Page> {
    private Form form;
    private int pageIndex;
    private int pageSize;

    public FormDefinitionSelectPageCommand(Form form, int pageIndex, int pageSize) {
        this.form = form;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }


    @Override
    public Page execute(CommandContext context) {
        SqlQuery sqlQuery = SqlQuery.from(FormDefinition.class);
        if (StringUtils.isNotEmpty(form.getId())) {
            sqlQuery.equal(FormDefinitionInfo.ID, form.getId());
        }
        if (StringUtils.isNotEmpty(form.getFormCode())) {
            sqlQuery.equal(FormDefinitionInfo.FORMCODE, form.getFormCode());
        }
        if (StringUtils.isNotEmpty(form.getFormType())) {
            sqlQuery.equal(FormDefinitionInfo.FORMTYPE, form.getFormType());
        }
        if (StringUtils.isNotEmpty(form.getFormName())) {
            sqlQuery.equal(FormDefinitionInfo.FORMNAME, form.getFormName());
        }
        Page page = context.getMapper().selectPageByQuery(sqlQuery, pageIndex * pageSize, (pageIndex + 1) * pageSize);
        List<FormDefinition> list = page.getData();
        if (list.size() > 0) {
            List<FormDefinition> listForm = new ArrayList<>();
            for (FormDefinition formDefinition : list) {
                List<FormField> listFiled = context.getMapper().selectByQuery(SqlQuery.from(FormField.class).equal(FormFieldInfo.FORMDEFINITIONID, formDefinition.getId()));
                formDefinition.setFormFieldList(listFiled);
                listForm.add(formDefinition);
            }
            page.setData(listForm);
        }
        return page;
    }

}
