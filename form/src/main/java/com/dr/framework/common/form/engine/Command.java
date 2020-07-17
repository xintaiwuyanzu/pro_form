package com.dr.framework.common.form.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 具体的命令实现
 *
 * @param <T>
 * @author dr
 */
public interface Command<T> {
    Logger logger = LoggerFactory.getLogger(Command.class);

    /**
     * 执行命令并且返回执行结果
     *
     * @param context
     * @return
     */
    T execute(CommandContext context);
}
