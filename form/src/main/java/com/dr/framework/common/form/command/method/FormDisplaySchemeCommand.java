package com.dr.framework.common.form.command.method;

import com.dr.framework.common.form.command.entity.FieldDisplayScheme;
import com.dr.framework.common.form.command.entity.FormDisplayScheme;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.List;
import java.util.UUID;

public class FormDisplaySchemeCommand implements Command<FormDisplayScheme> {

    private FormDisplayScheme formDisplayScheme;

    private List<FieldDisplayScheme> fieldDisplayList;

    public FormDisplaySchemeCommand(FormDisplayScheme formDisplayScheme, List<FieldDisplayScheme> fieldDisplayList) {
        this.formDisplayScheme = formDisplayScheme;
        this.fieldDisplayList = fieldDisplayList;
    }

    @Override
    public FormDisplayScheme execute(CommandContext context) {
        Assert.notNull(formDisplayScheme, "显示方案不能为空");
        Assert.isTrue(StringUtils.isNotEmpty(formDisplayScheme.getFormId()), "formId不能为空");
        if (StringUtils.isEmpty(formDisplayScheme.getId())) {
            formDisplayScheme.setId(UUID.randomUUID().toString());
        }
        //先保存显示方案主表信息
        context.getMapper().insert(formDisplayScheme);
        if (fieldDisplayList.size() > 0) {
            for (FieldDisplayScheme fieldDisplayScheme : fieldDisplayList) {
                fieldDisplayScheme.setFormDisplayId(formDisplayScheme.getId());
                fieldDisplayScheme.setFormId(formDisplayScheme.getFormId());
                if (StringUtils.isEmpty(fieldDisplayScheme.getId())) {
                    fieldDisplayScheme.setId(UUID.randomUUID().toString());
                }
                context.getMapper().insert(fieldDisplayScheme);
            }
            //将各字段显示信息保存在主表显示方案中
            formDisplayScheme.setFieldDisplayList(fieldDisplayList);
        }
        return formDisplayScheme;
    }

}
