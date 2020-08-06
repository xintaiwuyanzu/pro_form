package com.dr.framework.common.form.autoconfig;

import com.dr.framework.common.form.display.service.FormDisplayService;
import com.dr.framework.common.form.display.service.impl.FormDisplayServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 表单显示方案自动配置
 */
@Configuration
class FormDisplayAutoConfig {

    /**
     * 注入默认的显示方案service
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    protected FormDisplayService formDisplayService() {
        return new FormDisplayServiceImpl();
    }

}
