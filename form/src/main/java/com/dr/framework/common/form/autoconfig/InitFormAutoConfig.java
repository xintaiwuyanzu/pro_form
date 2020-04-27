package com.dr.framework.common.form.autoconfig;

import com.dr.framework.common.form.init.service.FieldDefaultManager;
import com.dr.framework.common.form.init.service.FormDefaultValueService;
import com.dr.framework.common.form.init.service.InitValueService;
import com.dr.framework.common.form.init.service.impl.FieldDefaultManagerImpl;
import com.dr.framework.common.form.init.service.impl.FormDefaultValueServiceImpl;
import com.dr.framework.common.form.init.service.impl.InitValueServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class InitFormAutoConfig {

    /**
     * 注入默认的表单默认值service
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public FormDefaultValueService formDefaultValueService() {
        return new FormDefaultValueServiceImpl();
    }

    /**
     * 注入默认的表单默认数据service
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public InitValueService initValueService() {
        return new InitValueServiceImpl();
    }

    /**
     * 执行字段对比计算的service
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public FieldDefaultManager fieldDefaultManager() {
        return new FieldDefaultManagerImpl();
    }


}
