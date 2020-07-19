package com.dr.framework.common.form.core.service;

import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.page.Page;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 表单数据service，用来操作定义表的增删改查
 *
 * @author dr
 */
public interface FormDataService {
    /**
     * 是否默认创建表结构
     */
    boolean DEFAULT_AUTO_CREATE_TABLE = true;
    /*
     * ===============================================
     * 增加数据
     * ===============================================
     */

    /**
     * 添加表单实例数据
     *
     * @param formData
     * @return
     */
    default FormData addFormData(FormData formData) {
        return addFormData(formData, DEFAULT_AUTO_CREATE_TABLE);
    }

    /**
     * 添加表单实例数据
     *
     * @param formData
     * @param autoCheck true 表示自动检测表是否存在，没有则根据表单定义自动创建表结构
     * @return
     */
    FormData addFormData(FormData formData, boolean autoCheck);

    /*
     * ===============================================
     * 修改数据
     * ===============================================
     */

    /**
     * 更新表单实例数据
     *
     * @param formData
     * @return
     */
    FormData updateFormDataById(FormData formData);

    /**
     * 更新表单实例数据
     * 过滤空字段
     *
     * @param formData
     * @return
     */
    FormData updateFormDataIgnoreNullById(FormData formData);

    default Long updateFormDataBySqlBuilder(String formDefinitionId, SqlBuilder sqlBuilder) {
        return updateFormDataBySqlBuilder(formDefinitionId, sqlBuilder, DEFAULT_AUTO_CREATE_TABLE);
    }

    /**
     * 根据表单定义Id
     * 自定义修改指定表定义的数据
     *
     * @param formDefinitionId
     * @param sqlBuilder
     * @param autoCheck        true 表示自动检测表是否存在，没有则根据表单定义自动创建表结构
     * @return
     */
    Long updateFormDataBySqlBuilder(String formDefinitionId, SqlBuilder sqlBuilder, boolean autoCheck);

    default Long updateFormDataIgnoreNullBySqlBuilder(String formDefinitionId, SqlBuilder sqlBuilder) {
        return updateFormDataIgnoreNullBySqlBuilder(formDefinitionId, sqlBuilder, DEFAULT_AUTO_CREATE_TABLE);
    }

    /**
     * 根据表单定义Id
     * 自定义修改指定表定义的数据
     * 只更新非空的数据
     *
     * @param formDefinitionId
     * @param sqlBuilder
     * @param autoCheck
     * @return
     */
    Long updateFormDataIgnoreNullBySqlBuilder(String formDefinitionId, SqlBuilder sqlBuilder, boolean autoCheck);

    default Long updateFormDataBySqlBuilderAndFormCode(String formCode, SqlBuilder sqlBuilder) {
        return updateFormDataBySqlBuilderAndFormCode(formCode, null, sqlBuilder, DEFAULT_AUTO_CREATE_TABLE);
    }

    default Long updateFormDataBySqlBuilderAndFormCode(String formCode, SqlBuilder sqlBuilder, boolean autoCheck) {
        return updateFormDataBySqlBuilderAndFormCode(formCode, null, sqlBuilder, autoCheck);
    }

    default Long updateFormDataBySqlBuilderAndFormCode(String formCode, Integer version, SqlBuilder sqlBuilder) {
        return updateFormDataBySqlBuilderAndFormCode(formCode, version, sqlBuilder, DEFAULT_AUTO_CREATE_TABLE);
    }

    /**
     * 根据表单编码和版本
     * 自定义修改指定表定义的数据
     *
     * @param formCode
     * @param version
     * @param sqlBuilder
     * @param autoCheck
     * @return
     */
    Long updateFormDataBySqlBuilderAndFormCode(String formCode, Integer version, SqlBuilder sqlBuilder, boolean autoCheck);

    default Long updateFormDataIgnoreNullBySqlBuilderAndFormCode(String formCode, SqlBuilder sqlBuilder) {
        return updateFormDataIgnoreNullBySqlBuilderAndFormCode(formCode, null, sqlBuilder, DEFAULT_AUTO_CREATE_TABLE);
    }

    default Long updateFormDataIgnoreNullBySqlBuilderAndFormCode(String formCode, SqlBuilder sqlBuilder, boolean autoCheck) {
        return updateFormDataIgnoreNullBySqlBuilderAndFormCode(formCode, null, sqlBuilder, autoCheck);
    }

    default Long updateFormDataIgnoreNullBySqlBuilderAndFormCode(String formCode, Integer version, SqlBuilder sqlBuilder) {
        return updateFormDataIgnoreNullBySqlBuilderAndFormCode(formCode, version, sqlBuilder, DEFAULT_AUTO_CREATE_TABLE);
    }

    /**
     * 根据表单编码和版本
     * 自定义修改指定表定义的数据
     * 只更新非空的数据
     *
     * @param formCode
     * @param version
     * @param sqlBuilder
     * @param autoCheck
     * @return
     */
    Long updateFormDataIgnoreNullBySqlBuilderAndFormCode(String formCode, Integer version, SqlBuilder sqlBuilder, boolean autoCheck);
    /*
     * ===============================================
     * 查询数据
     * ===============================================
     */

    /**
     * 根据sql语句查询指定表的数据
     *
     * @param formDefinitionId
     * @param sqlBuilder
     * @return
     */
    List<FormData> selectFormData(String formDefinitionId, SqlBuilder sqlBuilder);

    /**
     * 不是查询所有的字段，需要自己手动添加字段
     *
     * @param formDefinitionId
     * @param sqlBuilder       默认不查询任何列，需要自己再sqlquery中手动添加列
     * @return
     */
    List<FormData> selectSelfColumnFormData(String formDefinitionId, SqlBuilder sqlBuilder);

    default List<FormData> selectSelfColumnFormDataByFormCode(String formCode, SqlBuilder sqlBuilder) {
        return selectSelfColumnFormDataByFormCode(formCode, null, sqlBuilder);
    }

    /**
     * 不是查询所有的字段，需要自己手动添加字段
     *
     * @param formCode
     * @param version
     * @param sqlBuilder 默认不查询任何列，需要自己再sqlquery中手动添加列
     * @return
     */
    List<FormData> selectSelfColumnFormDataByFormCode(String formCode, Integer version, SqlBuilder sqlBuilder);


    default List<FormData> selectFormDataByFormCode(String formCode, SqlBuilder sqlBuilder) {
        return selectFormDataByFormCode(formCode, null, sqlBuilder);
    }

    /**
     * 根据表单编码和版本自定义查询表单数据
     *
     * @param formCode
     * @param version
     * @param sqlBuilder
     * @return
     */
    List<FormData> selectFormDataByFormCode(String formCode, Integer version, SqlBuilder sqlBuilder);

    /**
     * 查询表单单个实例数据
     *
     * @param formDefinitionId 表单定义Id
     * @param formDataId       数据Id
     * @return
     */
    FormData selectOneFormData(String formDefinitionId, String formDataId);

    default FormData selectOneFormDataByFormCode(String formCode, String formDataId) {
        return selectOneFormDataByFormCode(formCode, null, formDataId);
    }

    /**
     * @param formCode
     * @param version
     * @param formDataId
     * @return
     */
    FormData selectOneFormDataByFormCode(String formCode, Integer version, String formDataId);

    default Page<FormData> selectPageFormData(String formDefinitionId, SqlBuilder sqlBuilder, int pageIndex) {
        return this.selectPageFormData(formDefinitionId, sqlBuilder, pageIndex, (int) Page.DEFAULT_PAGE_SIZE);
    }

    /**
     * 分页查询表单实例数据
     *
     * @param formDefinitionId
     * @param sqlBuilder
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<FormData> selectPageFormData(String formDefinitionId, SqlBuilder sqlBuilder, int pageIndex, int pageSize);

    default Page<FormData> selectPageFormDataByFormCode(String formCode, SqlBuilder sqlBuilder, int pageIndex) {
        return selectPageFormDataByFormCode(formCode, null, sqlBuilder, pageIndex, (int) Page.DEFAULT_PAGE_SIZE);
    }

    default Page<FormData> selectPageFormDataByFormCode(String formCode, SqlBuilder sqlBuilder, int pageIndex, int pageSize) {
        return selectPageFormDataByFormCode(formCode, null, sqlBuilder, pageIndex, pageSize);
    }

    /**
     * 根据表单编码和版本
     * 分页查询表单实例数据
     *
     * @param formCode
     * @param version
     * @param sqlBuilder
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<FormData> selectPageFormDataByFormCode(String formCode, Integer version, SqlBuilder sqlBuilder, int pageIndex, int pageSize);
    /*
     * ===============================================
     * 删除数据
     * ===============================================
     */

    /**
     * 删除表单实例数据
     *
     * @param formDefinitionId
     * @param formDataId
     * @return
     */
    default Long removeFormData(String formDefinitionId, String formDataId) {
        Assert.isTrue(!StringUtils.isEmpty(formDataId), "表单数据主键不能为空！");
        return removeFormData(formDefinitionId, ((sqlQuery, formRelationWrapper) -> sqlQuery.equal(formRelationWrapper.idColumn(), formDataId)));
    }

    default Long removeFormDataByFormCode(String formCode, String formDataId) {
        return removeFormDataByFormCode(formCode, null, formDataId);

    }

    default Long removeFormDataByFormCode(String formCode, Integer version, String formDataId) {
        Assert.isTrue(!StringUtils.isEmpty(formDataId), "表单数据主键不能为空！");
        return removeFormDataByFormCode(formCode, version, ((sqlQuery, formRelationWrapper) -> sqlQuery.equal(formRelationWrapper.idColumn(), formDataId)));
    }

    /**
     * 跟据表单Id自定义删除数据
     *
     * @param formDefinitionId
     * @param sqlBuilder
     * @return
     */
    Long removeFormData(String formDefinitionId, SqlBuilder sqlBuilder);

    /**
     * 根据表单编码自定义删除数据
     *
     * @param formCode
     * @param version
     * @param sqlBuilder
     * @return
     */
    Long removeFormDataByFormCode(String formCode, Integer version, SqlBuilder sqlBuilder);

}
