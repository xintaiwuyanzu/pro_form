package com.dr.framework.common.form.display.command;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.model.display.FormDisplay;

import java.util.List;

/**
 * 根据显示方案id查询单个显示方案
 *
 * @author dr
 */
public class FormDisplaySelectListCommand extends AbstractFormDisplayQueryCommand implements Command<List<FormDisplay>> {

    public FormDisplaySelectListCommand(String formDefinitionId, String displayType) {
        super(formDefinitionId, displayType);
    }

    public FormDisplaySelectListCommand(String formDefinitionCode, Integer version, String displayType) {
        super(formDefinitionCode, version, displayType);
    }

    @Override
    public List<FormDisplay> execute(CommandContext context) {
        return selectList(context);
    }

}
