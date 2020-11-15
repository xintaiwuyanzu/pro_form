package com.dr.framework.common.form.display.command;

import com.dr.framework.common.config.service.CommonMetaService;
import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.display.entity.FieldDisplayScheme;
import com.dr.framework.common.form.display.entity.FormDisplayScheme;
import com.dr.framework.common.form.display.entity.FormDisplaySchemeInfo;
import com.dr.framework.common.form.display.service.FormDisplayService;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.impl.command.AbstractCommand;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.form.engine.model.display.FormDisplay;
import com.dr.framework.common.form.util.CacheUtil;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 根据显示方案Id查询的命令
 *
 * @author dr
 */
public class AbstractFormDisplayCommand extends AbstractCommand {
    /**
     * 根据显示方案Id删除相关数据
     *
     * @param context
     * @param formDisplayId
     * @param allVersion    是否删除所有版本
     * @return
     */
    protected long deleteFormDisplayById(CommandContext context, String formDisplayId, boolean allVersion) {
        if (allVersion) {
            FormDisplay formDisplay = getFormDisplayById(context, formDisplayId);
            Assert.notNull(formDisplay, "未找到指定的显示方案");
            //TODO 删除所有版本的显示方案
            return deleteFormDisplayById(context, formDisplayId);
        } else {
            return deleteFormDisplayById(context, formDisplayId);
        }
    }

    /**
     * 根据显示方案Id删除相关数据
     *
     * @param context
     * @param formDisplayId
     * @return
     */
    protected long deleteFormDisplayById(CommandContext context, String formDisplayId) {
        long count = 0;
        FormDisplay display = getFormDisplayById(context, formDisplayId);
        Assert.notNull(display, "未查询到指定的显示方案！");
        CommonMetaService commonMetaService = context.getApplicationContext().getBean(CommonMetaService.class);
        CommonMapper mapper = context.getMapper();
        //删除显示方案
        count += mapper.deleteById(FormDisplayScheme.class, formDisplayId);
        //删除字段定义
        count += display.getFields()
                .stream()
                .mapToLong(f -> deleteDisplayFieldById(mapper, commonMetaService, f.getId()))
                .sum();
        //删除元数据
        count += deleteMeta(commonMetaService, formDisplayId, FormDisplayService.FORM_DISPLAY_META_TYPE);
        //删除缓存
        CacheUtil.removeFormDisplayCache(context, formDisplayId);
        return count;
    }


    protected long deleteDisplayFieldById(CommonMapper mapper, CommonMetaService commonMetaService, String displayFieldId) {
        long count = 0;
        count += mapper.deleteById(FieldDisplayScheme.class, displayFieldId);
        count += deleteMeta(commonMetaService, displayFieldId, FormDisplayService.FORM_DISPLAY_FIELD_META_TYPE);
        return count;
    }


    /**
     * 根据Id获取表单显示方案
     *
     * @param context
     * @param formDisplayId
     * @return
     */
    protected FormDisplayScheme getFormDisplayById(CommandContext context, String formDisplayId) {
        return CacheUtil.getFormDisplayFromCache(context, formDisplayId);
    }

    /**
     * 根据表单定义id 查询表单定义
     *
     * @param context
     * @param formDefinitionId
     * @return
     */
    protected FormModel getFormDefinitionById(CommandContext context, String formDefinitionId) {
        return context.getFormDefinitionService()
                .selectFormDefinitionById(formDefinitionId);
    }

    /**
     * 根据表单编码和版本查询表单定义
     *
     * @param context
     * @param formCode
     * @param version
     * @return
     */
    protected FormModel getFormDefinitionByCodeAndVersion(CommandContext context, String formCode, Integer version) {
        return context.getFormDefinitionService()
                .selectFormDefinitionByCodeAndVersion(formCode, version);
    }

    /**
     * 根据显示方案查找表单定义
     *
     * @param context
     * @param display
     * @return
     */
    protected FormModel getFormDefinitionByFormDisplay(CommandContext context, FormDisplay display) {
        if (!StringUtils.isEmpty(display.getFormDefinitionId())) {
            return getFormDefinitionById(context, display.getFormDefinitionId());
        } else {
            return getFormDefinitionByCodeAndVersion(context, display.getFormDefinitionCode(), display.getVersion());
        }
    }

    /**
     * 校验表单显示方案
     *
     * @param context
     * @param formDisplay
     */
    protected void validateFormDisplay(CommandContext context, FormDisplay formDisplay, boolean insert) {
        if (!StringUtils.isEmpty(formDisplay.getFormDefinitionId())) {
            //表单定义Id为空，则从code和版本查询表单定义
        } else {
            Assert.isTrue(!StringUtils.isEmpty(formDisplay.getFormDefinitionCode()), "表单定义Id和编码不能同时为空！");
        }

        FormModel formModel = getFormDefinitionByFormDisplay(context, formDisplay);
        Assert.notNull(formModel, "未找到指定的表单定义！");
        Assert.isTrue(!StringUtils.isEmpty(formDisplay.getCode()), "显示方案编码不能为空！");
        Assert.isTrue(!StringUtils.isEmpty(formDisplay.getType()), "显示方案类型不能为空！");
        if (insert) {
            //判断重复
            Assert.isTrue(
                    !context.getMapper().existsByQuery(
                            SqlQuery.from(FormDisplayScheme.class)
                                    .equal(FormDisplaySchemeInfo.FORMDEFINITIONID, formDisplay.getFormDefinitionId())
                                    .equal(FormDisplaySchemeInfo.CODE, formDisplay.getCode())
                                    .equal(FormDisplaySchemeInfo.TYPE, formDisplay.getType())
                    ), "同一个表单的相同类型的显示方案编码不能相同！");

        }
    }
}
