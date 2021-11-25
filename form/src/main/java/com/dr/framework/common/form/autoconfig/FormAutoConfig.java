package com.dr.framework.common.form.autoconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 自动配置默认实现
 *
 * @author dr
 */
@Configuration
@Import({
        CoreFormAutoConfig.class,
        FormDefinitionAutoConfig.class,
        FormDisplayAutoConfig.class,
        FormJsonSchemaAutoConfig.class,
        InitFormAutoConfig.class,
        ValidateAutoConfig.class
})
class FormAutoConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new FormDataArgumentResolver());
    }
}
