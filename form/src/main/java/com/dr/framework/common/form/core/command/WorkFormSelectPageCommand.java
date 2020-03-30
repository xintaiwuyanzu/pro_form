package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.command.entity.FormFieldInfo;
import com.dr.framework.common.form.command.entity.WorkFormInfo;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.WorkForm;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.page.Page;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class WorkFormSelectPageCommand implements Command<Page> {
    private Form form;
    private int pageIndex;
    private int pageSize;

    public WorkFormSelectPageCommand(Form form, int pageIndex, int pageSize) {
        this.form = form;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }


    @Override
    public Page execute(CommandContext context) {
        SqlQuery sqlQuery = SqlQuery.from(WorkForm.class);
        if (StringUtils.isNotEmpty(form.getId())) {
            sqlQuery.equal(WorkFormInfo.ID, form.getId());
        }
        if (StringUtils.isNotEmpty(form.getFormCode())) {
            sqlQuery.equal(WorkFormInfo.FORMCODE, form.getFormCode());
        }
        if (StringUtils.isNotEmpty(form.getFormType())) {
            sqlQuery.equal(WorkFormInfo.FORMTYPE, form.getFormType());
        }
        if (StringUtils.isNotEmpty(form.getFormName())) {
            sqlQuery.equal(WorkFormInfo.FORMNAME, form.getFormName());
        }
        Page page = context.getMapper().selectPageByQuery(sqlQuery, pageIndex * pageSize, (pageIndex + 1) * pageSize);
        List<WorkForm> list = page.getData();
        if (list.size() > 0) {
            List<WorkForm> listForm = new ArrayList<>();
            for (WorkForm workForm : list) {
                List<FormField> listFiled = context.getMapper().selectByQuery(SqlQuery.from(FormField.class).equal(FormFieldInfo.FORMID, workForm.getId()));
                workForm.setFormFieldList(listFiled);
                listForm.add(workForm);
            }
            page.setData(listForm);
        }
        return page;
    }

}
