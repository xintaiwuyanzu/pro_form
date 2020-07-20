package com.dr.framework.common.form;

import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.FieldType;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.model.FormRelationWrapper;
import com.dr.framework.common.form.core.service.FormDataService;
import com.dr.framework.common.form.core.service.SqlBuilder;
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

    //TODO 如果需要生成表结构需要穿哪些参数
    @Test
    public void addFormData() {
        FormData formData = new FormData("b35fe585-a45f-4541-972c-25bf9c694ab0", UUID.randomUUID().toString());
//        formData.put("id", UUID.randomUUID().toString());
//        formData.put("quanzong1", "XZ");//TODO 当key不存在时，会添加一条空数据
//        formData.put("quanzong", 2);//TODO 定义string类型，添加int类型也可以
//        formData.put("niandu", "22");//TODO 定义number类型，添加String类型也可以
//        formData.put("niandu", "测试");//TODO 定义number类型，添加String类型报错
//        formData.put("niandu", "1111111111");
        formData.put("niandu", 1111111111);
        formDataService.addFormData(formData);
    }

    @Test
    public void removeFormData() {
//        Long num = formDataService.removeFormData("b35fe585-a45f-4541-972c-25bf9c694ab0", "ba11aee0-cf4d-41a3-bab9-150dd6fab9a8");
//        Long num = formDataService.removeFormDataByFormCode("cs", 2, "ba11aee0-cf4d-41a3-bab9-150dd6fab9a8");
        Long num = formDataService.removeFormDataByFormCode("cs", "ba11aee0-cf4d-41a3-bab9-150dd6fab9a8");
        System.out.println(num);
    }

    @Test
    public void selectFormData() {
        /*SqlBuilder sqlBuilder = new SqlBuilder() {
            @Override
            public void buildSql(SqlQuery sqlQuery, FormRelationWrapper formRelationWrapper) {
                sqlQuery.isNull(formRelationWrapper.getColumn("QUANZONG"));
//                sqlQuery.equal(formRelationWrapper.getColumn("QUANZONG"), "");//TODO　查询值为空时查询全部数据
            }
        };
//        List<FormData> formDataList = formDataService.selectFormData("b35fe585-a45f-4541-972c-25bf9c694ab0", sqlBuilder);

        List<FormData> formDataList = formDataService.selectFormDataByFormCode("cs", sqlBuilder);
        System.out.println(formDataList);*/

        FormData formData = formDataService.selectOneFormData("b35fe585-a45f-4541-972c-25bf9c694ab0", "3bd10487-ac17-4d15-a35e-71652c082f29");
        formData.getFormCode();
        FormField formField = new FormField();
        formField.setFieldCode("danghao");
        formField.setFieldTypeStr(FieldType.STRING);
        String danghao = formData.getString("danghao");
        Object object = formData.getFieldValue(formField);

        System.out.println(formData);
    }
}
