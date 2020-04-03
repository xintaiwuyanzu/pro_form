package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.FormFieldInfo;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class FieldDataSelectOneCommand implements Command<Field> {

    private String formDefinitionId;
    private String fieldCode;
    private String fieldName;

    public FieldDataSelectOneCommand(String formDefinitionId, String fieldCode) {
        this(formDefinitionId, fieldCode, null);
    }

    public FieldDataSelectOneCommand(String formDefinitionId, String fieldCode, String fieldName) {
        this.formDefinitionId = formDefinitionId;
        this.fieldCode = fieldCode;
        this.fieldName = fieldName;
    }

    @Override
    public Field execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(formDefinitionId), "表单定义Id不能为空");
        Assert.isTrue(StringUtils.isNotEmpty(fieldCode), "表单定义Id不能为空");
        SqlQuery<FormField> sqlQuery = SqlQuery.from(FormField.class);
        if (StringUtils.isNotEmpty(formDefinitionId)) {
            sqlQuery.equal(FormFieldInfo.FORMDEFINITIONID, formDefinitionId);
        }
        if (StringUtils.isNotEmpty(fieldCode)) {
            sqlQuery.equal(FormFieldInfo.FIELDCODE, fieldCode);
        }
        if (StringUtils.isNotEmpty(fieldName)) {
            sqlQuery.equal(FormFieldInfo.FIELDNAME, fieldName);
        }
        FormField formField = context.getMapper().selectOneByQuery(sqlQuery);
        return formField;
    }

}
