package com.dr.framework.common.form;

import com.dr.framework.common.form.schema.service.CombinationSchemaService;
import com.dr.framework.common.form.schema.service.DecomposeSchemaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SchemaDataText extends AbstractApplicationTest {

    @Autowired
    DecomposeSchemaService decomposeSchemaService;
    @Autowired
    CombinationSchemaService combinationSchemaService;


    @Test
    public void getSchemaData() {
        String schema = "{\n" +
                "  \"title\": \"fistForm\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"id\": \"这个是主键\",\n" +
                "  \"uiId\":\"显示方案Id\",\n" +
                "  \"validateId\":\"校验方案Id\",\n" +
                "  \"defaultId\":\"默认值方案Id\",\n" +
                "  \"description\": \"这个是描述\",\n" +
                "  \"properties\": {\n" +
                "    \"id\": {\n" +
                "      \"title\": \"主键\",\n" +
                "      \"type\": \"pk\",\n" +
                "      \"maxLength\": 50\n" +
                "    },\n" +
                "    \"name\": {\n" +
                "      \"title\": \"名称\",\n" +
                "      \"type\": \"string\",\n" +
                "      \"maxLength\": 100,\n" +
                "      \"default\": \"登录人员\"\n" +
                "    },\n" +
                "    \"age\": {\n" +
                "      \"title\": \"岁数\",\n" +
                "      \"type\": \"string\",\n" +
                "      \"maxLength\": 50,\n" +
                "      \"default\": \"20\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"required\": [\"id\",\"name\",\"age\"]\n" +
                "}";
        decomposeSchemaService.analysisJsonSchema(schema);
    }

    @Test
    public void getSchemaDatas() throws JsonProcessingException {
        combinationSchemaService.combinationJson("122dcd99-80ca-4fe7-a37a-64e55e117e37");
    }


}
