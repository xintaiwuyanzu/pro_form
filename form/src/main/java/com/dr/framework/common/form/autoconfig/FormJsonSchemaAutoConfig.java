package com.dr.framework.common.form.autoconfig;

import com.dr.framework.common.form.display.config.JsonSchemaConfig;
import com.dr.framework.common.form.schema.jackson.JsonSchemaModule;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置jsonschema
 *
 * @author dr
 */
@Configuration
@EnableConfigurationProperties(JsonSchemaConfig.class)
class FormJsonSchemaAutoConfig {
    /**
     * 自动配置前端jsonschema返回json对象
     *
     * @return
     */
    @Bean
    JsonSchemaModule jsonSchemaModule() {
        return new JsonSchemaModule();
    }

}
