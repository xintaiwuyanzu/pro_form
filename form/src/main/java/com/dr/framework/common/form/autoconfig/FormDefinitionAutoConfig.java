package com.dr.framework.common.form.autoconfig;

import com.dr.framework.common.entity.BaseCreateInfoEntity;
import com.dr.framework.common.form.core.plugin.impl.FormTypeInitFieldsPlugin;
import com.dr.framework.common.form.core.service.FormDefinitionTypeProvider;
import com.dr.framework.common.form.core.service.impl.AbstractEntityFormDefinitionTypeProvider;
import com.dr.framework.core.util.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 表单定义自动配置
 *
 * @author dr
 */
@Configuration
class FormDefinitionAutoConfig {
    /**
     * 根据表单类型自动初始化表单字段定义
     *
     * @param providerList
     * @return
     */
    @Bean
    FormTypeInitFieldsPlugin formTypeInitFieldsPlugin(List<FormDefinitionTypeProvider> providerList) {
        return new FormTypeInitFieldsPlugin(providerList);
    }

    /**
     * 默认表单类型
     *
     * @return
     */
    @Bean
    AbstractEntityFormDefinitionTypeProvider defaultDefinitionTypeProvider() {
        return new AbstractEntityFormDefinitionTypeProvider() {
            @Override
            public Class getEntityClass() {
                return BaseCreateInfoEntity.class;
            }

            @Override
            public String type() {
                return Constants.DEFAULT;
            }

            @Override
            public String description() {
                return "默认表单";
            }
        };
    }


}
