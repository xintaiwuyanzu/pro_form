package com.dr.framework.common.form.schema.service.impl;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.init.service.FormDefaultValueService;
import com.dr.framework.common.form.schema.service.CombinationSchemaService;
import com.dr.framework.common.form.validate.service.ValidateDefaultService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Collection;

@Service
public class CombinationSchemaServiceImpl implements CombinationSchemaService {
    @Autowired
    FormDefinitionService formDefinitionService;
    @Autowired
    FormDefaultValueService formDefaultValueService;
    @Autowired
    ValidateDefaultService validateDefaultService;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String combinationJson(String formDefinitionId) throws JsonProcessingException {
        Assert.isTrue(!StringUtils.isEmpty(formDefinitionId), "表单定义id不能为空！");
        FormDefinition formDefinition = (FormDefinition) formDefinitionService.selectFormDefinitionById(formDefinitionId);
        ObjectNode jsonNode = objectMapper.createObjectNode();

        jsonNode.put("id", formDefinition.getId());
        jsonNode.put("title", formDefinition.getFormTable());
        jsonNode.put("uiId", "");
        jsonNode.put("validateId", "");
        jsonNode.put("defaultId", "");
        jsonNode.put("description", formDefinition.getDescription());

        Collection<FormField> listFiled = formDefinition.getFormFieldList();
        if (listFiled.size() > 0) {
            ArrayNode required = objectMapper.createArrayNode();
            ObjectNode properties = objectMapper.createObjectNode();
            for (FormField FormField : listFiled) {
                properties.set(FormField.getFieldCode(), getJsonMap(FormField));
                required.add(FormField.getFieldCode());
            }
            jsonNode.set("required", required);
            jsonNode.set("properties", properties);
        }
        return objectMapper.writeValueAsString(jsonNode);
    }

    /**
     * 获取需要拼接的值
     *
     * @param formField
     * @return
     */
    protected ObjectNode getJsonMap(FormField formField) {
        ObjectNode map = objectMapper.createObjectNode();
        // map.put("type", formField.getFieldType());
        map.put("maxLength", formField.getFieldLength());
        map.put("default", "");

        //根据表单定义iD查询表单默认值 ，并拼接在返回的类中
        //FormDefaultValue formDefaultValue = formDefaultValueService.SelectOneFormDefaultValue(formField.getFormDefinitionId())
        //根据表单定义iD查询表单默认值 ，并拼接在返回的类中
        //ValidateDefinitionForm validateDefinitionForm = validateDefaultService.SelectOneValidateDefinitionForm(formField.getFormDefinitionId())
        return map;
    }
}
