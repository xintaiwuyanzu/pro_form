package com.dr.framework.common.form.display.command;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.model.display.FieldDisplay;

/**
 * 添加字段展示
 *
 * @author dr
 */
public class FormDisplayFieldAddCommand extends FormDisplayFieldModifyCommand implements Command<FieldDisplay> {

    public FormDisplayFieldAddCommand(FieldDisplay fieldDisplay, boolean modifyAllVersion) {
        super(fieldDisplay, modifyAllVersion);
    }

    @Override
    protected boolean isInsert() {
        return true;
    }
}
