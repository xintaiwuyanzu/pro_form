package com.dr.framework.common.form.engine.impl;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.CommandExecutor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.cache.CacheManager;

/**
 * @author dr
 */
public class StandCommandContext implements CommandContext, ApplicationContextAware {
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

    @Override
    public CacheManager getCacheManager() {
        return null;
    }

    public void setExecutor(CommandExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
