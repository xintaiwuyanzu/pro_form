package com.dr.framework.common.form.engine.impl;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.engine.FormConfig;
import com.dr.framework.common.form.core.service.FormDataService;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.init.service.FormDefaultValueService;
import com.dr.framework.common.form.validate.service.ValidateDefaultService;
import com.dr.framework.common.form.validate.service.ValidateService;
import com.dr.framework.common.service.DataBaseService;
import org.springframework.beans.BeansException;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;

/**
 * @author dr
 */
public class StandCommandContext implements CommandContext {
    private CommonMapper mapper;
    private FormConfig config;
    private CommandExecutor executor;
    private ApplicationContext applicationContext;
    private CacheManager cacheManager;

    private FormNameGenerator formNameGenerator;
    private FormDefinitionService formDefinitionService;
    private FormDataService formDataService;

    private DataBaseService dataBaseService;

    private ValidateDefaultService validateDefaultService;
    private ValidateService validateService;
    private FormDefaultValueService formDefaultValueService;


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

    @Override
    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public FormNameGenerator getFormNameGenerator() {
        return formNameGenerator;
    }

    public void setFormNameGenerator(FormNameGenerator formNameGenerator) {
        this.formNameGenerator = formNameGenerator;
    }

    @Override
    public FormDefinitionService getFormDefinitionService() {
        return formDefinitionService;
    }

    public void setFormDefinitionService(FormDefinitionService formDefinitionService) {
        this.formDefinitionService = formDefinitionService;
    }

    @Override
    public FormDataService getFormDataService() {
        return formDataService;
    }

    public void setFormDataService(FormDataService formDataService) {
        this.formDataService = formDataService;
    }

    @Override
    public DataBaseService getDataBaseService() {
        return dataBaseService;
    }

    public void setDataBaseService(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    @Override
    public ValidateDefaultService getValidateDefaultService() {
        return validateDefaultService;
    }

    public void setValidateDefaultService(ValidateDefaultService validateDefaultService) {
        this.validateDefaultService = validateDefaultService;
    }

    @Override
    public ValidateService getValidateService() {
        return validateService;
    }

    public void setValidateService(ValidateService validateService) {
        this.validateService = validateService;
    }

    @Override
    public FormDefaultValueService getFormDefaultValueService() {
        return formDefaultValueService;
    }

    public void setFormDefaultValueService(FormDefaultValueService formDefaultValueService) {
        this.formDefaultValueService = formDefaultValueService;
    }

    @Override
    public FormConfig getConfig() {
        return config;
    }

    public void setConfig(FormConfig config) {
        this.config = config;
    }
}
