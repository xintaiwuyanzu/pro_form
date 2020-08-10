package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import org.springframework.util.Assert;

/**
 * 检查并且生成表结构
 *
 * @author dr
 */
public class FormDefinitionGenTableCommand extends AbstractFormDefinitionIdCommand implements Command<FormDefinition> {

    public FormDefinitionGenTableCommand(String formDefinitionId) {
        super(formDefinitionId);
    }

    public FormDefinitionGenTableCommand(String formCode, Integer version) {
        super(formCode, version);
    }

    @Override
    public FormDefinition execute(CommandContext context) {
        FormDefinition formDefinition = getFormDefinition(context);
        Assert.isTrue(formDefinition != null, FORM_NOT_DEFINITION_ERROR);
        //多模式下，表存在才不生成，单模式需要重复丰富表结构
        if (
                tableExist(context, formDefinition) && context.getConfig().multiTableEnable(formDefinition.getFormCode())
        ) {
            logger.trace("指定的表已存在，不再重复生成，" + formDefinition.getFormTable());
        } else {
            createTable(context, formDefinition);
        }
        return formDefinition;
    }
}
