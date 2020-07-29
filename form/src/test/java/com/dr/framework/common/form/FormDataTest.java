package com.dr.framework.common.form;

import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.FieldType;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.model.FormRelationWrapper;
import com.dr.framework.common.form.core.service.FormDataService;
import com.dr.framework.common.form.core.service.SqlBuilder;
import com.dr.framework.common.page.Page;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = FormApplication.class)
@RunWith(SpringRunner.class)
public class FormDataTest {
    @Autowired
    FormDataService formDataService;

    public static void main(String[] args) {
        MultiKeyMap<String, String> keyMap = new MultiKeyMap();
        keyMap.put("a", "b", "c");
        System.out.println(keyMap);
        System.out.println(keyMap.containsKey("a", "b"));
        System.out.println(keyMap.get("a"));
    }

    @Test
    public void addFormData() {
        FormData formData = new FormData("a4b72521-7492-4803-81f5-acc8c49daf85", UUID.randomUUID().toString());
//        formData.put("id", UUID.randomUUID().toString());
//        formData.put("quanzong1", "XZ");
        formData.put("quanzong", "SDDR");
        formData.put("niandu", 22);
//        formData.put("niandu", "测试");
//        formData.put("niandu", "1111111111");
//        formData.put("niandu", 1111111111);
        formDataService.addFormData(formData);
    }

    @Test
    public void removeFormData() {
//        Long num = formDataService.removeFormData("b35fe585-a45f-4541-972c-25bf9c694ab0", "ba11aee0-cf4d-41a3-bab9-150dd6fab9a8");
//        Long num = formDataService.removeFormDataByFormCode("cs", 2, "ba11aee0-cf4d-41a3-bab9-150dd6fab9a8");
        Long num = formDataService.removeFormDataByFormCode("cs", 2, (sqlQuery, formRelationWrapper) -> {
            sqlQuery.equal(formRelationWrapper.idColumn(), "cf27c3e8-31cd-40be-a315-6771e95ef0b3");
        });
        System.out.println(num);
    }

    @Test
    public void selectFormData() {
        /*SqlBuilder sqlBuilder = (sqlQuery, formRelationWrapper) -> {
            sqlQuery.isNull(formRelationWrapper.getColumn("quanzong"));
//                sqlQuery.equal(formRelationWrapper.getColumn("QUANZONG"), "");
        };
//        List<FormData> formDataList = formDataService.selectFormData("b35fe585-a45f-4541-972c-25bf9c694ab0", sqlBuilder);

        List<FormData> formDataList = formDataService.selectFormDataByFormCode("cs", sqlBuilder);
        System.out.println(formDataList);*/

        /*FormData formData = formDataService.selectOneFormData("b35fe585-a45f-4541-972c-25bf9c694ab0", "3bd10487-ac17-4d15-a35e-71652c082f29");
        formData.getFormCode();
        FormField formField = new FormField();
        formField.setFieldCode("danghao");
        formField.setFieldTypeStr(FieldType.STRING);
        String danghao = formData.getString("danghao");
        Object object = formData.getFieldValue(formField);

        System.out.println(formData);*/
//        FormData formData = formDataService.selectOneFormDataByFormCode("cs",2, "a44896fa-4c57-4cf6-981a-1fe57d40c966");
        /*FormData formData = formDataService.selectOneFormDataByFormCode("cs", "a44896fa-4c57-4cf6-981a-1fe57d40c966");
        System.out.println(formData);*/

//        Page<FormData> formDataPage = formDataService.selectPageFormData("b35fe585-a45f-4541-972c-25bf9c694ab0", (sqlQuery, formRelationWrapper) -> {
//            sqlQuery.equal(formRelationWrapper.getColumn("quanzong"), "XZ");
//        }, 0);

//        Page<FormData> formDataPage = formDataService.selectPageFormDataByFormCode("cs", (sqlQuery, formRelationWrapper) -> {
//            sqlQuery.equal(formRelationWrapper.getColumn("quanzong"), "XZ");
//        }, 0);

//        List<FormData> formDataPage = formDataService.selectSelfColumnFormData("b35fe585-a45f-4541-972c-25bf9c694ab0", (sqlQuery, formRelationWrapper) -> {
//            sqlQuery.column(formRelationWrapper.getColumn("id"));
//            sqlQuery.column(formRelationWrapper.getColumn("quanzong"));
//            sqlQuery.equal(formRelationWrapper.getColumn("quanzong"), "XZ");
//        });

        List<FormData> formDataPage = formDataService.selectSelfColumnFormDataByFormCode("cs", (sqlQuery, formRelationWrapper) -> {
            sqlQuery.column(formRelationWrapper.getColumn("id"));
            sqlQuery.column(formRelationWrapper.getColumn("danghao"));
            sqlQuery.column(formRelationWrapper.getColumn("quanzong"));
            sqlQuery.equal(formRelationWrapper.getColumn("quanzong"), "XZ");
        });
        System.out.println(formDataPage);
    }

    @Test
    public void updateFormDataById() {
        FormData formData = new FormData("a4b72521-7492-4803-81f5-acc8c49daf85", "9f0c3e8a-4a7b-48dc-92b6-bf6abf6ac7a0");
        formData.put("niandu", 2020);
        formData.put("quanzong", "2020");
        FormData formDataNew = formDataService.updateFormDataIgnoreNullById(formData);
        System.out.println(formDataNew);
    }

    @Test
    public void updateFormDataBySqlBuilder() {
        SqlBuilder sqlBuilder = (sqlQuery, formRelationWrapper) -> {
            sqlQuery.set(formRelationWrapper.getColumn("quanzong"), "XZ")
                    .equal(formRelationWrapper.getColumn("niandu"), "2019");
        };
        Long num = formDataService.updateFormDataBySqlBuilder("89e97792-cee3-4a2e-84ac-00665314ab09", sqlBuilder, true);
//        Long num = formDataService.updateFormDataIgnoreNullBySqlBuilder("b35fe585-a45f-4541-972c-25bf9c694ab0", sqlBuilder);

        System.out.println(num);
    }

    @Test
    public void updateFormDataBySqlBuilderAndFormCode() {
//        Long num = formDataService.updateFormDataBySqlBuilderAndFormCode("cs", (sqlQuery, formRelationWrapper) -> {
//            sqlQuery.set(formRelationWrapper.getColumn("quanzong"), "DQ");
//            sqlQuery.equal(formRelationWrapper.getColumn("niandu"), "2019");
//        });
//        Long num = formDataService.updateFormDataBySqlBuilderAndFormCode("cs", (sqlQuery, formRelationWrapper) -> {
//            sqlQuery.set(formRelationWrapper.getColumn("quanzong"), "DQ");
//            sqlQuery.equal(formRelationWrapper.getColumn("niandu"), "2019");
//        });
        FormData formData = new FormData("b35fe585-a45f-4541-972c-25bf9c694ab0", "2e974a91-6ecb-4b74-aa9e-3646ce8ea00a");
        formData.put("niandu", 2020);
        formData.put("danghao", 2020);
        FormData formDataNew = formDataService.updateFormDataIgnoreNullById(formData);
        System.out.println(formDataNew);
    }

    @Test
    public void updateFormDataIgnoreNullBySqlBuilder() {
        Long num = formDataService.updateFormDataIgnoreNullBySqlBuilder("b35fe585-a45f-4541-972c-25bf9c694ab0", (sqlQuery, formRelationWrapper) -> {
            sqlQuery.set(formRelationWrapper.getColumn("quanzong"), "XZ");
            sqlQuery.equal(formRelationWrapper.getColumn("niandu"), "2020");
        }, true);
        System.out.println(num);
    }

    @Test
    public void updateFormDataIgnoreNullBySqlBuilderAndFormCode() {
        Long num = formDataService.updateFormDataIgnoreNullBySqlBuilderAndFormCode("cs", 2, (sqlQuery, formRelationWrapper) -> {
            sqlQuery.set(formRelationWrapper.getColumn("quanzong"), "XM");
            sqlQuery.equal(formRelationWrapper.getColumn("niandu"), "2020");
        }, true);
        System.out.println(num);
    }
}
