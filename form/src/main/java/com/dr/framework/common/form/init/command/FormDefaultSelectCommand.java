package com.dr.framework.common.form.init.command;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.init.entity.FieldDefaultValue;
import com.dr.framework.common.form.init.entity.FieldDefaultValueInfo;
import com.dr.framework.common.form.init.entity.FormDefaultValue;
import com.dr.framework.common.form.init.entity.FormDefaultValueInfo;
import com.dr.framework.common.form.init.model.FormDefault;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionForm;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionFormField;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionFormFieldInfo;
import com.dr.framework.common.form.validate.model.ValidateDefinition;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class FormDefaultSelectCommand implements Command<List<FormDefault>> {

    private String formId;

    public FormDefaultSelectCommand(String formId) {
        this.formId = formId;
    }

    @Override
    public List<FormDefault> execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(formId), "表单定义Id不能全为空");
        List<FormDefault> listFormDefaultValues = new ArrayList<>();
        SqlQuery sqlQuery = SqlQuery.from(FormDefaultValue.class);
        if (StringUtils.isNotEmpty(formId)) {
            sqlQuery.equal(FormDefaultValueInfo.FORMID, formId);
        }
        List<FormDefaultValue> list = context.getMapper().selectByQuery(sqlQuery);
        if (list.size() > 0) {
            for (FormDefaultValue formDefaultValue : list) {
                List<FieldDefaultValue> listFieldDefaultValue = context.getMapper().selectByQuery(SqlQuery.from(FieldDefaultValue.class).equal(FieldDefaultValueInfo.FORMDEFAULTVALUEID, formDefaultValue.getId()));
                formDefaultValue.setFieldDefaultList(listFieldDefaultValue);
                listFormDefaultValues.add(formDefaultValue);
            }
        }
        return listFormDefaultValues;
    }


}
