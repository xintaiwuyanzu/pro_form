package com.dr.framework.common.form.schema.service.impl;

import com.dr.framework.common.form.schema.entity.Constitute;
import com.dr.framework.common.form.schema.service.SchemaService;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Service
public class SchemaServiceImpl implements SchemaService {

    @Override
    public Constitute analysisJsonSchema(String jsonSchema) {
        Assert.isTrue(!StringUtils.isEmpty(jsonSchema),"参数不能为空！");
        try {
            if ( verifyNode(jsonSchema)){
                JSONObject Schema = new JSONObject(jsonSchema);
                JSONArray JsonSchemas = Schema.getJSONArray("properties");
            }else{

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


}
