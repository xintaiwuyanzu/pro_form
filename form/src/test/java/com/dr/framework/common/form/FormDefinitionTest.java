package com.dr.framework.common.form;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.core.query.FormDefinitionQuery;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.engine.model.core.FieldType;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.page.Page;
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
        formDefinition.setFormCode("cesg");
        formDefinition.setFormName("文书档案");
        formDefinition.setFormType("gongwen");
        formDefinition.setExt1("wewweerwerwer");
        Collection<FieldModel> list = new ArrayList<>();
        FormField formField = new FormField();
        formField.setFieldCode("quanzong");
        formField.setLabel("全宗");
        formField.setExt1("扩展1");
        formField.setFieldTypeStrEnum(FieldType.STRING);
        formField.setFieldLength(200);
//        formField.setFieldAliasStr("quanzong");
        formField.setOrder(1);
        list.add(formField);
        FormField formField1 = new FormField();
        formField1.setFieldCode("niandu");
        formField1.setLabel("年度");
        formField1.setFieldTypeStrEnum(FieldType.NUMBER);
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
        formField.setFieldTypeStrEnum(FieldType.LONG);
        formDefinitionService.addField(definition.getId(), formField, true);
//        formDefinitionService.addField("276ad349-5b8e-4724-a3f6-6e3152ca5914", formField);


    }

    public FormField doCreateField() {
        FormField formField = new FormField();
        formField.setFieldCode("立档单位");
        formField.setLabel("lidangdanwei");
        formField.setFieldTypeStrEnum(FieldType.NUMBER);
//        formField.setFieldLength(300);
        return formField;
    }

    @Test
    public void addFieldByFormCode() {
        FormDefinition definition = doCreateFormDefinition(true);
        FormField formField = doCreateField();
        formDefinitionService.addFieldByFormCode(definition.getFormCode(), formField, true);
//        formDefinitionService.addField("276ad349-5b8e-4724-a3f6-6e3152ca5914", formField);
//        formDefinitionService.addFieldByFormCode("ws", 1, formField, true);
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
//        formDefinitionService.disableFieldByFormCode("ws", 10, "niandu");
//        formDefinitionService.enableFieldByFormCode("ws", "niandu");

        formDefinitionService.disableField("356f1d57-6969-4814-a608-a652c174cbef", "niandu");
//        formDefinitionService.enableField("","niandu");
    }

    @Test
    public void removeField() {
//        formDefinitionService.removeField("356f1d57-6969-4814-a608-a652c174cbef", "niandu");
        formDefinitionService.removeFieldByFormCode("ws", "niandu");
//        formDefinitionService.removeFieldByFormCode("ws",10,"niandu");

    }

    @Test
    public void removeFormDefinitionAllByCode() {
//        FormDefinition definition = doCreateFormDefinition(true);
//        FormField formField = doCreateField();
//        formDefinitionService.addFieldByFormCode("ws", formField, true);
//        formDefinitionService.removeFormDefinitionAllByCode("ws", true);
//        formDefinitionService.removeFormDefinitionAllByCode("ws");

//        formDefinitionService.removeFormDefinitionByCodeAndVersion("ws",1,true);
        formDefinitionService.removeFormDefinitionById("e6be1844-6c19-4624-9692-0bfd314d66ef", false);
    }

    @Test
    public void selectFieldByCode() {
//        this.removeFormDefinitionAllByCode();
//        FormField formField = (FormField) formDefinitionService.selectFieldByCode("2294b1aa-e0ec-4814-8aaf-3b6ef9e99aa5", "quanzong");
        FormField formField = (FormField) formDefinitionService.selectFieldByCodeAndVersion("ws", 2, "danghao");
        System.out.println(formField);
    }

    @Test
    public void selectFormDefinitionByCodeAndVersion() {
//        FormDefinition formDefinition = (FormDefinition) formDefinitionService.selectFormDefinitionByCodeAndVersion("ws", 1);
//        FormDefinition formDefinition = (FormDefinition) formDefinitionService.selectFormDefinitionById("23ec8c25-ad02-43f6-94f8-5b2c7f2f1c3c");
        FormDefinitionQuery formDefinitionQuery = new FormDefinitionQuery();
//        formDefinitionQuery.versionEqual(1);
        formDefinitionQuery.codeEqual("cs");
        formDefinitionQuery.versionAll();
//        List<FormDefinition> formDefinition = (List<FormDefinition>) formDefinitionService.selectFormDefinitionByQuery(formDefinitionQuery);

        Page<FormDefinition> page = (Page<FormDefinition>) formDefinitionService.selectPageFormDefinition(formDefinitionQuery, 0);
//        Page<FormDefinition> page = (Page<FormDefinition>) formDefinitionService.selectPageFormDefinition(formDefinitionQuery, 0, 5);

        System.out.println(page);

    }

    @Test
    public void updateFormDefinitionBaseInfo() {
        FormDefinition formDefinition = new FormDefinition();
        formDefinition.setId("89e97792-cee3-4a2e-84ac-00665314ab09");
        formDefinition.setFormName("文书类档案");
        formDefinition.setRemarks("sss");//TODO 未更新全部字段
        FormDefinition formDefinition2 = (FormDefinition) formDefinitionService.updateFormDefinitionBaseInfo(formDefinition);
        System.out.println(formDefinition2);
    }

    @Test
    public void add() {
        FormDefinition definition = doCreateFormDefinition(true);
        FormField formField = doCreateField();
        formDefinitionService.addFieldByFormCode(definition.getFormCode(), formField, true);
        //TODO 物理表已经存在，报异常，但是formdefiniton和formfield内容也添加成功了，未回滚
    }

    @Test
    public void deleteField() {
        //TODO 删除时也只能删除字段状态为1的？
        FormField formField = (FormField) formDefinitionService.removeField("4662d427-4d4a-4558-ad37-0476e8c75728", "责任人");
        System.out.println(formField);
    }

    @Test
    public void addFormTest() {
        FormDefinition formDefinition = new FormDefinition();
        formDefinition.setFormCode("xz");
        formDefinition.setFormName("行政类");
        formDefinition.setFormType("gongwen");
        Collection<FieldModel> list = new ArrayList<>();
        FormField formField = new FormField();
        formField.setFieldCode("quanzong");
        formField.setLabel("全宗");
        formField.setFieldTypeStrEnum(FieldType.STRING);
        formField.setFieldLength(200);
//        formField.setFieldAliasStr("quanzong");
        formField.setOrder(1);
        list.add(formField);
        FormField formField1 = new FormField();
        formField1.setFieldCode("niandu");
        formField1.setLabel("年度");
        formField1.setFieldTypeStrEnum(FieldType.LONG);
//        formField1.setFieldLength(10);
        formField1.setOrder(2);
//        formField1.setFieldAliasStr("niandu");
        list.add(formField1);
        FormDefinition form = (FormDefinition) formDefinitionService.addFormDefinition(formDefinition, list, true);
    }
}
