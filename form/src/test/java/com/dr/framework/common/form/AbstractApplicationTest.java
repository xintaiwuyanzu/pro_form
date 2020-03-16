package com.dr.framework.common.form;

import com.dr.framework.common.form.command.entity.FormField;
import com.dr.framework.common.form.command.entity.WorkForm;
import com.dr.framework.common.form.command.method.WorkFormInsertCommand;
import com.dr.framework.common.form.command.method.WorkFormRemoveCommand;
import com.dr.framework.common.form.engine.CommandExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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
        WorkForm workForm = new WorkForm();
        workForm.setFormCode("fist");
        workForm.setFormName("456");
        workForm.setFormOrder(1);
        workForm.setFormTable("FistFrom");
        workForm.setFormState("0");
        workForm.setFormType("gongWen");
        List<FormField> list = new ArrayList<FormField>();
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
        commandExecutor.execute(new WorkFormInsertCommand(workForm, list, true));
    }

    @Test
    public void revomeDataTest(){
        String formId = "00a905ac-8e85-46a1-b699-9ef330e96131";
        commandExecutor.execute(new WorkFormRemoveCommand(formId,true));
    }
}
