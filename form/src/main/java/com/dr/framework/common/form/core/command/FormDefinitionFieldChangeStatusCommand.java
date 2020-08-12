package com.dr.framework.common.form.core.command;


import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.FormFieldInfo;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.CacheUtil;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 删除表单定义字段
 *
 * @author dr
 */
public class FormDefinitionFieldChangeStatusCommand extends FormDefinitionFieldSelectOneCommand {
    /**
     * 状态
     */
    private final String status;

    public FormDefinitionFieldChangeStatusCommand(String formDefinitionId, String fieldCode, String status) {
        super(formDefinitionId, fieldCode);
        this.status = status;
    }

    public FormDefinitionFieldChangeStatusCommand(String formCode, Integer version, String fieldCode, String status) {
        super(formCode, version, fieldCode);
        this.status = status;
    }

    /**
     * 删除表单数据，表单定义
     *
     * @param context
     * @return long
     */
    @Override
    public FormField execute(CommandContext context) {
        Assert.isTrue(!StringUtils.isEmpty(status), "状态类型不能为空！");
        Assert.isTrue(validateStatus(status), "字段状态格式不正确");

        FormField formField = null;
        formField = super.execute(context);
        if (formField != null && !status.equalsIgnoreCase(formField.getStatus())) {
            context.getMapper().updateIgnoreNullByQuery(
                    SqlQuery.from(FormField.class)
                            .set(FormFieldInfo.STATUS, status)
                            .equal(FormFieldInfo.ID, formField.getId())
            );
            CacheUtil.removeFormDefinitionCache(context, formField.getFormDefinitionId());
        } else {
            FormDefinition formDefinition = getFormDefinition(context);
            context.getMapper().updateIgnoreNullByQuery(
                    SqlQuery.from(FormField.class)
                            .set(FormFieldInfo.STATUS, status)
                            .equal(FormFieldInfo.FORMDEFINITIONID, formDefinition.getId())
                            .equal(FormFieldInfo.FIELDCODE, getFieldCode()));
            CacheUtil.removeFormDefinitionCache(context, formDefinition.getId());
        }
        return formField;
    }

    public String getStatus() {
        return status;
    }
}
