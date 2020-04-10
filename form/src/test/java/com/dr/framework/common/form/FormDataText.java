package com.dr.framework.common.form;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormDefinitionInfo;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.FormFieldInfo;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.service.FormDataService;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class FormDataText extends AbstractApplicationTest {
    @Autowired
    CommonMapper commonMapper;
    @Autowired
    FormNameGenerator formNameGenerator;
    @Autowired
    FormDataService formDataService;

    @Test
    public void add() {
        FormData formData1 = new FormData("369363b0-ae13-4193-9626-1fccc7a4d69b", UUID.randomUUID().toString(), "NAME", "fist");
        formData1.put("formDefinitionId", "29eba72e-e041-4b3a-90e2-1b49b8bc302e");
        formData1.put("formDataId", UUID.randomUUID().toString());
        formData1.put("text001", "NAME");
        formData1.put("id", UUID.randomUUID().toString());
        FormData formData = formDataService.addFormData(formData1);
        System.out.println(formData);
    }

    @Test
    public void select() {
        List<FormData> formData = formDataService.selectFormData((sqlQuery, relation) -> {
            sqlQuery.equal(relation.getColumn("FORMDEFINITIONID"),"29eba72e-e041-4b3a-90e2-1b49b8bc302e");
        });
        System.out.println(formData);
    }

    @Test
    public void field() {
        FormDefinition formDefinition = commonMapper.selectOneByQuery(SqlQuery.from(FormDefinition.class).equal(FormDefinitionInfo.ID, "29eba72e-e041-4b3a-90e2-1b49b8bc302e"));
        FormField formField = commonMapper.selectOneByQuery(SqlQuery.from(FormField.class).equal(FormFieldInfo.ID, "4d6bb20d-6257-44b1-ad2d-513d7949ec55"));
        String s = formNameGenerator.genFieldName(formDefinition, formField);
        System.out.println(s);
    }


}
