package com.dr.framework.common.form.display.command;

import com.dr.framework.common.form.display.entity.FormDisplayScheme;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;

/**
 * 根据显示方案id查询单个显示方案
 *
 * @author dr
 */
public class FormDisplaySelectByIdCommand extends AbstractFormDisplayCommand implements Command<FormDisplayScheme> {
    private String formDisplayId;

    public FormDisplaySelectByIdCommand(String formDisplayId) {
        this.formDisplayId = formDisplayId;
    }

    @Override
    public FormDisplayScheme execute(CommandContext context) {
        return getFormDisplayById(context, formDisplayId);
    }
}
