package com.dr.framework.common.form.autoconfig;

import com.dr.framework.common.form.init.service.FieldDefaultManager;
import com.dr.framework.common.form.init.service.FormDefaultValueService;
import com.dr.framework.common.form.init.service.InitDataService;
import com.dr.framework.common.form.init.service.impl.FieldDefaultManagerImpl;
import com.dr.framework.common.form.init.service.impl.FormDefaultValueServiceImpl;
import com.dr.framework.common.form.init.service.impl.InitDataServiceImpl;
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
    protected FormDefaultValueService formDefaultValueService() {
        return new FormDefaultValueServiceImpl();
    }

    /**
     * 注入默认的表单默认数据service
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    protected InitDataService initDataService() {
        return new InitDataServiceImpl();
    }

    /**
     * 执行字段对比计算的service
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    protected FieldDefaultManager fieldDefaultManager() {
        return new FieldDefaultManagerImpl();
    }


}
