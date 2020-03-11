package com.dr.framework.common.form.engine;

/**
 * @author dr
 * 命令执行器
 */
public interface CommandExecutor {
    /**
     * 具体执行一个命令，并返回执行结果
     * 一般可以用来增加拦截器，释放资源，缓存数据等操作
     *
     * @param command
     * @param <T>
     * @return
     */
    <T> T execute(Command<T> command);
}
