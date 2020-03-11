package com.dr.framework.common.form.engine.impl;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.CommandExecutor;

/**
 * @author dr
 */
public class StandCommandContext implements CommandContext {
    private CommonMapper mapper;
    private CommandExecutor executor;

    @Override
    public CommonMapper getMapper() {
        return mapper;
    }

    public void setMapper(CommonMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public CommandExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(CommandExecutor executor) {
        this.executor = executor;
    }
}
