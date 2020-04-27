package com.dr.framework.common.form.schema.service.impl;

import com.dr.framework.common.form.schema.entity.Constitute;
import com.dr.framework.common.form.schema.service.SchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SchemaServiceImpl implements SchemaService {

    @Autowired
    CoreFormAutoConfig coreFormAutoConfig;

    /**
     * 解析传过来的JsonSchema数据
     *
     * @param jsonSchema
     * @return
     */
    @Override
    public Constitute analysisJsonSchema(String jsonSchema) {
        Assert.isTrue(!StringUtils.isEmpty(jsonSchema), "参数不能为空！");
        Assert.isTrue(verifyNode(jsonSchema), "jsonSchema数据缺少节点");
        //表单数据
        FormDefinition formDefinition = new FormDefinition();
        JSONObject jsonObject = JSONObject.parseObject(jsonSchema);
        formDefinition.setVersion("1");
        formDefinition.setFormCode(jsonObject.getString("title"));
        formDefinition.setFormTable(jsonObject.getString("title"));
        formDefinition.setDescription(jsonObject.getString("description"));
        formDefinition.setFormName(jsonObject.getString("title"));
        formDefinition.setFormType(jsonObject.getString("type"));
        formDefinition.setFormOrder(1);
        //解析properties
        JSONObject properties = jsonObject.getJSONObject("properties");
        //解析required
        JSONArray required = jsonObject.getJSONArray("required");
        List<Field> fields = new ArrayList<>();
        List<FormField> formFields = new ArrayList<>();
        for (int i = 0; i < required.size(); i++) {
            //表单字段
            FormField formField = new FormField();
            //根据required字段名称获取properties下的所有信息
            JSONObject value = properties.getJSONObject(required.getString(i));
            if (!value.isEmpty()) {
                formField.setVersion("1");
                formField.setFieldName(required.getString(i));
                formField.setFieldCode(required.getString(i));
                formField.setFieldType(value.getString("type"));
                formField.setDescription(value.getString("description"));
                //formField.setFieldLength(Integer.valueOf(value.getString("maximum")));
                formField.setFieldOrder(i + 1);
                formFields.add(formField);
                fields.add(formField);
            }
        }
        formDefinition.setFormFieldList(formFields);
        FormDefinitionService formDefinitionService = coreFormAutoConfig.formDefinitionService();
        formDefinitionService.addFormDefinition(formDefinition, fields, true);
        Constitute constitute = new Constitute();
        constitute.setFormDefinition(formDefinition);
        return constitute;
    }

    /**
     * 验证传过来的jsonSchema是否有这些数据节点
     *
     * @param jsonSchema
     * @return
     */
    @Override
    public boolean verifyNode(String jsonSchema) {
        if (jsonSchema.indexOf("title") == -1 ||
                jsonSchema.indexOf("description") == -1 ||
                jsonSchema.indexOf("type") == -1 ||
                jsonSchema.indexOf("properties") == -1 ||
                jsonSchema.indexOf("required") == -1) {
            return false;
        }
        return true;
    }

    @Override
    public Constitute getConstitute(Map schemaMap) {
        return null;
    }

}
