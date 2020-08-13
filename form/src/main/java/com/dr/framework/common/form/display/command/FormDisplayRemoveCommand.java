package com.dr.framework.common.form.display.command;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;

/**
 * 删除显示方案
 *
 * @author dr
 */
public class FormDisplayRemoveCommand extends AbstractFormDisplayCommand implements Command<Long> {

    private String formDisplayId;
    private boolean allVersion;

    public FormDisplayRemoveCommand(String formDisplayId, boolean allVersion) {
        this.formDisplayId = formDisplayId;
        this.allVersion = allVersion;
    }

    /**
     * 删除显示方案
     *
     * @param context
     * @return long
     */
    @Override
    public Long execute(CommandContext context) {
        return deleteFormDisplayById(context, formDisplayId, allVersion);
    }
}
