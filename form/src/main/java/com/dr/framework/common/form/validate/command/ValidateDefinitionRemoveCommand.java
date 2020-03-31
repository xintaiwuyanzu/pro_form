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

public class ValidateDefinitionRemoveCommand implements Command<Long> {

    private String validateId;


    public ValidateDefinitionRemoveCommand(String validateId) {
        this.validateId = validateId;
    }

    @Override
    public Long execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(validateId), "校验数据标识不能为空");
        //先删除表单校验字段数据
        context.getMapper().deleteByQuery(SqlQuery.from(ValidateDefinitionFormField.class).equal(ValidateDefinitionFieldInfo.VALIDATEFORMID, validateId));
        //再删除表单校验主表数据
        return context.getMapper().deleteByQuery(SqlQuery.from(ValidateDefinitionForm.class).equal(ValidateDefinitionFormInfo.ID, validateId));
    }

}
