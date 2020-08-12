package com.dr.framework.common.form.core.command;


import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.CacheUtil;

/**
 * 删除表单定义字段
 *
 * @author dr
 */
public class FormDefinitionFieldRemoveCommand extends FormDefinitionFieldSelectOneCommand {

    public FormDefinitionFieldRemoveCommand(String formDefinitionId, String fieldCode) {
        super(formDefinitionId, fieldCode);
    }

    public FormDefinitionFieldRemoveCommand(String formCode, Integer version, String fieldCode) {
        super(formCode, version, fieldCode);
    }

    /**
     * 删除表单数据，表单定义
     *
     * @param context
     * @return long
     */
    @Override
    public FormField execute(CommandContext context) {
        FormField formField = super.execute(context);
        if (formField != null) {
            Long num = context.getMapper().deleteById(FormField.class, formField.getId());
//TODO 删除失败
            CacheUtil.removeFormDefinitionCache(context, formField.getFormDefinitionId());
        }
        return formField;
    }

}
