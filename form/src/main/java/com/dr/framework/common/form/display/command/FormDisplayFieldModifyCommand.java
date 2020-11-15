package com.dr.framework.common.form.display.command;

import com.dr.framework.common.form.display.entity.FieldDisplayScheme;
import com.dr.framework.common.form.display.entity.FieldDisplaySchemeInfo;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.model.display.FieldDisplay;
import com.dr.framework.common.form.util.CacheUtil;
import com.dr.framework.common.service.CommonService;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 添加字段展示
 *
 * @author dr
 */
public abstract class FormDisplayFieldModifyCommand extends AbstractFormDisplayCommand implements Command<FieldDisplay> {
    private FieldDisplay fieldDisplay;
    private boolean modifyAllVersion;

    public FormDisplayFieldModifyCommand(FieldDisplay fieldDisplay, boolean modifyAllVersion) {
        this.fieldDisplay = fieldDisplay;
        this.modifyAllVersion = modifyAllVersion;
    }

    @Override
    public FieldDisplay execute(CommandContext context) {
        validateFieldDisplay(context, fieldDisplay);
        FieldDisplayScheme displayScheme = newFieldDisplay(context, fieldDisplay);
        CommonService.bindCreateInfo(displayScheme);
        if (isInsert()) {
            context.getMapper().insert(displayScheme);
        } else {
            context.getMapper().updateIgnoreNullById(displayScheme);
        }
        CacheUtil.removeFormDisplayCache(context, displayScheme.getFormDisplayId());
        return displayScheme;
    }

    /**
     * 新建字段定义
     *
     * @param context
     * @param fieldDisplay
     * @return
     */
    protected FieldDisplayScheme newFieldDisplay(CommandContext context, FieldDisplay fieldDisplay) {
        FieldDisplayScheme fieldDisplayScheme = new FieldDisplayScheme(fieldDisplay);
        return fieldDisplayScheme;
    }

    /**
     * 校验字段显示方案
     *
     * @param context
     * @param fieldDisplay
     */
    protected void validateFieldDisplay(CommandContext context, FieldDisplay fieldDisplay) {
        Assert.notNull(fieldDisplay, "字段显示方案不能为空！");
        Assert.isTrue(!StringUtils.isEmpty(fieldDisplay.getFormDisplayId()), "显示方案Id不能为空！");
        //TODO 判断表单有无指定的字段
        Assert.isTrue(!StringUtils.isEmpty(fieldDisplay.getCode()), "编码不能为空！");
        //TODO 判断显示类型
        Assert.isTrue(!StringUtils.isEmpty(fieldDisplay.getType()), "类型不能为空！");

        Assert.notNull(getFormDisplayById(context, fieldDisplay.getFormDisplayId()), "未查询到指定的显示方案");
        if (isInsert()) {
            //判断重复
            Assert.isTrue(
                    !context.getMapper().existsByQuery(
                            SqlQuery.from(FieldDisplayScheme.class)
                                    .equal(FieldDisplaySchemeInfo.FORMDEFINITIONID, fieldDisplay.getFormDisplayId())
                                    .equal(FieldDisplaySchemeInfo.CODE, fieldDisplay.getCode())
                    ), "字段不能重复！");

        }
    }

    /**
     * 是否插入语句
     *
     * @return
     */
    protected abstract boolean isInsert();

}
