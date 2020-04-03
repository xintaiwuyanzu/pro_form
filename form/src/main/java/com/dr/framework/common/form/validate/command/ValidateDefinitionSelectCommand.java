package com.dr.framework.common.form.validate.command;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionForm;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionFormField;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionFormFieldInfo;
import com.dr.framework.common.form.validate.entity.ValidateDefinitionFormInfo;
import com.dr.framework.common.form.validate.model.ValidateDefinition;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ValidateDefinitionSelectCommand implements Command<List<ValidateDefinition>> {

    /**
     * 表单定义id
     */
    private String formDefinitionId;

    /**
     * 校验类型
     */
    private String validateType;

    /**
     * 校验名称
     */
    private String validateName;

    public ValidateDefinitionSelectCommand(String formDefinitionId) {
        this(formDefinitionId, "default", null);
    }

    public ValidateDefinitionSelectCommand(String formDefinitionId, String validateType) {
        this(formDefinitionId, validateType, null);
    }

    public ValidateDefinitionSelectCommand(String formDefinitionId, String validateType, String validateName) {
        this.formDefinitionId = formDefinitionId;
        this.validateType = validateType;
        this.validateName = validateName;
    }

    @Override
    public List<ValidateDefinition> execute(CommandContext context) {
        List<ValidateDefinition> listValidate = new ArrayList<>();
        SqlQuery sqlQuery = SqlQuery.from(ValidateDefinitionForm.class);
        if (StringUtils.isNotEmpty(formDefinitionId)) {
            sqlQuery.equal(ValidateDefinitionFormInfo.FORMDEFINITIONID, formDefinitionId);
        }
        if (StringUtils.isNotEmpty(validateType)) {
            sqlQuery.equal(ValidateDefinitionFormInfo.VALIDATETYPE, validateType);
        }
        if (StringUtils.isNotEmpty(validateName)) {
            sqlQuery.equal(ValidateDefinitionFormInfo.VALIDATENAME, validateName);
        }
        List<ValidateDefinitionForm> list = context.getMapper().selectByQuery(sqlQuery);
        if (list.size() > 0) {
            for (ValidateDefinitionForm validateDefinitionForm : list) {
                List<ValidateDefinitionFormField> listValidateDefinitionField = context.getMapper().selectByQuery(SqlQuery.from(ValidateDefinitionFormField.class).equal(ValidateDefinitionFormFieldInfo.VALIDATEFORMID, validateDefinitionForm.getId()));
                validateDefinitionForm.setValidateDefinitionFieldList(listValidateDefinitionField);
                listValidate.add(validateDefinitionForm);
            }
        }
        return listValidate;
    }

}
