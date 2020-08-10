package com.dr.framework.common.form.display.command;

import com.dr.framework.common.form.core.command.AbstractFormDefinitionIdCommand;

/**
 * 表单显示方案抽象类
 *
 * @author dr
 */
public abstract class AbstractFormDisplayWithFormDefinitionCommand extends AbstractFormDefinitionIdCommand {
    private boolean modifyAllVersion;

    public AbstractFormDisplayWithFormDefinitionCommand(String formDefinitionId) {
        super(formDefinitionId);
    }

    public AbstractFormDisplayWithFormDefinitionCommand(String formCode, Integer version) {
        super(formCode, version);
    }

    protected boolean isModifyAllVersion() {
        return modifyAllVersion;
    }
}
