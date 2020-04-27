package com.dr.framework.common.form.schema.service;

import com.dr.framework.common.form.schema.entity.Constitute;

import java.util.Map;

public interface SchemaService {

    /**
     * 解析传过来的JsonSchema数据
     *
     * @param jsonSchema
     */
    Constitute analysisJsonSchema(String jsonSchema);

    /**
     * 验证传过来的jsonSchema是否有这些数据节点
     *
     * @param jsonSchema
     * @return
     */
    boolean verifyNode(String jsonSchema);

    /**
     * 根据解析出来的数据进行创建
     *
     * @param schemaMap
     * @return
     */
    Constitute getConstitute(Map schemaMap);


}

