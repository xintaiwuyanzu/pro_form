package com.dr.framework.common.form.command.method;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class FormAddFieldCommand implements Command {
    private String formType;

    public FormAddFieldCommand(String formType) {
        this.formType = formType;
    }

    @Override
    public Object execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(formType),"表单类型不能为空");
        //todo 根据表单类型添加 相应字段值

        return null;
    }


}