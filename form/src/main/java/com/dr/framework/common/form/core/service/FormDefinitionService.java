package com.dr.framework.common.form.core.service;

import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.page.Page;

import java.util.Collection;
import java.util.List;

/**
 * 表单定义service，用来抽象封装表单定义相关service
 *
 * @author dr
 */
public interface FormDefinitionService {

    /**
     * 添加表单定义
     *
     * @param form        表基本信息定义
     * @param fields      表字段定义
     * @param createTable 是否创建表结构  true:生成； false:不生成
     */
    Form addFormDefinition(Form form, Collection<Field> fields, boolean createTable);

    /**
     * 更新表单定义
     *
     * @param form
     * @param fields
     * @param createTable
     * @return
     */
    Form updateFormDefinition(Form form, Collection<Field> fields, boolean createTable);

    /**
     * 查询表单定义
     *
     * @param formId
     * @param formCode
     * @param formType
     * @param formName
     * @return
     */
    List<Form> selectFormDefinition(String formId, String formCode, String formType, String formName);

    /**
     * 分页查询表单定义数据
     *
     * @param form
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Form> selectPageFormDefinition(Form form, int pageIndex, int pageSize);

    /**
     * 查询单一表单定义数据
     *
     * @param formId
     * @return
     */
    Form selectOneFormDefinition(String formId);

    /**
     * 删除表单定义
     *
     * @param formId
     * @param retain 是否直接删除表 true: 删  false: 不删
     * @return
     */
    Long removeFormDefinition(String formId, boolean retain);

    /**
     * 根据表单定义Id 和字段编码 查询字段信息
     *
     * @param formDefinitionId
     * @param fieldCode
     * @return
     */
    Field selectFieldByFieldCode(String formDefinitionId, String fieldCode);

}
