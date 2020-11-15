package com.dr.framework.common.form.display.command;

import com.dr.framework.common.form.display.service.FormDisplayService;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.impl.command.SetMetaCommand;
import com.dr.framework.common.form.engine.model.display.FormDisplay;
import com.dr.framework.common.form.util.CacheUtil;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 表单添加元数据
 *
 * @author dr
 */
public class FormDisplayAddMetaCommand extends AbstractFormDisplayCommand implements SetMetaCommand {
    private Map<String, String> metas;
    private String formDisplayId;

    public FormDisplayAddMetaCommand(String formDisplayId, Map<String, String> metas) {
        this.metas = metas;
        this.formDisplayId = formDisplayId;
    }

    @Override
    public Map<String, String> getMetaMap() {
        return metas;
    }

    @Override
    public String getRefId(CommandContext context) {
        FormDisplay formDisplay = getFormDisplayById(context, formDisplayId);
        Assert.notNull(formDisplay, "未查询到指定的显示方案！");
        return formDisplay.getId();
    }

    @Override
    public String getRefType(CommandContext context) {
        return FormDisplayService.FORM_DISPLAY_META_TYPE;
    }

    @Override
    public void clearCache(CommandContext context) {
        CacheUtil.removeFormDisplayCache(context, getRefId(context));
    }
}
