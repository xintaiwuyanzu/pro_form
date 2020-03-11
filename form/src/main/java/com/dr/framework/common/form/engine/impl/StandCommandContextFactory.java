package com.dr.framework.common.form.engine.impl;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.CommandContextFactory;
import com.dr.framework.common.form.engine.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 默认上下文构建工厂
 *
 * @author dr
 */
public class StandCommandContextFactory implements CommandContextFactory {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private CommandExecutor commandExecutor;

    @Override
    public CommandContext createCommandContext() {
        StandCommandContext commandContext = new StandCommandContext();
        commandContext.setMapper(commonMapper);
        commandContext.setExecutor(commandExecutor);
        return commandContext;
    }

    public CommonMapper getCommonMapper() {
        return commonMapper;
    }

    public void setCommonMapper(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }

    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    public void setCommandExecutor(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

}
