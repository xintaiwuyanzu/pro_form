package com.dr.framework.common.form.schema.service;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * jsonSchema组合
 * @author lc
 */
public interface CombinationSchemaService {

    /**
     * 根据表单定义Id重组JsonSchema数据
     *
     * @param formDefinitionId
     * @return
     */
    String combinationJson(String formDefinitionId) throws JsonProcessingException;




}
