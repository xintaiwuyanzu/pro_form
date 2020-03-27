package com.dr.framework.common.form.core.service;

import com.dr.framework.common.form.core.model.FormData;

import java.util.List;
import java.util.Map;

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
    Map<String, Object> addFormData(FormData formData);

    /**
     * 更新表单实例数据
     *
     * @param formData
     * @return
     */
    Map<String, Object> updateFormData(FormData formData);


    /**
     * 多条件查询表单实例数据
     *
     * @param formDataId
     * @return
     */
    List<Map<String, Object>> selectFormData(String formDataId, String formId);

    /**
     * 查询表单单个实例数据
     *
     * @param formDataId
     * @return
     */
    Map<String, Object> selectOneFormData(String formDataId, String formId);

    /**
     * 删除表单实例数据
     *
     * @param formDataId
     * @param formId
     * @return
     */
    Long removeFormData(String formDataId, String formId);


}
