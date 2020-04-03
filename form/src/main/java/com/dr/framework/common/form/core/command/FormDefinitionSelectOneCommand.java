package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormDefinitionInfo;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.FormFieldInfo;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.List;

public class FormDefinitionSelectOneCommand implements Command<Form> {
    private String formDefinitionId;
    private String formCode;

    public FormDefinitionSelectOneCommand(String formDefinitionId) {
        this(formDefinitionId, null);
    }

    public FormDefinitionSelectOneCommand(String formDefinitionId, String formCode) {
        this.formDefinitionId = formDefinitionId;
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
        SqlQuery<FormDefinition> sqlQuery = SqlQuery.from(FormDefinition.class);
        if (StringUtils.isNotEmpty(formDefinitionId)) {
            sqlQuery.equal(FormDefinitionInfo.ID, formDefinitionId);
        }
        if (StringUtils.isNotEmpty(formCode)) {
            sqlQuery.equal(FormDefinitionInfo.FORMCODE, formCode);
        }
        FormDefinition formDefinition = context.getMapper().selectOneByQuery(sqlQuery);
        Assert.notNull(formDefinition, "查询的表单定义不存在");
        List<FormField> listFiled = context.getMapper().selectByQuery(SqlQuery.from(FormField.class).equal(FormFieldInfo.FORMDEFINITIONID, formDefinition.getId()));
        formDefinition.setFormFieldList(listFiled);
        return formDefinition;
    }
}
