package com.dr.framework.common.form.validate.plugin;

import com.dr.framework.common.form.core.command.FormDataInsertCommand;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.CommandPlugin;
import com.dr.framework.common.form.validate.model.ValidateException;
import com.dr.framework.common.form.validate.model.ValidateResults;
import com.dr.framework.common.form.validate.service.ValidateService;

public class ValidatePlugin implements CommandPlugin {
    ValidateService validateService;

    @Override
    public String type() {
        return "validate";
    }

    @Override
    public String description() {
        return "校验";
    }

    @Override
    public boolean accept(CommandContext context, Command command) {
        //拦截表单数据保存命令
        return command instanceof FormDataInsertCommand;
    }

    @Override
    public Command handle(CommandContext context, Command command) throws ValidateException {
        FormDataInsertCommand insertCommand = (FormDataInsertCommand) command;
        //获取需要保存的数据
        FormData data = insertCommand.getFormData();
        //从上下文中获取校验定义规则
        String validateDefinitionId = "";
        //使用validateService 执行定义规则的校验
        ValidateResults results = validateService.validate(validateDefinitionId, data);
        if (results.success()) {
            return command;
        } else {
            throw new ValidateException(results);
        }
    }
}
