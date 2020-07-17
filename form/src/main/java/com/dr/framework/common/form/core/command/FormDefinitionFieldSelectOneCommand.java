package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 根据字段编码获取单个字段信息
 *
 * @author dr
 */
public class FormDefinitionFieldSelectOneCommand extends AbstractFormDefinitionIdCommand implements Command<FormField> {
    /**
     * 字段编码
     */
    private String fieldCode;

    public FormDefinitionFieldSelectOneCommand(String formDefinitionId, String fieldCode) {
        super(formDefinitionId);
        this.fieldCode = fieldCode;
    }

    public FormDefinitionFieldSelectOneCommand(String formCode, Integer version, String fieldCode) {
        super(formCode, version);
        this.fieldCode = fieldCode;
    }

    @Override
    public FormField execute(CommandContext context) {
        Assert.isTrue(!StringUtils.isEmpty(fieldCode), "字段编码不能为空！");
        FormDefinition formDefinition = getFormDefinition(context);
        Assert.notNull(formDefinition, "未查询到指定的表单定义！");
        return formDefinition.getFieldByCode(fieldCode);
    }

    public String getFieldCode() {
        return fieldCode;
    }

}
