package com.dr.framework.common.form.validate.service.impl;

import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.validate.command.*;
import com.dr.framework.common.form.validate.model.ValidateDefinition;
import com.dr.framework.common.form.validate.model.ValidateDefinitionField;
import com.dr.framework.common.form.validate.model.ValidateResults;
import com.dr.framework.common.form.validate.service.ValidateService;
import com.dr.framework.common.form.validate.service.Validator;
import com.dr.framework.common.page.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DefaultValidateService implements ValidateService {

    @Autowired
    CommandExecutor executor;

    List<Validator> defaultValidators = Arrays.asList(
            new IdNoValidator(),
            new PhoneValidator()
    );

    @Autowired
    List<Validator> validators;

    /**
     * 获取字段校验结果
     *
     * @param validateDefinitionId 校验定义Id
     * @param formData             表单数据
     * @return
     */
    @Override
    public ValidateResults<FormData> validate(String validateDefinitionId, FormData formData) {
        return executor.execute(new FormDataValidateCommand(validateDefinitionId, defaultValidators, formData));
    }

    /**
     * 添加表单校验定义
     *
     * @param validateDefinition
     * @param ValidateDefinitionFieldList
     * @return
     */
    @Override
    public ValidateDefinition addValidateDefinitionForm(ValidateDefinition validateDefinition, Collection<ValidateDefinitionField> ValidateDefinitionFieldList) {
        return executor.execute(new ValidateDefinitionInsertCommand(validateDefinition, ValidateDefinitionFieldList));
    }

    /**
     * 更新表单校验定义
     *
     * @param validateDefinition
     * @param ValidateDefinitionFieldList
     * @return
     */
    @Override
    public ValidateDefinition updateValidateDefinitionForm(ValidateDefinition validateDefinition, Collection<ValidateDefinitionField> ValidateDefinitionFieldList) {
        return addValidateDefinitionForm(validateDefinition, ValidateDefinitionFieldList);
    }

    /**
     * 查询一套表单检验定义
     *
     * @param validateId
     * @return
     */
    @Override
    public ValidateDefinition SelectOneValidateDefinitionForm(String validateId) {
        return executor.execute(new ValidateDefinitionSelectOneCommand(validateId));
    }

    /**
     * 查询这个类型下的表单定校验
     *
     * @param formDefinitionId
     * @param validateType
     * @return
     */
    @Override
    public List<ValidateDefinition> SelectValidateDefinitionForm(String formDefinitionId, String validateType) {
        return executor.execute(new ValidateDefinitionSelectCommand(formDefinitionId, validateType));
    }

    /**
     * 查询这个表单下的校验 分页
     *
     * @param formDefinitionId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Page<ValidateDefinition> SelectPageValidateDefinitionForm(String formDefinitionId, int pageIndex, int pageSize) {
        return executor.execute(new ValidateDefinitionSelectPageCommand(formDefinitionId, pageIndex, pageSize));
    }

    /**
     * 删除表单校验
     *
     * @param validateId
     * @return
     */
    @Override
    public Long removeValidateDefinitionForm(String validateId) {
        return executor.execute(new ValidateDefinitionRemoveCommand(validateId));
    }


}
