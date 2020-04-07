package com.dr.framework.common.form.autoconfig;

import com.dr.framework.common.form.validate.service.ValidateService;
import com.dr.framework.common.form.validate.service.impl.DefaultValidateService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 校验相关的默认实现配置
 *
 * @author dr
 */
@Configuration
public class ValidateAutoConfig {
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
}
