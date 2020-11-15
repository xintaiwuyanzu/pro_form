package com.dr.framework.common.form.display.command;

import com.dr.framework.common.config.service.CommonMetaService;
import com.dr.framework.common.form.display.entity.FieldDisplayScheme;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.CacheUtil;

/**
 * 删除显示方案
 *
 * @author dr
 */
public class FormDisplayFieldRemoveCommand extends AbstractFormDisplayCommand implements Command<Long> {

    private String fieldId;
    private boolean allVersion;

    public FormDisplayFieldRemoveCommand(String fieldId, boolean allVersion) {
        this.fieldId = fieldId;
        this.allVersion = allVersion;
    }

    /**
     * 删除显示方案
     *
     * @param context
     * @return long
     */
    @Override
    public Long execute(CommandContext context) {
        CommonMetaService commonMetaService = context.getApplicationContext().getBean(CommonMetaService.class);
        FieldDisplayScheme fieldDisplayScheme = context.getMapper().selectById(FieldDisplayScheme.class, fieldId);
        long result = deleteDisplayFieldById(context.getMapper(), commonMetaService, fieldId);
        CacheUtil.removeFormDisplayCache(context, fieldDisplayScheme.getFormDisplayId());
        return result;
    }
}
