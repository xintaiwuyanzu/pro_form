package com.dr.framework.common.form;

import com.dr.framework.common.form.schema.service.SchemaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SchemDataText extends AbstractApplicationTest {

    @Autowired
    SchemaService SchemaService;

    @Test
    public void getSchemaData() {
        String schema = "{\n" + "\"title\":\"A person\",\n" +
                "     \"description\":\"A person\",\n" +
                "     \"type\":\"object\",\n" +
                "\n" +
                "     \"properties\":{\n" +
                "       \"name\":{\"type\":\"string\"},\n" +
                "       \"age\" :{\n" +
                "           \"type\":\"integer\",\n" +
                "           \"maximum\":125\n" +
                "       }\n" +
                "     },\n" +
                "\"required\":[\n" + "\"name\",\n " +
                " \"age\",]" +
                "   }";
        SchemaService.analysisJsonSchema(schema);
    }


}
