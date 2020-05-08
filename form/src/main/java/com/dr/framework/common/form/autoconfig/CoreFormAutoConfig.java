package com.dr.framework.common.form.autoconfig;

import com.dr.framework.common.form.core.service.FormDataService;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.core.service.impl.DefaultFormNameGenerator;
import com.dr.framework.common.form.core.service.impl.FormDataServiceImpl;
import com.dr.framework.common.form.core.service.impl.FormDefinitionServiceImpl;
import com.dr.framework.common.form.engine.CommandContextFactory;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.engine.CommandPlugin;
import com.dr.framework.common.form.engine.impl.PlugInCommandExecutor;
import com.dr.framework.common.form.engine.impl.StandCommandContextFactory;
import com.dr.framework.common.form.engine.impl.StandCommandExecutor;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.database.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 核心包功能默认实现配置类
 *
 * @author dr
 */
@Configuration
public class CoreFormAutoConfig {
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
    protected CommandExecutor commandExecutor(
            @Autowired(required = false) List<CommandPlugin> plugins,
            CommandContextFactory commandContextFactory
    ) {
        //构造标准的命令执行器
        StandCommandExecutor commandExecutor = new StandCommandExecutor(commandContextFactory);
        return new PlugInCommandExecutor(commandExecutor, plugins, commandContextFactory);
    }

    /**
     * 注入默认的表单名称生成器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    protected FormNameGenerator formNameGenerator(DataBaseService dataBaseService) {
        Dialect dialect = dataBaseService.getDataBaseMetaDataByModuleName(Constants.MODULE_NAME).getDialect();
        return new DefaultFormNameGenerator(dialect);
    }

    /**
     * 注入默认的表单定义service
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public FormDefinitionService formDefinitionService() {
        return new FormDefinitionServiceImpl();
    }

    /**
     * 注入默认的表单实例service
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public FormDataService formDataService() {
        return new FormDataServiceImpl();
    }


}
