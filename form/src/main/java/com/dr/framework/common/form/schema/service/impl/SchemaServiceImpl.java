package com.dr.framework.common.form.schema.service.impl;

import com.dr.framework.common.form.schema.entity.Constitute;
import com.dr.framework.common.form.schema.service.SchemaService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class SchemaServiceImpl implements SchemaService {

    @Override
    public Constitute analysisJsonSchema(String jsonSchema) {
        try {
            JSONObject Schema = new JSONObject(jsonSchema);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
