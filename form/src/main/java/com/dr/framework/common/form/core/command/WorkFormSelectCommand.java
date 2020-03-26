package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.command.entity.FormFieldInfo;
import com.dr.framework.common.form.command.entity.WorkFormInfo;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.WorkForm;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class WorkFormSelectCommand implements Command<List> {
    private String formId;
    private String formCode;
    private String formType;
    private String formName;

    public WorkFormSelectCommand(String formId, String formCode, String formType, String formName) {
        this.formId = formId;
        this.formCode = formCode;
        this.formType = formType;
        this.formName = formName;
    }

    /**
     * 多条件查询表单定义
     *
     * @param context
     * @return List
     */
    @Override
    public List<WorkForm> execute(CommandContext context) {
        List<WorkForm> listForm = new ArrayList<>();
        SqlQuery sqlQuery = SqlQuery.from(WorkForm.class);
        if (StringUtils.isNotEmpty(formId)) {
            sqlQuery.equal(WorkFormInfo.ID, formId);
        }
        if (StringUtils.isNotEmpty(formCode)) {
            sqlQuery.equal(WorkFormInfo.FORMCODE, formCode);
        }
        if (StringUtils.isNotEmpty(formType)) {
            sqlQuery.equal(WorkFormInfo.FORMTYPE, formType);
        }
        if (StringUtils.isNotEmpty(formName)) {
            sqlQuery.equal(WorkFormInfo.FORMNAME, formName);
        }
        //根据条件查询表单定义数据
        List<WorkForm> list = context.getMapper().selectByQuery(sqlQuery);
        if (list.size() > 0) {
            for (WorkForm workForm : list) {
                List<FormField> listFiled = context.getMapper().selectByQuery(SqlQuery.from(FormField.class).equal(FormFieldInfo.FORMID, workForm.getId()));
                workForm.setFormFieldList(listFiled);
                listForm.add(workForm);
            }
        }
        return listForm;
    }
}
