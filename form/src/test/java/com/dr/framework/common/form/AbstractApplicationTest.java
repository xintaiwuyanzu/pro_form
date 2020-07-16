package com.dr.framework.common.form;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.FieldType;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.engine.CommandExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootTest(classes = FormApplication.class)
@RunWith(SpringRunner.class)
public class AbstractApplicationTest {
    Logger logger = LoggerFactory.getLogger(AbstractApplicationTest.class);

    @Autowired
    CommandExecutor commandExecutor;

    @Autowired
    FormDefinitionService formDefinitionService;

    @Test
    public void testInit() {
        logger.info(commandExecutor.toString());
    }

    @Test
    public void addDataTest() {
        FormDefinition formDefinition = new FormDefinition();
        formDefinition.setFormCode("fist");
        formDefinition.setFormName("456");
        formDefinition.setOrder(1);
        formDefinition.setFormTable("FistFrom");
        formDefinition.setStatus("0");
        formDefinition.setFormType("gongWen");
        formDefinition.setVersion(2);
        Collection<Field> list = new ArrayList<Field>();
        FormField formField = new FormField();
        formField.setFieldCode("text001");
        formField.setLabel("测试");
        formField.setFieldTypeStr(FieldType.STRING);
        formField.setFieldLength(300);
        formField.setOrder(1);
        formField.setStatus("0");
        list.add(formField);
        FormField formField1 = new FormField();
        formField1.setFieldCode("id");
        formField1.setLabel("试试");
        formField1.setFieldTypeStr(FieldType.STRING);
        formField1.setFieldLength(50);
        formField1.setOrder(1);
        formField1.setStatus("0");
        list.add(formField1);
        FormField formField2 = new FormField();
        formField2.setFieldCode("formDefinitionId");
        formField2.setLabel("试试");
        formField2.setFieldTypeStr(FieldType.STRING);
        formField2.setFieldLength(50);
        formField2.setOrder(1);
        formField2.setStatus("0");
        list.add(formField2);
        formDefinitionService.addFormDefinition(formDefinition, list, true);
    }

    @Test
    public void revomeDataTest() {
        String formId = "369363b0-ae13-4193-9626-1fccc7a4d69b";
        formDefinitionService.removeFormDefinitionById(formId, false);
    }

    @Test
    public void SelectOneForm() {
        String formId = "29eba72e-e041-4b3a-90e2-1b49b8bc302e";
        Form from = formDefinitionService.selectFormDefinitionById(formId);
    }

    @Test
    public void selectForm() {
        String formId = "29eba72e-e041-4b3a-90e2-1b49b8bc302e";
        String formType = "gongWen";
    }

    @Test
    public void selectPage() {
        FormDefinition formDefinition = new FormDefinition();
        formDefinition.setFormType("gongWen");
        formDefinition.setFormCode("fist");
    }


}
