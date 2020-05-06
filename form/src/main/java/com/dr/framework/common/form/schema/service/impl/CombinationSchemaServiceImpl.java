package com.dr.framework.common.form.schema.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dr.framework.common.form.autoconfig.CoreFormAutoConfig;
import com.dr.framework.common.form.autoconfig.InitFormAutoConfig;
import com.dr.framework.common.form.autoconfig.ValidateAutoConfig;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.init.service.FormDefaultValueService;
import com.dr.framework.common.form.schema.service.CombinationSchemaService;
import com.dr.framework.common.form.validate.service.ValidateDefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CombinationSchemaServiceImpl implements CombinationSchemaService {

    @Autowired
    CoreFormAutoConfig coreFormAutoConfig;
    @Autowired
    InitFormAutoConfig initFormAutoConfig;
    @Autowired
    ValidateAutoConfig validateAutoConfig;

    @Override
    public String combinationJson(String formDefinitionId) {
        Assert.isTrue(!StringUtils.isEmpty(formDefinitionId), "表单定义id不能为空！");
        FormDefinitionService formDefinitionService = coreFormAutoConfig.formDefinitionService();
        FormDefinition formDefinition = (FormDefinition) formDefinitionService.selectOneFormDefinition(formDefinitionId);
        JSONObject jsonSchema = new JSONObject();
        jsonSchema.put("id", formDefinition.getId());
        jsonSchema.put("title", formDefinition.getFormTable());
        jsonSchema.put("uiId", "");
        jsonSchema.put("validateId", "");
        jsonSchema.put("defaultId", "");
        jsonSchema.put("description", formDefinition.getDescription());

        Collection<FormField> listFiled = formDefinition.getFormFieldList();
        if (listFiled.size() > 0) {
            JSONArray required = new JSONArray();
            JSONObject properties = new JSONObject();
            for (FormField FormField : listFiled) {
                Map map = getJsonMap(FormField);
                properties.put(FormField.getFieldCode(), map);
                required.add(FormField.getFieldCode());
            }
            jsonSchema.put("required", required);
            jsonSchema.put("properties", properties);
        }
        return jsonSchema.toString();
    }

    /**
     * 获取需要拼接的值
     *
     * @param formField
     * @return
     */
    public Map getJsonMap(FormField formField) {
        Map map = new HashMap();
        map.put("title", formField.getFieldName());
        map.put("type", formField.getFieldType());
        map.put("maxLength", formField.getFieldLength());
        map.put("default", "");

        //根据表单定义iD查询表单默认值 ，并拼接在返回的类中
        FormDefaultValueService formDefaultValueService = initFormAutoConfig.formDefaultValueService();
        //FormDefaultValue formDefaultValue = formDefaultValueService.SelectOneFormDefaultValue(formField.getFormDefinitionId())
        //根据表单定义iD查询表单默认值 ，并拼接在返回的类中
        ValidateDefaultService  validateDefaultService = validateAutoConfig.validateDefaultService();
        //ValidateDefinitionForm validateDefinitionForm = validateDefaultService.SelectOneValidateDefinitionForm(formField.getFormDefinitionId())

        return map;
    }
}
