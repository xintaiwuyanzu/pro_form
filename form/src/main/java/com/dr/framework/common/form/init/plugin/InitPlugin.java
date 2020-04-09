package com.dr.framework.common.form.init.plugin;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.CommandPlugin;
import com.dr.framework.common.form.validate.model.ValidateException;

public class InitPlugin implements CommandPlugin {

    @Override
    public boolean accept(CommandContext context, Command command) {
        return false;
    }

    @Override
    public Command handle(CommandContext context, Command command) throws ValidateException {
        return null;
    }

    @Override
    public String type() {
        return "init";
    }

    @Override
    public String description() {
        return "默认值";
    }

}
