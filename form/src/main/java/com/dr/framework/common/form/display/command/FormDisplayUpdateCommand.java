package com.dr.framework.common.form.display.command;

import com.dr.framework.common.form.display.entity.FormDisplayScheme;
import com.dr.framework.common.form.display.entity.FormDisplaySchemeInfo;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.model.display.FormDisplay;
import com.dr.framework.common.form.util.CacheUtil;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 添加表单显示方案
 *
 * @author dr
 */
public class FormDisplayUpdateCommand extends FormDisplayAddCommand {

    public FormDisplayUpdateCommand(FormDisplay formDisplay, boolean modifyAllVersion) {
        super(formDisplay, modifyAllVersion);
    }

    @Override
    public FormDisplay execute(CommandContext context) {
        FormDisplay formDisplay = getFormDisplay();
        Assert.notNull(formDisplay, "表单显示方案不能为空！");
        String displayId = formDisplay.getId();
        if (StringUtils.isEmpty(displayId) || !context.getMapper().exists(FormDisplayScheme.class, displayId)) {
            return super.execute(context);
        } else {
            //更新基本信息
            context.getMapper().updateByQuery(
                    SqlQuery.from(FormDisplayScheme.class)
                            .set(FormDisplaySchemeInfo.REMARKS, formDisplay.getRemarks())
                            .set(FormDisplaySchemeInfo.NAME, formDisplay.getName())
                            .set(FormDisplaySchemeInfo.DESCRIPTION, formDisplay.getDescription())
                            .set(FormDisplaySchemeInfo.LABELWIDTH, formDisplay.getLabelWidth())
                            .set(FormDisplaySchemeInfo.ORDERBY, formDisplay.getOrder())
                            .set(FormDisplaySchemeInfo.UPDATEPERSON, getCurrentPersonId())
                            .set(FormDisplaySchemeInfo.UPDATEDATE, System.currentTimeMillis())
                            .equal(FormDisplaySchemeInfo.ID, displayId)
            );
            //删除缓存
            CacheUtil.removeFormDisplayCache(context, displayId);
            return context.getFormDisplayService().getFormDisplayById(displayId);
        }
    }
}
