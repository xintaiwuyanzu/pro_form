package com.dr.framework.common.form.engine;

/**
 * 具体的命令实现
 *
 * @param <T>
 * @author dr
 */
public interface Command<T> {
    /**
     * 执行命令并且返回执行结果
     *
     * @param context
     * @return
     */
    T execute(CommandContext context);
}
