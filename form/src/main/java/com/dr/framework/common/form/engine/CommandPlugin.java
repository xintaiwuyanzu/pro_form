package com.dr.framework.common.form.engine;

/**
 * 用来拦截命令执行
 *
 * @author dr
 */
public interface CommandPlugin extends Plugin {
    /**
     * 是否能够处理指定的命令
     *
     * @param context
     * @param command
     * @return
     */
    boolean accept(CommandContext context, Command command);

    /**
     * 拦截处理指定的命令
     *
     * @param context
     * @param command
     * @return
     */
    default Command handle(CommandContext context, Command command) throws Exception {
        return command;
    }

    /**
     * 拦截命令返回结果
     *
     * @param context
     * @param command
     * @param returnValue
     * @param <T>
     * @return
     * @throws Exception
     */
    default <T> T postExecute(CommandContext context, Command<T> command, T returnValue) throws Exception {
        return returnValue;
    }

}
