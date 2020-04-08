package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormDefinitionInfo;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.FormFieldInfo;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FormDefinitionSelectCommand extends AbstractFormDefinitionIdCommand<List> {
    private String formCode;
    private String formType;
    private String formName;

    public FormDefinitionSelectCommand(String formDefinitionId, String formCode, String formType, String formName) {
        super(formDefinitionId);
        this.formCode = formCode;
        this.formType = formType;
        this.formName = formName;
    }

    public FormDefinitionSelectCommand(String version, String formDefinitionId, String formCode, String formType, String formName) {
        super(version, formDefinitionId);
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
    public List<FormDefinition> execute(CommandContext context) {
        List<FormDefinition> listForm = new ArrayList<>();
        SqlQuery sqlQuery = SqlQuery.from(FormDefinition.class);
        if (StringUtils.isNotEmpty(getFormDefinitionId())) {
            sqlQuery.equal(FormDefinitionInfo.ID, getFormDefinitionId());
        }
        if (StringUtils.isNotEmpty(formCode)) {
            sqlQuery.equal(FormDefinitionInfo.FORMCODE, formCode);
        }
        if (StringUtils.isNotEmpty(formType)) {
            sqlQuery.equal(FormDefinitionInfo.FORMTYPE, formType);
        }
        if (StringUtils.isNotEmpty(formName)) {
            sqlQuery.equal(FormDefinitionInfo.FORMNAME, formName);
        }
        //根据条件查询表单定义数据
        List<FormDefinition> list = context.getMapper().selectByQuery(sqlQuery);
        if (list.size() > 0) {
            for (FormDefinition formDefinition : list) {
                List<FormField> listFiled = context.getMapper().selectByQuery(SqlQuery.from(FormField.class).equal(FormFieldInfo.FORMDEFINITIONID, formDefinition.getId()));
                formDefinition.setFormFieldList(listFiled);
                listForm.add(formDefinition);
            }
        }
        return listForm;
    }

    public String getFormCode() {
        return formCode;
    }

    public String getFormType() {
        return formType;
    }

    public String getFormName() {
        return formName;
    }
}
