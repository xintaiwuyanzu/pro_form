package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.query.FormDefinitionQuery;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 根据表单code生成所有的物理表
 *
 * @author dr
 */
public class FormDefinitionGenAllTableByCodeCommand extends AbstractFormDefinitionCommand implements Command<List<FormDefinition>> {
    private String formCode;

    public FormDefinitionGenAllTableByCodeCommand(String formCode) {
        this.formCode = formCode;
    }

    @Override
    public List<FormDefinition> execute(CommandContext context) {
        Assert.isTrue(!StringUtils.isEmpty(formCode), "表单编码不能为空!");
        List<FormDefinition> formDefinitions = (List<FormDefinition>) context.getFormDefinitionService()
                .selectFormDefinitionByQuery(
                        new FormDefinitionQuery()
                                .codeEqual(formCode)
                                .statusEnable()
                );
        formDefinitions.forEach(f -> {
            if (!tableExist(context, f) || !context.getConfig().multiTableEnable(f.getFormCode())) {
                createTable(context, f);
            }
        });
        return formDefinitions;
    }

    public String getFormCode() {
        return formCode;
    }
}
