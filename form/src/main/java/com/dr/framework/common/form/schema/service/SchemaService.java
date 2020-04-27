package com.dr.framework.common.form.schema.service;

import com.dr.framework.common.form.schema.entity.Constitute;

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


}

