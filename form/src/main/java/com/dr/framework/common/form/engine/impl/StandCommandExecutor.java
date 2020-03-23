package com.dr.framework.common.form.engine.impl;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContextFactory;
import com.dr.framework.common.form.engine.CommandExecutor;

/**
 * 默认标准命令执行器
 *
 * @author dr
 */
public class StandCommandExecutor implements CommandExecutor {

    private CommandContextFactory commandContextFactory;

    public StandCommandExecutor(CommandContextFactory commandContextFactory) {
        setCommandContextFactory(commandContextFactory);
    }

    @Override
    public <T> T execute(Command<T> command) {
        return command.execute(commandContextFactory.createCommandContext());
    }

    public CommandContextFactory getCommandContextFactory() {
        return commandContextFactory;
    }

    public void setCommandContextFactory(CommandContextFactory commandContextFactory) {
        this.commandContextFactory = commandContextFactory;
    }
}
