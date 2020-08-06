package com.dr.framework.common.form.display.service.impl;

import com.dr.framework.common.config.service.CommonConfigService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 抽象组件，用来处理共性的功能
 */
public abstract class AbstractFieldComponentProvider {
    @Autowired
    protected CommonConfigService configService;


    public CommonConfigService getConfigService() {
        return configService;
    }
}
