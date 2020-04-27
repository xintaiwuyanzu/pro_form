package com.dr.framework.common.form.schema.service.impl;

import com.dr.framework.common.form.autoconfig.CoreFormAutoConfig;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.schema.entity.Constitute;
import com.dr.framework.common.form.schema.service.SchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class SchemaServiceImpl implements SchemaService {

    @Autowired


    @Override
    public String analysisJsonSchema(String jsonSchema) {
        Assert.isTrue(!StringUtils.isEmpty(jsonSchema), "参数不能为空！");
        try {
            if (verifyNode(jsonSchema)) {
                JSONObject Schema = new JSONObject(jsonSchema);
                JSONArray JsonSchemas = Schema.getJSONArray("properties");
            } else {

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean verifyNode(String jsonSchema) {
        return false;
    }

    @Override
    public Constitute getConstitute(Map schemaMap) {
        return null;
    }

}
