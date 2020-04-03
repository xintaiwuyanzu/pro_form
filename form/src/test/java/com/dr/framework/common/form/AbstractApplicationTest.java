package com.dr.framework.common.form;

import com.dr.framework.common.form.core.command.FormDefinitionInsertCommand;
import com.dr.framework.common.form.core.command.FormDefinitionRemoveCommand;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.model.Field;
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

    @Test
    public void testInit() {
        logger.info(commandExecutor.toString());
    }

    @Test
    public void addDataTest() {
        FormDefinition formDefinition = new FormDefinition();
        formDefinition.setFormCode("fist");
        formDefinition.setFormName("456");
        formDefinition.setFormOrder(1);
        formDefinition.setFormTable("FistFrom");
        formDefinition.setFormState("0");
        formDefinition.setFormType("gongWen");
        Collection<Field> list = new ArrayList<Field>();
        FormField formField = new FormField();
        formField.setFieldCode("text001");
        formField.setFieldName("ceshi");
        formField.setFieldLength(300);
        formField.setFieldOrder(1);
        formField.setFieldState("0");
        formField.setFieldValue("textceshi");
        list.add(formField);
        FormField formField1 = new FormField();
        formField1.setFieldCode("id");
        formField1.setFieldName("text");
        formField1.setFieldLength(50);
        formField1.setFieldOrder(1);
        formField1.setFieldState("0");
        formField1.setFieldValue("ceshi");
        list.add(formField1);
        commandExecutor.execute(new FormDefinitionInsertCommand(formDefinition, list, true, true));
    }

    @Test
    public void revomeDataTest() {
        String formId = "00a905ac-8e85-46a1-b699-9ef330e96131";
        commandExecutor.execute(new FormDefinitionRemoveCommand(formId, true));
    }
}
