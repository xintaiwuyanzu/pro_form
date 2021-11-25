package com.dr.framework.common.form.core.plugin.impl;

import com.dr.framework.common.form.core.command.FormDefinitionInsertCommand;
import com.dr.framework.common.form.core.service.FormDefinitionTypeProvider;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.CommandPlugin;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.engine.model.core.FormModel;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 根据表单类型自动创建对应字段
 *
 * @author dr
 */
public class FormTypeInitFieldsPlugin implements CommandPlugin {
    private Map<String, FormDefinitionTypeProvider> formDefinitionTypeProviders;

    public FormTypeInitFieldsPlugin(List<FormDefinitionTypeProvider> providerList) {
        if (providerList != null) {
            this.formDefinitionTypeProviders = Collections.synchronizedMap(new HashMap<>(providerList.size()));
            providerList.forEach(p -> this.formDefinitionTypeProviders.put(p.type(), p));
        } else {
            this.formDefinitionTypeProviders = Collections.EMPTY_MAP;
        }
    }

    @Override
    public boolean accept(CommandContext context, Command command) {
        return command instanceof FormDefinitionInsertCommand;
    }

    /**
     * 拦截表单创建命令，根据表单定义类型自动追加默认字段
     *
     * @param context
     * @param command
     * @return
     * @throws Exception
     */
    @Override
    public Command handle(CommandContext context, Command command) {
        FormDefinitionInsertCommand insertCommand = (FormDefinitionInsertCommand) command;
        FormModel formModel = insertCommand.getForm();
        String formDefinitionType = formModel.getFormType();
        if (StringUtils.hasText(formDefinitionType)) {
            FormDefinitionTypeProvider provider = formDefinitionTypeProviders.get(formDefinitionType);
            if (provider != null) {
                Collection<FieldModel> fieldModels = insertCommand.getFields();
                Collection<String> definitionFieldNames = fieldModels.stream().map(FieldModel::getFieldCode).collect(Collectors.toList());
                provider.getInitFields().stream()
                        .filter(f -> !definitionFieldNames.contains(f.getFieldCode()))
                        .forEach(fieldModels::add);
            }
        }
        return command;
    }

    @Override
    public String type() {
        return "initFormDefinitionFields";
    }

    @Override
    public String description() {
        return "根据表单类型追加默认字段";
    }
}
