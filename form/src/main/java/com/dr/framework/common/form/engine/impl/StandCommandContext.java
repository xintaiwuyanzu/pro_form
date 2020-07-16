package com.dr.framework.common.form.engine.impl;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.CommandExecutor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * @author dr
 */
public class StandCommandContext implements CommandContext {
    private CommonMapper mapper;
    private CommandExecutor executor;
    private ApplicationContext applicationContext;

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

    @Override
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setExecutor(CommandExecutor executor) {
        this.executor = executor;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
