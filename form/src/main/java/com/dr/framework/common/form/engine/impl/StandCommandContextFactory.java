package com.dr.framework.common.form.engine.impl;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.engine.config.FormConfig;
import com.dr.framework.common.form.core.service.FormDataService;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.CommandContextFactory;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.init.service.FormDefaultValueService;
import com.dr.framework.common.form.validate.service.ValidateDefaultService;
import com.dr.framework.common.form.validate.service.ValidateService;
import com.dr.framework.common.service.DataBaseService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * 默认上下文构建工厂
 *
 * @author dr
 */
public class StandCommandContextFactory implements CommandContextFactory, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private CommonMapper mapper;
    @Autowired
    private CommandExecutor executor;

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private FormNameGenerator formNameGenerator;
    @Autowired
    private FormDefinitionService formDefinitionService;
    @Autowired
    private FormDataService formDataService;

    @Autowired
    private DataBaseService dataBaseService;

    @Autowired
    private ValidateDefaultService validateDefaultService;
    @Autowired
    private ValidateService validateService;
    @Autowired
    private FormDefaultValueService formDefaultValueService;
    @Autowired
    private FormConfig formConfig;

    @Override
    public CommandContext createCommandContext() {
        Assert.isTrue(applicationContext != null, "spring上下文不能为空！");
        StandCommandContext context = new StandCommandContext();
        context.setApplicationContext(applicationContext);
        context.setMapper(mapper);
        context.setConfig(formConfig);

        context.setExecutor(executor);
        context.setCacheManager(cacheManager);
        context.setFormNameGenerator(formNameGenerator);

        context.setFormDefinitionService(formDefinitionService);
        context.setFormDataService(formDataService);
        context.setDataBaseService(dataBaseService);

        context.setValidateDefaultService(validateDefaultService);
        context.setValidateService(validateService);
        context.setFormDefaultValueService(formDefaultValueService);
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
