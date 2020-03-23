package com.dr.framework.common.form.engine;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.core.service.FormDataService;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.validate.service.ValidateService;
import org.springframework.context.ApplicationContext;

import javax.cache.CacheManager;

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

    /**
     * 获取缓存管理器
     *
     * @return
     */
    CacheManager getCacheManager();


    /**
     * =========================================================
     * 功能包之间相互调用通过service调用，不可以直接跨包调用command
     * =========================================================
     */

    /**
     * 获取表单定义service
     *
     * @return
     */
    default FormDefinitionService getFormDefinitionService() {
        return getApplicationContext().getBean(FormDefinitionService.class);
    }

    /**
     * 获取表单数据service
     *
     * @return
     */
    default FormDataService getFormDataService() {
        return getApplicationContext().getBean(FormDataService.class);
    }

    /**
     * 获取校验服务
     *
     * @return
     */
    default ValidateService getValidateService() {
        return getApplicationContext().getBean(ValidateService.class);
    }

}
