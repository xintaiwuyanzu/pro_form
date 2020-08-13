package com.dr.framework.common.form.engine.impl.service;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 抽象service，封装通用方法
 *
 * @author dr
 */
public class AbstractFormService implements InitializingBean, ApplicationContextAware {
    protected final Logger logger = LoggerFactory.getLogger(AbstractFormService.class);
    @Autowired
    protected CommandExecutor commandExecutor;
    protected ApplicationContext applicationContext;

    /**
     * 包裹事务，统一处理执行的命令
     *
     * @param command
     * @param <T>
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public <T> T execute(Command<T> command) {
        Assert.isTrue(command != null, "命令参数不能为空！");
        logger.trace("开始执行命令：{}", command.getClass().getName());
        return commandExecutor.execute(command);
    }

    @Override
    public void afterPropertiesSet() {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    protected CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    protected ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
