package com.dr.framework.common.form.validate.command;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionFormField;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionFieldInfo;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionForm;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionFormInfo;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.List;

public class ValidateDefinitionSelectOneCommand implements Command<ValidateDefinitionForm> {

    private String validateId;

    private String validateCode;

    public ValidateDefinitionSelectOneCommand(String validateId, String validateCode) {
        this.validateId = validateId;
        this.validateCode = validateCode;
    }

    @Override
    public ValidateDefinitionForm execute(CommandContext context) {
        SqlQuery<ValidateDefinitionForm> sqlQuery = SqlQuery.from(ValidateDefinitionForm.class);
        if (StringUtils.isNotEmpty(validateId)) {
            sqlQuery.equal(ValidateDefinitionFormInfo.ID, validateId);
        }
        if (StringUtils.isNotEmpty(validateCode)) {
            sqlQuery.equal(ValidateDefinitionFormInfo.VALIDATECODE, validateCode);
        }
        ValidateDefinitionForm validateDefinitionForm = context.getMapper().selectOneByQuery(sqlQuery);
        Assert.notNull(validateDefinitionForm, "校验不存在");
        List<ValidateDefinitionFormField> listValidateDefinitionField = context.getMapper().selectByQuery(SqlQuery.from(ValidateDefinitionFormField.class).equal(ValidateDefinitionFieldInfo.VALIDATEFORMID, validateDefinitionForm.getId()));
        validateDefinitionForm.setValidateDefinitionFieldList(listValidateDefinitionField);
        return validateDefinitionForm;
    }
}
