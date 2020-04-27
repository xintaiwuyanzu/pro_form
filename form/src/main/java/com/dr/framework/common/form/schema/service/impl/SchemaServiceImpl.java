package com.dr.framework.common.form.schema.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dr.framework.common.form.autoconfig.CoreFormAutoConfig;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.init.entity.FormDefaultValue;
import com.dr.framework.common.form.schema.entity.Constitute;
import com.dr.framework.common.form.schema.service.SchemaService;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

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
        if (verifyNode(jsonSchema)) {
            //将jsonSchema数据转换成Json对象
            JSONObject jsonObject = JSONObject.parseObject(jsonSchema);
            //根据获取的数据，分离出来表单的定义数据
            FormDefinition formDefinition = getFormDefinition(jsonObject);
            //根据json对象获取校验数据
            ValidateDefinitionForm validateDefinitionForm = getValidateDefinitionForm(jsonObject);
            //根据json对象获取默认值数据
            FormDefaultValue formDefaultValue = getFormDefaultValue(jsonObject);
            //创建返回的类
            Constitute constitute = new Constitute(formDefinition, validateDefinitionForm, formDefaultValue);
            return constitute;
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
     * @param jsonObject
     * @return
     */
    public FormDefinition getFormDefinition(JSONObject jsonObject) {
        Collection<FormField> FormFields = new ArrayList<>();
        Collection<Field> fields = new ArrayList<>();
        FormDefinition formDefinition = new FormDefinition();
        formDefinition.setVersion("1");
        formDefinition.setFormCode(jsonObject.getString("title"));
        formDefinition.setFormTable(jsonObject.getString("title"));
        formDefinition.setDescription(jsonObject.getString("description"));
        formDefinition.setFormName(jsonObject.getString("title"));
        formDefinition.setFormType(jsonObject.getString("type"));
        formDefinition.setFormOrder(1);
        //解析properties
        if (jsonObject.getJSONObject("properties") != null) {
            JSONObject properties = jsonObject.getJSONObject("properties");
            //解析required 获取其中的字段数据
            JSONArray required = jsonObject.getJSONArray("required");
            if (required.size() > 0) {
                for (int i = 0; i < required.size(); i++) {
                    //根据required字段名称获取properties下的所有信息
                    JSONObject value = properties.getJSONObject(required.getString(i));
                    if (!value.isEmpty()) {
                        FormField formField = getFormFile(required, value, i);
                        FormFields.add(formField);
                        fields.add(formField);
                    }
                }
            }
        }
        formDefinition.setFormFieldList(FormFields);
        FormDefinitionService formDefinitionService = coreFormAutoConfig.formDefinitionService();
        formDefinitionService.addFormDefinition(formDefinition, fields, true);
        return formDefinition;
    }

    /**
     * 获取表单中的字段数据
     *
     * @param required
     * @param value
     * @param i
     * @return
     */
    public FormField getFormFile(JSONArray required, JSONObject value, int i) {
        FormField formField = new FormField();
        formField.setVersion("1");
        formField.setFieldName(required.getString(i));
        formField.setFieldCode(required.getString(i));
        formField.setFieldType(value.getString("type"));
        formField.setDescription(value.getString("description"));
        //formField.setFieldLength(Integer.valueOf(value.getString("maximum")));
        //TODO 补充相对应的字段

        formField.setFieldOrder(i + 1);
        return formField;
    }


    /**
     * 根据json对象获取需要校验的字段
     *
     * @param jsonObject
     * @return
     */
    public ValidateDefinitionForm getValidateDefinitionForm(JSONObject jsonObject) {
        //TODO 获取定义的校验规则

        return null;
    }


    /**
     * 根据 json 对象获取定义的默认值数据
     *
     * @param jsonObject
     * @return
     */
    public FormDefaultValue getFormDefaultValue(JSONObject jsonObject) {
        //TODO 获取定义的默认值

        return null;
    }

}
