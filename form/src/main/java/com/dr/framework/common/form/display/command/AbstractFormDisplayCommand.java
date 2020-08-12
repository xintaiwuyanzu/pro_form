package com.dr.framework.common.form.display.command;

import com.dr.framework.common.config.service.CommonMetaService;
import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.display.entity.FormDisplayScheme;
import com.dr.framework.common.form.display.service.FormDisplayService;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.impl.command.AbstractCommand;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.form.engine.model.display.FormDisplay;
import com.dr.framework.common.form.util.CacheUtil;
import org.springframework.util.Assert;

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

}
