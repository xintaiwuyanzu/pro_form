package com.dr.framework.common.form;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.FieldType;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.service.DataBaseService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author caor
 * @date 2020/7/16 17:47
 */
@SpringBootTest(classes = FormApplication.class)
@RunWith(SpringRunner.class)
public class FormDefinitionTest {
    Logger logger = LoggerFactory.getLogger(AbstractApplicationTest.class);
    @Autowired
    CommandExecutor commandExecutor;
    @Autowired
    DataBaseService dataBaseService;
    @Autowired
    FormNameGenerator generator;

    @Autowired
    FormDefinitionService formDefinitionService;

    FormDefinition doCreateFormDefinition(boolean createTable) {
        FormDefinition formDefinition = new FormDefinition();
        formDefinition.setFormCode("ws");
        formDefinition.setFormName("文书档案");
        formDefinition.setFormType("gongwen");
        Collection<Field> list = new ArrayList<Field>();
        FormField formField = new FormField();
        formField.setFieldCode("quanzong");
        formField.setLabel("全宗");
        formField.setFieldTypeStr(FieldType.STRING);
        formField.setFieldLength(200);
//        formField.setFieldAliasStr("quanzong");
        formField.setOrder(1);
        list.add(formField);
        FormField formField1 = new FormField();
        formField1.setFieldCode("niandu");
        formField1.setLabel("年度");
        formField1.setFieldTypeStr(FieldType.NUMBER);
        formField1.setFieldLength(10);
        formField1.setOrder(2);
//        formField1.setFieldAliasStr("niandu");
        list.add(formField1);
        FormDefinition form = (FormDefinition) formDefinitionService.addFormDefinition(formDefinition, list, true);

        Assert.assertTrue("表单定义创建失败！", form != null);
        return form;
    }

    void dropTableById(FormDefinition formDefinition) {
        formDefinitionService.removeFormDefinitionById(formDefinition.getId(), true);
    }


    @Test
    public void addFormDefinitionTest() {
        FormDefinition formDefinition = doCreateFormDefinition(true);
        String genTableName = generator.genTableName(formDefinition);
        String tableName = "f-" + formDefinition.getFormCode() + "-1";

        Assert.assertTrue("表名称创建错误", tableName.equalsIgnoreCase(genTableName));
        Assert.assertTrue("表结构创建失败", dataBaseService.tableExist(tableName, Constants.MODULE_NAME));
//        dropTableById(formDefinition);
    }

    @Test
    public void addField() {
        FormDefinition definition = doCreateFormDefinition(true);

        FormField formField = new FormField();
        formField.setFieldCode("danghao");
        formField.setLabel("档号");
        formField.setFieldTypeStr(FieldType.LONG);
        formDefinitionService.addField(definition.getId(), formField, true);
//        formDefinitionService.addField("276ad349-5b8e-4724-a3f6-6e3152ca5914", formField);


    }

    public FormField doCreateField() {
        FormField formField = new FormField();
        formField.setFieldCode("quanzong");
        formField.setLabel("全宗2");
        formField.setFieldTypeStr(FieldType.NUMBER);
//        formField.setFieldLength(300);
        return formField;
    }

    @Test
    public void addFieldByFormCode() {
//        FormDefinition definition = doCreateFormDefinition(true);
        FormField formField = doCreateField();
//        formDefinitionService.addFieldByFormCode("ws", formField, true);
//        formDefinitionService.addField("276ad349-5b8e-4724-a3f6-6e3152ca5914", formField);
        formDefinitionService.addFieldByFormCode("ws", 1, formField, true);
    }

    @Test
    public void changeField() {
        FormField formField = doCreateField();
//        formDefinitionService.changeField("f583cac9-d59a-4359-b40b-e7b8687b75fb", formField, true);

        formDefinitionService.changeField("f583cac9-d59a-4359-b40b-e7b8687b75fb", formField, true);
    }

    @Test
    public void changeFieldByFormCode() {
        FormField formField = doCreateField();
        formDefinitionService.changeFieldByFormCode("ws", 1, formField, true);
    }

    @Test
    public void changeFieldStatus() {
        formDefinitionService.disableFieldByFormCode("ws", 10, "niandu");
//        formDefinitionService.enableFieldByFormCode("ws", "niandu");
    }


}
