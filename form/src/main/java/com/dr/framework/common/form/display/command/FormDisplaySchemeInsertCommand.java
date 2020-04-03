package com.dr.framework.common.form.display.command;

import com.dr.framework.common.form.display.entity.FieldDisplayScheme;
import com.dr.framework.common.form.display.entity.FormDisplayScheme;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.List;
import java.util.UUID;

public class FormDisplaySchemeInsertCommand implements Command<FormDisplayScheme> {

    private FormDisplayScheme formDisplayScheme;

    private List<FieldDisplayScheme> fieldDisplayList;

    public FormDisplaySchemeInsertCommand(FormDisplayScheme formDisplayScheme, List<FieldDisplayScheme> fieldDisplayList) {
        this.formDisplayScheme = formDisplayScheme;
        this.fieldDisplayList = fieldDisplayList;
    }

    /**
     * 保存显示方案
     *
     * @param context
     * @return
     */
    @Override
    public FormDisplayScheme execute(CommandContext context) {
        Assert.notNull(formDisplayScheme, "显示方案不能为空");
        Assert.isTrue(StringUtils.isNotEmpty(formDisplayScheme.getFormDefinitionId()), "formId不能为空");
        if (StringUtils.isEmpty(formDisplayScheme.getId())) {
            formDisplayScheme.setId(UUID.randomUUID().toString());
        }
        if (fieldDisplayList.size() > 0) {
            for (FieldDisplayScheme fieldDisplayScheme : fieldDisplayList) {
                fieldDisplayScheme.setFormDisplayId(formDisplayScheme.getId());
                fieldDisplayScheme.setFormDefinitionId(formDisplayScheme.getFormDefinitionId());
                if (StringUtils.isEmpty(fieldDisplayScheme.getId())) {
                    fieldDisplayScheme.setId(UUID.randomUUID().toString());
                }
                //先保存各字段的显示信息
                context.getMapper().insert(fieldDisplayScheme);
            }
            //再保存显示方案主表信息
            context.getMapper().insert(formDisplayScheme);
            //将各字段显示信息保存在主表显示方案中
            formDisplayScheme.setFieldDisplayList(fieldDisplayList);
        }
        return formDisplayScheme;
    }

}
