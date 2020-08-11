package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.CacheUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * 根据表单定义Id查询表单定义
 *
 * @author dr
 */
public class FormDefinitionSelectByIdCommand implements Command<FormDefinition> {
    /**
     * 表单定义Id
     */
    private final String formDefinitionId;

    public FormDefinitionSelectByIdCommand(String formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
    }

    /**
     * 查询表单定义对象
     *
     * @param context
     * @return workForm
     */
    @Override
    public FormDefinition execute(CommandContext context) {
        Assert.isTrue(!StringUtils.isEmpty(formDefinitionId), "表单定义Id不能为空！");
        return CacheUtil.getFormDefinitionFromCache(context, formDefinitionId);
    }

}
