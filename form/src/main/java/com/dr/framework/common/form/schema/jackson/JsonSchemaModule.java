package com.dr.framework.common.form.schema.jackson;

import com.dr.framework.common.form.schema.model.JsonSchema;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 用来注入jsonschema解析方案的代码
 *
 * @author dr
 */
public class JsonSchemaModule extends SimpleModule implements ApplicationContextAware, InitializingBean {
    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        addSerializer(JsonSchema.class, new JsonSchemaSerializer(applicationContext));
    }
}
