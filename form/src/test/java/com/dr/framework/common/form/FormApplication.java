package com.dr.framework.common.form;

import com.dr.framework.common.form.engine.CommandContextFactory;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.engine.impl.StandCommandContextFactory;
import com.dr.framework.common.form.engine.impl.StandCommandExecutor;
import com.dr.framework.core.orm.support.mybatis.spring.boot.autoconfigure.EnableAutoMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.dr")
@EnableAutoMapper(basePackages = "com.dr")
public class FormApplication {
    @Bean
    CommandExecutor commandExecutor() {
        return new StandCommandExecutor();
    }

    @Bean
    CommandContextFactory commandContextFactory() {
        return new StandCommandContextFactory();
    }

}
