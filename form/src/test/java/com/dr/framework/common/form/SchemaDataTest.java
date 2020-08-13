package com.dr.framework.common.form;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.display.entity.FieldDisplayScheme;
import com.dr.framework.common.form.display.entity.FormDisplayScheme;
import com.dr.framework.common.form.engine.model.core.FieldType;
import com.dr.framework.common.form.schema.model.JsonSchema;
import com.dr.framework.common.form.schema.service.CombinationSchemaService;
import com.dr.framework.common.form.schema.service.DecomposeSchemaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

public class SchemaDataTest extends AbstractApplicationTest {
    Logger logger = LoggerFactory.getLogger(SchemaDataTest.class);
    @Autowired
    DecomposeSchemaService decomposeSchemaService;
    @Autowired
    CombinationSchemaService combinationSchemaService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testJsonSchema() throws JsonProcessingException {
        FormField formField = new FormField();
        formField.setFieldCode("aa");
        formField.setLabel("label");
        formField.setFieldTypeStrEnum(FieldType.STRING);
        FormDefinition formDefinition = new FormDefinition();
        formDefinition.setFormName("setFormName");
        formDefinition.setFields(Arrays.asList(formField));


        FormDisplayScheme formDisplay = new FormDisplayScheme();

        FieldDisplayScheme fieldDisplay = new FieldDisplayScheme();
        fieldDisplay.setCode("aa");
        formDisplay.setFields(Arrays.asList(fieldDisplay));

        JsonSchema jsonSchema = new JsonSchema();
        jsonSchema.setFormModel(formDefinition);
        jsonSchema.setFormDisplay(formDisplay);

        logger.warn(objectMapper.writeValueAsString(jsonSchema));
        logger.warn(String.valueOf(objectMapper.readValue(objectMapper.writeValueAsString(jsonSchema), Map.class)));
    }

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
