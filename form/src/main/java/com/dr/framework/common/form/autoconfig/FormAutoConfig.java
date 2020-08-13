package com.dr.framework.common.form.autoconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动配置默认实现
 *
 * @author dr
 */
@Configuration
@Import({
        CoreFormAutoConfig.class,
        FormDisplayAutoConfig.class,
        FormJsonSchemaAutoConfig.class,
        InitFormAutoConfig.class,
        ValidateAutoConfig.class
})
class FormAutoConfig {


}
