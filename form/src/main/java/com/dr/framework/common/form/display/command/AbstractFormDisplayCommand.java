package com.dr.framework.common.form.display.command;

import com.dr.framework.common.form.core.command.AbstractFormDefinitionIdCommand;

/**
 * 表单显示方案抽象类
 *
 * @author dr
 */
public abstract class AbstractFormDisplayCommand extends AbstractFormDefinitionIdCommand {
    private boolean modifyAllVersion;

    public AbstractFormDisplayCommand(String formDefinitionId) {
        super(formDefinitionId);
    }

    public AbstractFormDisplayCommand(String formCode, Integer version) {
        super(formCode, version);
    }

    protected boolean isModifyAllVersion() {
        return modifyAllVersion;
    }
}
