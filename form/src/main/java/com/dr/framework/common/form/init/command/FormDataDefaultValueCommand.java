package com.dr.framework.common.form.init.command;

import com.dr.framework.common.form.core.command.AbstractFormDefinitionIdCommand;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.init.entity.FormDefaultValue;
import com.dr.framework.common.form.init.model.FieldDefault;
import com.dr.framework.common.form.init.service.FieldDefaultManager;

import java.io.Serializable;
import java.util.UUID;

public class FormDataDefaultValueCommand extends AbstractFormDefinitionIdCommand<FormData> {
    private String formDefaultValueId;


    public FormDataDefaultValueCommand(String formDefinitionId, String formDefaultValueId) {
        super(formDefinitionId);
        this.formDefaultValueId = formDefaultValueId;
    }

    public FormDataDefaultValueCommand(String formDefinitionId, String version, String formDefaultValueId) {
        super(version, formDefinitionId);
        this.formDefaultValueId = formDefaultValueId;
    }

    @Override
    public FormData execute(CommandContext context) {
        CommandExecutor executor = context.getExecutor();
        //查询表字段定义
        FormDefinition form = (FormDefinition) context.getFormDefinitionService().selectOneFormDefinition(getFormDefinitionId());
        //查询该表定义的默认值设置List
        FormDefaultValue formDefault = (FormDefaultValue) context.getFormDefaultValueService().SelectOneFormDefaultValue(getFormDefinitionId(), formDefaultValueId);
        FormData data = new FormData(getFormDefinitionId(), null);
        data.put("formDefinitionId",getFormDefinitionId());
        data.put("formDataId", UUID.randomUUID().toString());
        //遍历所有默认值定义，只需要处理定义的字段就可以了
        for (FieldDefault fieldDefault : formDefault.getFieldDefaultList()) {
            //获取所设置的默认值所对应的的字段定义
            Field field = form.getFieldByCode(fieldDefault.getFieldCode());
            Serializable fieldDefaultValue = getFieldDefaultValue(context, field, fieldDefault);
            data.put(field.getFieldName(), fieldDefaultValue);
        }
        return data;
    }

    private Serializable getFieldDefaultValue(CommandContext context, Field field, FieldDefault fieldDefault) {
        FieldDefaultManager manager = context.getApplicationContext().getBean(FieldDefaultManager.class);
        return manager.getDefaultValue(field, fieldDefault);
    }

    public String getFormDefaultValueId() {
        return formDefaultValueId;
    }

}
