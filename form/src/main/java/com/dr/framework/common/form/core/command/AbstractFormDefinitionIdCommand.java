package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.CacheUtil;
import org.springframework.util.StringUtils;

/**
 * 默认根据表单Id查询的抽象父类
 * <p>
 * 传了formDefinitionId就根据formDefinitionId查询具体的数据
 * <p>
 * 否则按照code和版本号查询数据，版本号为空则查询默认的表单
 *
 * @author dr
 */
public abstract class AbstractFormDefinitionIdCommand extends AbstractFormDefinitionVersionCommand {
    /**
     * 表单Id查询条件
     */
    private String formDefinitionId;

    public AbstractFormDefinitionIdCommand(String formDefinitionId) {
        super(null);
        this.formDefinitionId = formDefinitionId;
    }

    public AbstractFormDefinitionIdCommand(String formCode, Integer version) {
        super(formCode, version);
    }

    /**
     * 根据条件校验表单是否存在
     *
     * @param context
     */
    @Override
    protected boolean checkFormExist(CommandContext context) {
        if (!StringUtils.isEmpty(formDefinitionId)) {
            boolean exist = context.getMapper().exists(FormDefinition.class, getFormDefinitionId());
            if (!exist) {
                CacheUtil.removeFormDefinitionCache(context, formDefinitionId);
            }
            return exist;
        } else {
            return super.checkFormExist(context);
        }
    }

    @Override
    protected FormDefinition getFormDefinition(CommandContext context) {
        if (!StringUtils.isEmpty(formDefinitionId)) {
            return getFormDefinitionById(context, formDefinitionId);
        }
        return super.getFormDefinition(context);
    }

    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    protected void setFormDefinitionId(String formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
    }

}
