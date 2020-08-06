package com.dr.framework.common.form.autoconfig;

import com.dr.framework.common.form.core.service.FormDataService;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.core.service.impl.DefaultFormDefinitionServiceImpl;
import com.dr.framework.common.form.core.service.impl.DefaultFormNameGenerator;
import com.dr.framework.common.form.core.service.impl.FormDataServiceImpl;
import com.dr.framework.common.form.engine.CommandContextFactory;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.engine.CommandPlugin;
import com.dr.framework.common.form.engine.FormConfig;
import com.dr.framework.common.form.engine.impl.PlugInCommandExecutor;
import com.dr.framework.common.form.engine.impl.StandCommandContextFactory;
import com.dr.framework.common.form.engine.impl.StandCommandExecutor;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.database.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 核心包功能默认实现配置类
 *
 * @author dr
 */
@Configuration
@EnableConfigurationProperties(FormConfig.class)
class CoreFormAutoConfig {
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
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    protected CommandExecutor commandExecutor(@Autowired(required = false) List<CommandPlugin> plugins) {
        //构造标准的命令执行器
        StandCommandExecutor commandExecutor = new StandCommandExecutor();
        return new PlugInCommandExecutor(commandExecutor, plugins);
    }

    /**
     * 注入默认的表单名称生成器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    protected FormNameGenerator formNameGenerator(
            @Autowired(required = false) DataBaseService dataBaseService,
            FormConfig config) {
        Dialect dialect = dataBaseService.getDataBaseMetaDataByModuleName(Constants.MODULE_NAME).getDialect();
        return new DefaultFormNameGenerator(dialect, config);
    }

    /**
     * 注入默认的表单定义service
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    protected FormDefinitionService formDefinitionService() {
        return new DefaultFormDefinitionServiceImpl();
    }

    /**
     * 注入默认的表单实例service
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    protected FormDataService formDataService() {
        return new FormDataServiceImpl();
    }


}
