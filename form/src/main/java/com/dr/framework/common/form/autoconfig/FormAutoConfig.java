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
        InitFormAutoConfig.class,
        ValidateAutoConfig.class
})
public class FormAutoConfig {

}
