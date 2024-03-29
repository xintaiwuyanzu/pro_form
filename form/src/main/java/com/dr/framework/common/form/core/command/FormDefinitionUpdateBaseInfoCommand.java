package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormDefinitionInfo;
import com.dr.framework.common.form.engine.model.core.FormModel;
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

    private final FormModel formModel;

    public FormDefinitionUpdateBaseInfoCommand(FormModel formModel) {
        super(null);
        Assert.isTrue(formModel != null, FORM_CAN_NOT_BE_NULL_ERROR);
        setFormDefinitionId(formModel.getId());
        setFormCode(formModel.getFormCode());
        setVersion(formModel.getVersion());
        this.formModel = formModel;
    }

    @Override
    public FormDefinition execute(CommandContext context) {
        FormDefinition formDefinition = getFormDefinition(context);
        Assert.isTrue(formDefinition != null, FORM_CAN_NOT_BE_NULL_ERROR);
        SqlQuery sqlQuery = SqlQuery.from(FormDefinition.class).equal(FormDefinitionInfo.ID, formDefinition.getId());
        if (!StringUtils.isEmpty(formModel.getFormName())) {
            sqlQuery.set(FormDefinitionInfo.FORMNAME, formModel.getFormName());
        }
        if (!StringUtils.isEmpty(formModel.getFormType())) {
            sqlQuery.set(FormDefinitionInfo.FORMTYPE, formModel.getFormType());
        }
        if (!StringUtils.isEmpty(formModel.getDescription())) {
            sqlQuery.set(FormDefinitionInfo.DESCRIPTION, formModel.getDescription());
        }
        if (!StringUtils.isEmpty(formModel.getDataObjectId())) {
            sqlQuery.set(FormDefinitionInfo.DATAOBJECTID, formModel.getDataObjectId());
        }
        if (formModel.getFormOrder() != null) {
            sqlQuery.set(FormDefinitionInfo.ORDERBY, formModel.getFormOrder());
        }
        if (!StringUtils.isEmpty(formModel.getExt1())) {
            sqlQuery.set(FormDefinitionInfo.EXT1, formModel.getExt1());
        }
        if (!StringUtils.isEmpty(formModel.getExt2())) {
            sqlQuery.set(FormDefinitionInfo.EXT2, formModel.getExt2());
        }
        if (!StringUtils.isEmpty(formModel.getExt3())) {
            sqlQuery.set(FormDefinitionInfo.EXT3, formModel.getExt3());
        }
        if (!StringUtils.isEmpty(formModel.getExt4())) {
            sqlQuery.set(FormDefinitionInfo.EXT4, formModel.getExt4());
        }
        if (!StringUtils.isEmpty(formModel.getExt5())) {
            sqlQuery.set(FormDefinitionInfo.EXT5, formModel.getExt5());
        }
        context.getMapper().updateIgnoreNullByQuery(sqlQuery);
        //更新成功后更新缓存
        if (!StringUtils.isEmpty(formModel.getFormName())) {
            formDefinition.setFormName(formModel.getFormName());
        }
        if (!StringUtils.isEmpty(formModel.getFormType())) {
            formDefinition.setFormType(formModel.getFormType());
        }
        if (!StringUtils.isEmpty(formModel.getDescription())) {
            formDefinition.setDescription(formModel.getDescription());
        }
        if (!StringUtils.isEmpty(formModel.getDataObjectId())) {
            formDefinition.setDataObjectId(formModel.getDataObjectId());
        }
        if (formModel.getFormOrder() != null) {
            formDefinition.setOrder(formModel.getFormOrder());
        }
        if (!StringUtils.isEmpty(formModel.getExt1())) {
            formDefinition.setExt1(formModel.getExt1());
        }
        if (!StringUtils.isEmpty(formModel.getExt2())) {
            formDefinition.setExt2(formModel.getExt2());
        }
        if (!StringUtils.isEmpty(formModel.getExt3())) {
            formDefinition.setExt3(formModel.getExt3());
        }
        if (!StringUtils.isEmpty(formModel.getExt4())) {
            formDefinition.setExt4(formModel.getExt4());
        }
        if (!StringUtils.isEmpty(formModel.getExt5())) {
            formDefinition.setExt5(formModel.getExt5());
        }
        return formDefinition;
    }

    public FormModel getForm() {
        return formModel;
    }
}
