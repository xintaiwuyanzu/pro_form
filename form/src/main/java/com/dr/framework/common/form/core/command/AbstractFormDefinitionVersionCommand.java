package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;

/**
 * 默认版本查询的抽象父类
 *
 * @param <T>
 */
public abstract class AbstractFormDefinitionVersionCommand<T> extends AbstractFormDefinitionCommand<T> implements Command<T> {
    /**
     * 表单编码
     */
    private String formCode;
    /**
     * 版本号默认条件
     */
    private Integer version;

    public AbstractFormDefinitionVersionCommand(String formCode, Integer version) {
        this.formCode = formCode;
        this.version = version;
    }

    public AbstractFormDefinitionVersionCommand(String formCode) {
        this.formCode = formCode;
    }

    /**
     * 根据条件校验表单是否存在
     *
     * @param context
     */
    protected boolean checkFormExist(CommandContext context) {
        return existByCodeAndVersion(context, formCode, version);
    }

    /**
     * 获取表单定义对象
     *
     * @param context
     * @return
     */
    protected FormDefinition getFormDefinition(CommandContext context) {
        return getFormDefinitionByCodeAndVersion(context, getFormCode(), getVersion());
    }

    public Integer getVersion() {
        return version;
    }

    public String getFormCode() {
        return formCode;
    }
}
