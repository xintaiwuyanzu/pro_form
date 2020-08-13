package com.dr.framework.common.form.engine;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.core.service.FormDataService;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.display.service.FormDisplayService;
import com.dr.framework.common.form.engine.config.FormConfig;
import com.dr.framework.common.form.init.service.FormDefaultValueService;
import com.dr.framework.common.form.validate.service.ValidateDefaultService;
import com.dr.framework.common.form.validate.service.ValidateService;
import com.dr.framework.common.service.DataBaseService;
import org.springframework.cache.CacheManager;
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
     * 获取全局初始化表单配置
     *
     * @return
     */
    FormConfig getConfig();

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
     * 获取表单名称生成器
     *
     * @return
     */
    FormNameGenerator getFormNameGenerator();

    /**
     * 获取表单定义service
     *
     * @return
     */
    FormDefinitionService getFormDefinitionService();

    /**
     * 获取显示方案service
     *
     * @return
     */
    FormDisplayService getFormDisplayService();

    /**
     * 获取表单数据service
     *
     * @return
     */
    FormDataService getFormDataService();

    /**
     * 获取数据库操作工具类
     *
     * @return
     */
    DataBaseService getDataBaseService();

    /**
     * 获取校验服务
     *
     * @return
     */
    ValidateDefaultService getValidateDefaultService();

    /**
     * 获取校验服务
     *
     * @return
     */
    ValidateService getValidateService();

    /**
     * 获取默认值服务
     *
     * @return
     */
    FormDefaultValueService getFormDefaultValueService();

}
