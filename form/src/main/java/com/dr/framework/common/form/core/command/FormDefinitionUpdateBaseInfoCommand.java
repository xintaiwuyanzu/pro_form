package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormDefinitionInfo;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 更新表单基本信息
 *
 * @author dr
 */
public class FormDefinitionUpdateBaseInfoCommand extends AbstractFormDefinitionIdCommand implements Command<FormDefinition> {

    private Form form;

    public FormDefinitionUpdateBaseInfoCommand(Form form) {
        super(null);
        Assert.isTrue(form != null, "表单对象不能为空！");
        setFormDefinitionId(form.getId());
        setFormCode(form.getFormCode());
        setVersion(form.getVersion());
        this.form = form;
    }

    @Override
    public FormDefinition execute(CommandContext context) {
        FormDefinition formDefinition = getFormDefinition(context);
        Assert.isTrue(formDefinition != null, "未查询到指定的表单定义！");
        SqlQuery sqlQuery = SqlQuery.from(FormDefinition.class).equal(FormDefinitionInfo.ID, formDefinition.getId());
        if (!StringUtils.isEmpty(form.getFormName())) {
            sqlQuery.set(FormDefinitionInfo.FORMNAME, form.getFormName());
        }
        if (!StringUtils.isEmpty(form.getFormType())) {
            sqlQuery.set(FormDefinitionInfo.FORMTYPE, form.getFormType());
        }
        if (!StringUtils.isEmpty(form.getDescription())) {
            sqlQuery.set(FormDefinitionInfo.DESCRIPTION, form.getDescription());
        }
        if (!StringUtils.isEmpty(form.getDataObjectId())) {
            sqlQuery.set(FormDefinitionInfo.DATAOBJECTID, form.getDataObjectId());
        }
        if (form.getFormOrder() != null) {
            sqlQuery.set(FormDefinitionInfo.ORDERBY, form.getFormOrder());
        }
        context.getMapper().updateIgnoreNullByQuery(sqlQuery);
        //更新成功后更新缓存
        if (!StringUtils.isEmpty(form.getFormName())) {
            formDefinition.setFormName(form.getFormName());
        }
        if (!StringUtils.isEmpty(form.getFormType())) {
            formDefinition.setFormType(form.getFormType());
        }
        if (!StringUtils.isEmpty(form.getDescription())) {
            formDefinition.setDescription(form.getDescription());
        }
        if (!StringUtils.isEmpty(form.getDataObjectId())) {
            formDefinition.setDataObjectId(form.getDataObjectId());
        }
        if (form.getFormOrder() != null) {
            formDefinition.setOrder(form.getFormOrder());
        }
        return formDefinition;
    }

    public Form getForm() {
        return form;
    }
}
