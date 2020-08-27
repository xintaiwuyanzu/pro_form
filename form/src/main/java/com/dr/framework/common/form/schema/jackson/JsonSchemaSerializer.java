package com.dr.framework.common.form.schema.jackson;

import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.display.config.JsonSchemaConfig;
import com.dr.framework.common.form.display.service.FormDisplayService;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.form.engine.model.display.FieldDisplay;
import com.dr.framework.common.form.schema.model.JsonSchema;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * 拦截解析jsonschema，生成标准jsonschema
 *
 * @author dr
 */
public class JsonSchemaSerializer extends JsonSerializer<JsonSchema> {
    private ApplicationContext applicationContext;
    private FormDisplayService formDisplayService;
    private FormDefinitionService formDefinitionService;
    private JsonSchemaConfig config;

    public JsonSchemaSerializer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        formDefinitionService = applicationContext.getBean(FormDefinitionService.class);
        formDisplayService = applicationContext.getBean(FormDisplayService.class);
        config = applicationContext.getBean(JsonSchemaConfig.class);
    }

    @Override
    public void serialize(JsonSchema value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            fillBaseInfo(value);
            //开始
            gen.writeStartObject();
            //声明基本信息
            gen.writeStringField("title", getString(value.getFormDisplay().getName(), value.getFormModel().getFormName()));
            if (value.getFormDisplay().getLabelWidth() != null) {
                gen.writeNumberField(config.getLabelWidthKey(), value.getFormDisplay().getLabelWidth());
            }
            gen.writeStringField("type", "object");
            gen.writeStringField("id", value.getFormDisplay().getId());
            gen.writeStringField("definitionId", value.getFormModel().getId());
            //序列化属性
            serialProperties(value, gen, serializers);
            //结束
            gen.writeEndObject();
        }
    }

    /**
     * 序列化
     *
     * @param value
     * @param gen
     * @param serializers
     */
    protected void serialProperties(JsonSchema value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeFieldName("properties");
        gen.writeStartObject();
        FormModel formModel = value.getFormModel();
        for (FieldDisplay f : value.getFormDisplay().getFields()) {
            FormField formField = formModel.getFieldByCode(f.getCode());
            if (formField == null) {
                formField = formModel.getFieldByAlias(f.getCode());
            }
            gen.writeFieldName(formField.getFieldCode());
            gen.writeStartObject();
            gen.writeStringField("title", getString(f.getName(), formField.getLabel()));
            gen.writeStringField("type", formField.getFieldType().name());
            gen.writeStringField(config.getWidgetKey(), f.getType());
            if (f.getLabelWidth() != null) {
                gen.writeNumberField(config.getLabelWidthKey(), f.getLabelWidth());
            }
            gen.writeEndObject();
        }
        gen.writeEndObject();
    }


    protected String getString(String... strings) {
        for (String s : strings) {
            if (!StringUtils.isEmpty(s)) {
                return s;
            }
        }
        return null;
    }

    /**
     * 填充基本信息
     *
     * @param value
     */
    protected void fillBaseInfo(JsonSchema value) {
        if (value.getFormModel() == null) {
            Assert.notNull(value.getFormDisplay(), "显示方案和表单定义不能同时为空！");
            value.setFormModel(formDefinitionService.selectFormDefinitionById(value.getFormDisplay().getFormDefinitionId()));
        }
    }
}
