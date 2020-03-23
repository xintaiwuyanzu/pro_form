package com.dr.framework.common.form.autoconfig;

import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.core.service.impl.DefaultFormNameGenerator;
import com.dr.framework.common.form.engine.CommandContextFactory;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.engine.CommandPlugin;
import com.dr.framework.common.form.engine.impl.PlugInCommandExecutor;
import com.dr.framework.common.form.engine.impl.StandCommandContextFactory;
import com.dr.framework.common.form.engine.impl.StandCommandExecutor;
import com.dr.framework.common.form.validate.service.ValidateService;
import com.dr.framework.common.form.validate.service.impl.DefaultValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 自动配置默认实现
 *
 * @author dr
 */
@Configuration
public class FormAutoConfig {
    /**
     * 注入默认的contextFactory
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    protected CommandContextFactory commandContextFactory() {
        return new StandCommandContextFactory();
    }

    /**
     * 注入默认的命令执行器
     *
     * @param plugins
     * @param commandContextFactory
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    protected CommandExecutor commandExecutor(@Autowired(required = false) List<CommandPlugin> plugins, CommandContextFactory commandContextFactory) {
        //构造标准的命令执行器
        StandCommandExecutor commandExecutor = new StandCommandExecutor(commandContextFactory);
        return new PlugInCommandExecutor(commandExecutor, plugins);
    }

    /**
     * 注入默认的校验service
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    protected ValidateService validateService() {
        return new DefaultValidateService();
    }

    /**
     * 注入默认的表单名称生成器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    protected FormNameGenerator formNameGenerator() {
        return new DefaultFormNameGenerator();
    }


}
