package com.dr.framework.common.form.engine;

/**
 * 用来创建命令上下文
 *
 * @author dr
 */
public interface CommandContextFactory {
    /**
     * 创建命令上下文
     *
     * @return
     */
    CommandContext createCommandContext();

}
