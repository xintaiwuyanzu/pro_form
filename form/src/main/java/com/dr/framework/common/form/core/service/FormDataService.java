package com.dr.framework.common.form.core.service;

import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.page.Page;

import java.util.List;

/**
 * 表单数据service，用来操作定义表的增删改查
 *
 * @author dr
 */
public interface FormDataService {

    /**
     * 添加表单实例数据
     *
     * @param formData
     * @return
     */
    FormData addFormData(FormData formData);

    /**
     * 更新表单实例数据
     *
     * @param formData
     * @return
     */
    FormData updateFormData(FormData formData);


    /**
     * 多条件查询表单实例数据
     *
     * @param sqlBuilder
     * @return
     */
    List<FormData> selectFormData(SqlBuilder sqlBuilder);

    List<FormData> selectFormData(String formId,SqlBuilder sqlBuilder);

    /**
     * 查询表单单个实例数据
     *
     * @param formDataId
     * @return
     */
    FormData selectOneFormData(String formId, String formDataId);


    /**
     * 分页查询表单实例数据
     *
     * @param formData
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<FormData> selectPageFormDefinition(FormData formData, int pageIndex, int pageSize);

    /**
     * 删除表单实例数据
     *
     * @param formId
     * @param formDataId
     * @return
     */
    Long removeFormData(String formId, String formDataId);


}
