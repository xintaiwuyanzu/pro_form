package com.dr.framework.common.form.engine;

import com.dr.framework.common.dao.CommonMapper;
import org.springframework.context.ApplicationContext;

/**
 * 命令上下文，用来给命令提供上下文参数
 *
 * @author dr
 */
public interface CommandContext {
    /**
     * 获取mapper驱动类
     *
     * @return
     */
    CommonMapper getMapper();

    /**
     * 获取命令执行器
     *
     * @return
     */
    CommandExecutor getExecutor();

    /**
     * 获取spring上下文
     *
     * @return
     */
    ApplicationContext getApplicationContext();

}
