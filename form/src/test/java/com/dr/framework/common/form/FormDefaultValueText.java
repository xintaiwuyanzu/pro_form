package com.dr.framework.common.form;

import com.dr.framework.common.form.init.entity.FieldDefaultValue;
import com.dr.framework.common.form.init.entity.FormDefaultValue;
import com.dr.framework.common.form.init.model.FieldDefault;
import com.dr.framework.common.form.init.model.FormDefault;
import com.dr.framework.common.form.init.service.FormDefaultValueService;
import com.dr.framework.common.page.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class FormDefaultValueText extends AbstractApplicationTest {

    @Autowired
    FormDefaultValueService formDefaultValueService;

    @Test
    public void add() {
        Collection<FieldDefaultValue> list = new ArrayList<>();
        Collection<FieldDefault> list1 = new ArrayList<>();
        String s = UUID.randomUUID().toString();
        FormDefaultValue formDefaultValue = new FormDefaultValue();
        formDefaultValue.setId(s);
        formDefaultValue.setDefaultType("1");
        formDefaultValue.setFormDefinitionId("79f87c16-d5d6-49ff-9028-9edae1049887");
        formDefaultValue.setLinkCode("1");
        formDefaultValue.setLinkName("name");
        formDefaultValue.setDefaultState("1");
        formDefaultValue.setDescription("描述");
        formDefaultValue.setVersion("0.1");
        FieldDefaultValue fieldDefaultValue = new FieldDefaultValue();
        fieldDefaultValue.setId(UUID.randomUUID().toString());
        fieldDefaultValue.setCustom("1");
        fieldDefaultValue.setDefaultValue("1");
        fieldDefaultValue.setFieldName("bin");
        fieldDefaultValue.setFieldType("1");
        fieldDefaultValue.setFormDefaultValueId(s);
        fieldDefaultValue.setFormDefinitionId("79f87c16-d5d6-49ff-9028-9edae1049887");
        fieldDefaultValue.setFieldCode("default");
        formDefaultValue.setFieldDefaultList(list);
        list.add(fieldDefaultValue);
        list1.add(fieldDefaultValue);
        formDefaultValueService.addFormDefaultValue(formDefaultValue, list1);
    }

    @Test
    public void select() {
        // FormDefault formDefault = formDefaultValueService.SelectOneFormDefaultValue("79f87c16-d5d6-49ff-9028-9edae1049887", "44150a9f-a3ee-4ccb-b563-48cd968deadd");
        //List<FormDefault> formDefaults = formDefaultValueService.SelectFormDefaultValue("79f87c16-d5d6-49ff-9028-9edae1049887");
        Page<FormDefault> formDefaultPage = formDefaultValueService.SelectPageFormDefaultValue("79f87c16-d5d6-49ff-9028-9edae1049887", 2, 1);
        System.out.println(formDefaultPage.getData().toString());
    }

    @Test
    public void del() {
        formDefaultValueService.RemoveFormDefaultValue("44150a9f-a3ee-4ccb-b563-48cd968deadd");
    }

}
