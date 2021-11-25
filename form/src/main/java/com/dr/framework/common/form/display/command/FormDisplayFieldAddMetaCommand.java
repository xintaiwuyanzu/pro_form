package com.dr.framework.common.form.display.command;

import com.dr.framework.common.form.display.service.FormDisplayService;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.impl.command.SetMetaCommand;
import com.dr.framework.common.form.engine.model.display.FieldDisplay;
import com.dr.framework.common.form.engine.model.display.FormDisplay;
import com.dr.framework.common.form.util.CacheUtil;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 表单添加元数据
 *
 * @author dr
 */
public class FormDisplayFieldAddMetaCommand extends AbstractFormDisplayCommand implements SetMetaCommand {
    private String displayId;
    private String fieldCode;
    private Map<String, String> metas;

    public FormDisplayFieldAddMetaCommand(String displayId, String fieldCode, Map<String, String> metas) {
        this.displayId = displayId;
        this.fieldCode = fieldCode;
        this.metas = metas;
    }

    @Override
    public Map<String, String> getMetaMap() {
        return metas;
    }

    @Override
    public String getRefId(CommandContext context) {
        FormDisplay formDisplay = getFormDisplayById(context, displayId);
        Assert.notNull(formDisplay, "未查询到指定的显示方案！");
        FieldDisplay fieldDisplay = formDisplay.getFieldByCode(fieldCode);
        return fieldDisplay.getId();
    }

    @Override
    public String getRefType(CommandContext context) {
        return FormDisplayService.FORM_DISPLAY_FIELD_META_TYPE;
    }

    @Override
    public void clearCache(CommandContext context) {
        CacheUtil.removeFormDisplayCache(context, displayId);
    }
}
