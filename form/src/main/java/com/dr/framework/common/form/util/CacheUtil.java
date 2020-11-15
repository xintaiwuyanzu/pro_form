package com.dr.framework.common.form.util;

import com.dr.framework.common.config.model.MetaMap;
import com.dr.framework.common.config.service.CommonMetaService;
import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.entity.StatusEntity;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.FormFieldInfo;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.display.entity.FieldDisplayScheme;
import com.dr.framework.common.form.display.entity.FieldDisplaySchemeInfo;
import com.dr.framework.common.form.display.entity.FormDisplayScheme;
import com.dr.framework.common.form.display.service.FormDisplayService;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.entity.FormDefinitionRelation;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;


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
    public static final String FORM_DISPLAY_CACHE_KEY = "common.form.display";

    /**
     * 先从缓存读取表单定义，
     *
     * @param context
     * @param formDefinitionId
     * @return
     */
    public static FormDefinition getFormDefinitionFromCache(CommandContext context, String formDefinitionId) {
        Assert.isTrue(!StringUtils.isEmpty(formDefinitionId), "表单定义Id不能为空！");
        Cache formDefinitionCache = context.getCacheManager().getCache(FORM_CACHE_KEY);
        FormDefinition formDefinition = formDefinitionCache.get(formDefinitionId, FormDefinition.class);
        if (formDefinition == null) {
            synchronized (formDefinitionCache) {
                CommonMapper commonMapper = context.getMapper();
                CommonMetaService metaService = context.getApplicationContext().getBean(CommonMetaService.class);

                Assert.isTrue(!StringUtils.isEmpty(formDefinitionId), "表单定义Id不能为空！");
                formDefinition = commonMapper.selectById(FormDefinition.class, formDefinitionId);
                Assert.notNull(formDefinition, () -> "指定的表单Id不存在" + formDefinitionId);
                FormDefinition finalFormDefinition = formDefinition;
                formDefinition.setFields(commonMapper.selectByQuery(
                        SqlQuery.from(FormField.class)
                                .equal(FormFieldInfo.FORMDEFINITIONID, formDefinition.getId())
                                .equal(FormFieldInfo.STATUS, StatusEntity.STATUS_ENABLE_STR)
                        )
                                .stream()
                                .peek(f -> {
                                    f.setMeta(getMeta(metaService, f.getId(), FormDefinitionService.FORM_DEFINITION_FIELD_META_TYPE));
                                    f.setFormDefinitionCode(finalFormDefinition.getFormCode());
                                    f.setVersion(finalFormDefinition.getVersion());
                                    f.setFormDefinitionName(finalFormDefinition.getFormName());
                                })
                                .collect(Collectors.toList())
                );
                formDefinition.setMeta(getMeta(metaService, formDefinition.getId(), FormDefinitionService.FORM_DEFINITION_META_TYPE));
                formDefinitionCache.put(formDefinitionId, formDefinition);
            }
        }
        return formDefinition;
    }

    /**
     * 从缓存获取显示方案
     *
     * @param context
     * @param formDisplayId
     * @return
     */
    public static FormDisplayScheme getFormDisplayFromCache(CommandContext context, String formDisplayId) {
        Assert.isTrue(!StringUtils.isEmpty(formDisplayId), "表单显示方案Id不能为空！");
        Cache displayCache = context.getCacheManager().getCache(FORM_DISPLAY_CACHE_KEY);
        FormDisplayScheme formDisplay = displayCache.get(formDisplayId, FormDisplayScheme.class);
        if (formDisplay == null) {
            synchronized (displayCache) {
                CommonMapper commonMapper = context.getMapper();
                CommonMetaService metaService = context.getApplicationContext().getBean(CommonMetaService.class);

                formDisplay = commonMapper.selectById(FormDisplayScheme.class, formDisplayId);
                Assert.notNull(formDisplay, "未找到指定的显示方案");

                FormDefinition formDefinition = getFormDefinitionFromCache(context, formDisplay.getFormDefinitionId());
                bindFormDefinitionRelation(formDisplay, formDefinition);

                formDisplay.setMeta(getMeta(metaService, formDisplayId, FormDisplayService.FORM_DISPLAY_META_TYPE));
                formDisplay.setFields(
                        commonMapper.selectByQuery(
                                SqlQuery.from(FieldDisplayScheme.class)
                                        .equal(FieldDisplaySchemeInfo.FORMDISPLAYID, formDisplayId)
                                        .orderBy(FieldDisplaySchemeInfo.ORDERBY)
                        ).stream()
                                .peek(f -> {
                                    f.setMeta(getMeta(metaService, f.getId(), FormDisplayService.FORM_DISPLAY_FIELD_META_TYPE));
                                    bindFormDefinitionRelation(f, formDefinition);
                                })
                                .collect(Collectors.toList())
                );
                displayCache.put(formDisplayId, formDisplay);
            }
        }
        return formDisplay;
    }

    /**
     * 绑定表单定义基本信息
     *
     * @param relation
     * @param formDefinition
     */
    private static void bindFormDefinitionRelation(FormDefinitionRelation relation, FormDefinition formDefinition) {
        relation.setFormDefinitionCode(formDefinition.getFormCode());
        relation.setVersion(formDefinition.getVersion());
        relation.setFormDefinitionName(formDefinition.getFormName());
    }

    public static MetaMap getMeta(CommonMetaService metaService, String refId, String refType) {
        return metaService.metaMap(refId, refType);
    }

    /**
     * 删除显示方案缓存
     *
     * @param context
     * @param formDisplayId
     */
    public static void removeFormDisplayCache(CommandContext context, String formDisplayId) {
        removeCache(context, FORM_DISPLAY_CACHE_KEY, formDisplayId);
    }


    /**
     * 根据表单定义Id删除缓存
     *
     * @param formDefinitionId
     */
    public static void removeFormDefinitionCache(CommandContext context, String formDefinitionId) {
        removeCache(context, FORM_CACHE_KEY, formDefinitionId);
    }

    private static void removeCache(CommandContext context, String cacheKey, String cacheId) {
        Assert.isTrue(!StringUtils.isEmpty(cacheId), "缓存Id不能为空！");
        CacheManager cacheManager = context.getCacheManager();
        Cache formDefinitionCache = cacheManager.getCache(cacheKey);
        formDefinitionCache.evictIfPresent(cacheId);
    }
}
