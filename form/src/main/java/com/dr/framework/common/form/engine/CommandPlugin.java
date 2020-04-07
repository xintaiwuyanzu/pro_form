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
    Command handle(CommandContext context, Command command) throws Exception;

}
