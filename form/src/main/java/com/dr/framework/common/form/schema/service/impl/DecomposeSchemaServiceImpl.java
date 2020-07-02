package com.dr.framework.common.form.schema.service.impl;

import com.dr.framework.common.form.autoconfig.CoreFormAutoConfig;
import com.dr.framework.common.form.autoconfig.InitFormAutoConfig;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.init.entity.FieldDefaultValue;
import com.dr.framework.common.form.init.entity.FormDefaultValue;
import com.dr.framework.common.form.init.model.FieldDefault;
import com.dr.framework.common.form.init.service.FormDefaultValueService;
import com.dr.framework.common.form.schema.entity.Constitute;
import com.dr.framework.common.form.schema.service.DecomposeSchemaService;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@Service
public class DecomposeSchemaServiceImpl implements DecomposeSchemaService {
    Logger logger = LoggerFactory.getLogger(DecomposeSchemaService.class);
    @Autowired
    CoreFormAutoConfig coreFormAutoConfig;

    @Autowired
    InitFormAutoConfig initFormAutoConfig;
    @Autowired
    ObjectMapper objectMapper;

    /**
     * 解析传过来的JsonSchema数据
     *
     * @param jsonSchema
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Constitute analysisJsonSchema(String jsonSchema) {
        Assert.isTrue(!StringUtils.isEmpty(jsonSchema), "参数不能为空！");
        if (verifyNode(jsonSchema)) {
            try {
                //将jsonSchema数据转换成Json对象
                JsonNode jsonNode = objectMapper.readTree(jsonSchema);
                //根据获取的数据，分离出来表单的定义数据
                Form form = getFormDefinition(jsonNode);
                //根据json对象获取校验数据
                ValidateDefinitionForm validateDefinitionForm = getValidateDefinitionForm(jsonNode, form.getId());
                //根据json对象获取默认值数据
                FormDefaultValue formDefaultValue = getFormDefaultValue(jsonNode, form.getId());
                //创建返回的类
                Constitute constitute = new Constitute(form, new ValidateDefinitionForm(), formDefaultValue);
                return constitute;
            } catch (JsonProcessingException e) {
                logger.error("解析schema失败", e);
            }
        }
        return null;
    }

    /**
     * 验证传过来的jsonSchema是否有这些数据节点
     *
     * @param jsonSchema
     * @return
     */
    @Override
    public boolean verifyNode(String jsonSchema) {
        Assert.isTrue(jsonSchema.indexOf("title") != -1, "jsonSchema数据缺少title节点");
        Assert.isTrue(jsonSchema.indexOf("type") != -1, "jsonSchema数据缺少type节点");
        Assert.isTrue(jsonSchema.indexOf("properties") != -1, "jsonSchema数据缺少properties节点");
        Assert.isTrue(jsonSchema.indexOf("required") != -1, "jsonSchema数据缺少required节点");
        Assert.isTrue(jsonSchema.indexOf("description") != -1, "jsonSchema数据缺少description节点");
        //TODO 验证jsonSchema 是否符合标注

        return true;
    }

    /**
     * 获取JsonSchema的类
     *
     * @param schemaMap
     * @return
     */
    @Override
    public Constitute getConstitute(Map schemaMap) {
        return null;
    }


    /**
     * 根根json对象获取表单定义数据
     *
     * @param jsonNode
     * @return
     */
    protected Form getFormDefinition(JsonNode jsonNode) {
        Collection<FormField> formFields = new ArrayList<>();
        Collection<Field> fields = new ArrayList<>();
        FormDefinition formDefinition = new FormDefinition();
        formDefinition.setVersion("1");
        formDefinition.setFormCode(jsonNode.get("title").asText());
        formDefinition.setFormTable(jsonNode.get("title").asText());
        formDefinition.setDescription(jsonNode.get("description").asText());
        formDefinition.setFormName(jsonNode.get("title").asText());
        formDefinition.setFormType(jsonNode.get("type").asText());
        formDefinition.setFormOrder(1);
        if (jsonNode.has("properties")) {
            //解析properties
            JsonNode properties = jsonNode.get("properties");
            //解析required 获取其中的字段数据
            JsonNode required = jsonNode.get("required");
            if (!required.isEmpty()) {
                for (int i = 0; i < required.size(); i++) {
                    //根据required字段名称获取properties下的所有信息
                    JsonNode value = properties.get(required.get(i).asText());
                    if (!value.isEmpty()) {
                        FormField formField = getFormFile(required, value, i);
                        formFields.add(formField);
                        fields.add(formField);
                    }
                }
            }
        }
        formDefinition.setFormFieldList(formFields);
        FormDefinitionService formDefinitionService = coreFormAutoConfig.formDefinitionService();
        Form form = formDefinitionService.addFormDefinition(formDefinition, fields, true);
        return form;
    }

    /**
     * 获取表单中的字段数据
     *
     * @param required
     * @param value
     * @param i
     * @return
     */
    public FormField getFormFile(JsonNode required, JsonNode value, int i) {
        FormField formField = new FormField();
        formField.setVersion("1");
        formField.setFieldName(required.get(i).asText());
        formField.setFieldCode(required.get(i).asText());
        formField.setFieldType(value.get("type").asText());
        formField.setDescription(value.has("description") ? value.get("description").asText() : null);
        formField.setFieldLength(Integer.valueOf(value.get("maxLength").asText()));
        //TODO 补充相对应的字段
        formField.setFieldState("0");
        formField.setFieldOrder(i + 1);
        return formField;
    }


    /**
     * 根据json对象获取需要校验的字段
     *
     * @param jsonObject
     * @param formDefinitionId
     * @return
     */
    public ValidateDefinitionForm getValidateDefinitionForm(JsonNode jsonObject, String formDefinitionId) {
        //TODO 获取定义的校验规则


        return null;
    }


    /**
     * 根据 json 对象获取定义的默认值数据
     *
     * @param jsonObject
     * @param formDefinitionId
     * @return
     */
    public FormDefaultValue getFormDefaultValue(JsonNode jsonObject, String formDefinitionId) {
        Collection<FieldDefaultValue> fieldDefaultValues = new ArrayList<>();
        Collection<FieldDefault> fieldDefaults = new ArrayList<>();
        FormDefaultValue formDefaultValue = new FormDefaultValue();
        formDefaultValue.setId(UUID.randomUUID().toString());
        formDefaultValue.setVersion("1");
        formDefaultValue.setFormDefinitionId(formDefinitionId);
        formDefaultValue.setLinkCode(jsonObject.get("title").asText());
        formDefaultValue.setDescription(jsonObject.get("description").asText());
        formDefaultValue.setLinkName(jsonObject.get("title").asText());
        formDefaultValue.setDefaultType(jsonObject.get("type").asText());
        formDefaultValue.setVersion("1");
        if (jsonObject.has("properties")) {
            //解析properties
            JsonNode properties = jsonObject.get("properties");
            //解析required 获取其中的字段数据
            JsonNode required = jsonObject.get("required");
            if (!required.isEmpty()) {
                for (int i = 0; i < required.size(); i++) {
                    //根据required字段名称获取properties下的所有信息
                    JsonNode value = properties.get(required.get(i).asText());
                    if (!value.isEmpty() && value.has("default")) {
                        FieldDefaultValue fieldDefault = getFieldDefault(required, value, i, formDefinitionId, formDefaultValue.getId());
                        fieldDefaults.add(fieldDefault);
                        fieldDefaultValues.add(fieldDefault);
                    }
                }
            }
        }
        FormDefaultValueService formDefaultValueService = initFormAutoConfig.formDefaultValueService();
        formDefaultValueService.addFormDefaultValue(formDefaultValue, fieldDefaults);
        formDefaultValue.setFieldDefaultList(fieldDefaultValues);
        return formDefaultValue;
    }

    /**
     * 获取字段默认值
     *
     * @param required
     * @param value
     * @param i
     * @param formDefinitionId
     * @param formDefaultId
     * @return
     */
    private FieldDefaultValue getFieldDefault(JsonNode required, JsonNode value, int i, String formDefinitionId, String formDefaultId) {
        FieldDefaultValue fieldDefaultValue = new FieldDefaultValue();
        fieldDefaultValue.setId(UUID.randomUUID().toString());
        fieldDefaultValue.setFormDefaultValueId(formDefaultId);
        fieldDefaultValue.setFormDefinitionId(formDefinitionId);
        fieldDefaultValue.setFieldCode(required.get(i).asText());
        fieldDefaultValue.setFieldName(required.get(i).asText());
        fieldDefaultValue.setFieldType(value.get("type").asText());
        fieldDefaultValue.setDefaultValue(value.get("default").asText());
        return fieldDefaultValue;
    }

}
