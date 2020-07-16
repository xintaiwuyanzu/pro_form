package com.dr.framework.common.form.core.plugin.impl;

import com.dr.framework.common.form.core.command.FormDataSelectCommand;
import com.dr.framework.common.form.core.service.SqlBuilder;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.CommandPlugin;

public class DefaultSelectDataCondationPlugin implements CommandPlugin {
    @Override
    public String type() {
        return "data-select";
    }

    @Override
    public String description() {
        return "拦截表单数据查询，添加默认查询条件";
    }

    @Override
    public boolean accept(CommandContext context, Command command) {
        return command instanceof FormDataSelectCommand;
    }

    @Override
    public Command handle(CommandContext context, Command command) throws Exception {
        FormDataSelectCommand selectCommand = (FormDataSelectCommand) command;
        SqlBuilder sqlBuilder = selectCommand.getSqlBuilder();
        return null;
    }
}
