package com.dr.framework.common.form.util;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.entity.StatusEntity;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.FormFieldInfo;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;


/**
 * 同意管理表单相关的cache
 *
 * @author dr
 */
public class CacheUtil {
    /**
     * 表单定义缓存主键
     */
    public static final String FORM_CACHE_KEY = "common.form.definition";
    /**
     * 字段定义缓存主键
     */
    public static final String FORM_FIELD_CACHE_KEY = "common.form.definition";

    /**
     * 先从缓存读取表单定义，
     *
     * @param context
     * @param formDefinitionId
     * @return
     */
    public static FormDefinition getFormDefinitionFromCache(CommandContext context, String formDefinitionId) {
        Assert.isTrue(!StringUtils.isEmpty(formDefinitionId), "表单定义Id不能为空！");
        CacheManager cacheManager = context.getCacheManager();
        Cache formDefinitionCache = cacheManager.getCache(FORM_CACHE_KEY);
        FormDefinition formDefinition = formDefinitionCache.get(formDefinitionId, FormDefinition.class);
        if (formDefinition == null) {
            CommonMapper commonMapper = context.getMapper();
            Assert.isTrue(!StringUtils.isEmpty(formDefinitionId), "表单定义Id不能为空！");
            formDefinition = commonMapper.selectById(FormDefinition.class, formDefinitionId);
            Assert.notNull(formDefinition, () -> "指定的表单Id不存在" + formDefinitionId);
            formDefinition.setFormFieldList(commonMapper.selectByQuery(
                    SqlQuery.from(FormField.class)
                            .equal(FormFieldInfo.FORMDEFINITIONID, formDefinition.getId())
                            .equal(FormFieldInfo.STATUS, StatusEntity.STATUS_ENABLE_STR)//TODO 删除时也只能删除字段状态为1的？
                    )
            );
            formDefinitionCache.put(formDefinitionId, formDefinition);
        }
        return formDefinition;
    }

    /**
     * 根据表单定义Id删除缓存
     *
     * @param formDefinitionId
     */
    public static void removeCache(CommandContext context, String formDefinitionId) {
        Assert.isTrue(!StringUtils.isEmpty(formDefinitionId), "表单定义Id不能为空！");
        CacheManager cacheManager = context.getCacheManager();
        Cache formDefinitionCache = cacheManager.getCache(FORM_CACHE_KEY);
        formDefinitionCache.evictIfPresent(formDefinitionId);
    }
}