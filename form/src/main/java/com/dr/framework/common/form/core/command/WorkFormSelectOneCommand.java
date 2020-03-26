package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.command.entity.FormFieldInfo;
import com.dr.framework.common.form.command.entity.WorkFormInfo;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.WorkForm;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.List;

public class WorkFormSelectOneCommand implements Command<Form> {
    private String formId;
    private String formCode;

    public WorkFormSelectOneCommand(String formId, String formCode) {
        this.formId = formId;
        this.formCode = formCode;
    }

    /**
     * 查询表单定义对象
     *
     * @param context
     * @return workForm
     */
    @Override
    public Form execute(CommandContext context) {
        SqlQuery<WorkForm> sqlQuery = SqlQuery.from(WorkForm.class);
        if (StringUtils.isNotEmpty(formId)) {
            sqlQuery.equal(WorkFormInfo.ID, formId);
        }
        if (StringUtils.isNotEmpty(formCode)) {
            sqlQuery.equal(WorkFormInfo.FORMCODE, formCode);
        }
        WorkForm workForm = context.getMapper().selectOneByQuery(sqlQuery);
        Assert.notNull(workForm, "查询的表单定义不存在");
        List<FormField> listFiled = context.getMapper().selectByQuery(SqlQuery.from(FormField.class).equal(FormFieldInfo.FORMID, workForm.getId()));
        workForm.setFormFieldList(listFiled);
        return workForm;
    }
}
