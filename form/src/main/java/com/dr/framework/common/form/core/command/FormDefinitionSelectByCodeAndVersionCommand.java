package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.engine.CommandContext;

/**
 * 根据表单定义Id查询表单定义
 *
 * @author dr
 */
public class FormDefinitionSelectByCodeAndVersionCommand extends AbstractFormDefinitionVersionCommand<FormDefinition> {

    public FormDefinitionSelectByCodeAndVersionCommand(String formCode, Integer version) {
        super(formCode, version);
    }

    /**
     * 查询表单定义对象
     *
     * @param context
     * @return workForm
     */
    @Override
    public FormDefinition execute(CommandContext context) {
        return getFormDefinition(context);
    }

}
