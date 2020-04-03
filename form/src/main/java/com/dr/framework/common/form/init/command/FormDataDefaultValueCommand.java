package com.dr.framework.common.form.init.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.init.entity.FormDefaultValue;
import com.dr.framework.common.form.init.model.FieldDefault;
import com.dr.framework.common.form.init.service.FieldDefaultManager;
import com.dr.framework.common.form.init.service.FormDefaultValueService;
import org.springframework.beans.factory.annotation.Autowired;

public class FormDataDefaultValueCommand implements Command<FormData> {

    @Autowired
    FormDefinitionService formDefinitionService;

    @Autowired
    FormDefaultValueService formDefaultValueService;

    private String formDefinitionId;
    private String version;
    private String formDefaultValueId;


    public FormDataDefaultValueCommand(String formDefinitionId) {
        this(formDefinitionId, "default", "default");
    }

    public FormDataDefaultValueCommand(String formDefinitionId, String version) {
        this(formDefinitionId, version, "default");
    }

    public FormDataDefaultValueCommand(String formDefinitionId, String version, String formDefaultValueId) {
        this.formDefinitionId = formDefinitionId;
        this.version = version;
        this.formDefaultValueId = formDefaultValueId;
    }

    @Override
    public FormData execute(CommandContext context) {
        CommandExecutor executor = context.getExecutor();
        //查询表字段定义
        FormDefinition form = (FormDefinition) formDefinitionService.selectOneFormDefinition(formDefinitionId);
        //查询该表定义的默认值设置List
        FormDefaultValue formDefault = (FormDefaultValue) formDefaultValueService.SelectOneFormDefaultValue(formDefinitionId, formDefaultValueId);
        FormData data = new FormData(formDefinitionId, null);
        //遍历所有默认值定义，只需要处理定义的字段就可以了
        for (FieldDefault fieldDefault : formDefault.getFieldDefaultList()) {
            //获取所设置的默认值所对应的的字段定义
            Field field = form.getFieldByCode(fieldDefault.getFieldCode());
            Object fieldDefaultValue = getFieldDefaultValue(context, field, fieldDefault);
            data.put(field.getFieldName(), fieldDefaultValue);
        }
        return data;
    }

    private Object getFieldDefaultValue(CommandContext context, Field field, FieldDefault fieldDefault) {
        FieldDefaultManager manager = context.getApplicationContext().getBean(FieldDefaultManager.class);
        return manager.getDefaultValue(field, fieldDefault);
    }

    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    public String getVersion() {
        return version;
    }

    public String getFormDefaultValueId() {
        return formDefaultValueId;
    }
}
