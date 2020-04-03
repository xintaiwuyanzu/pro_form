package com.dr.framework.common.form.validate.service;

import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.validate.model.ValidateDefinition;
import com.dr.framework.common.form.validate.model.ValidateDefinitionField;
import com.dr.framework.common.form.validate.model.ValidateResults;
import com.dr.framework.common.page.Page;

import java.util.Collection;
import java.util.List;

/**
 * 表单校验相关的代码
 *
 * @author dr
 */
public interface ValidateService {
    /**
     * 执行校验
     *
     * @param validateDefinitionId 校验定义Id
     * @param formData             表单数据
     * @param <T>
     * @return
     */
    <T extends FormData> ValidateResults<T> validate(String validateDefinitionId, T formData);

    /**
     * 添加表单校验定义
     *
     * @param validateDefinition
     * @param ValidateDefinitionFieldList
     * @return
     */
    ValidateDefinition addValidateDefinitionForm(ValidateDefinition validateDefinition, Collection<ValidateDefinitionField> ValidateDefinitionFieldList);

    /**
     * 更新表单校验定义
     *
     * @param validateDefinition
     * @param ValidateDefinitionFieldList
     * @return
     */
    ValidateDefinition updateValidateDefinitionForm(ValidateDefinition validateDefinition, Collection<ValidateDefinitionField> ValidateDefinitionFieldList);

    /**
     * 查询一个检验
     *
     * @param validateId
     * @return
     */
    ValidateDefinition SelectOneValidateDefinitionForm(String validateId);

    /**
     * 查询这个类型下的表单定校验
     *
     * @param formDefinitionId
     * @param validateType
     * @return
     */
    List<ValidateDefinition> SelectValidateDefinitionForm(String formDefinitionId, String validateType);

    /**
     * 查询这个表单下的校验 分页
     *
     * @param formDefinitionId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<ValidateDefinition> SelectPageValidateDefinitionForm(String formDefinitionId, int pageIndex, int pageSize);

    /**
     * 删除表单校验
     *
     * @param validateId
     * @return
     */
    Long removeValidateDefinitionForm(String validateId);

}
