package com.dr.framework.common.form.core.service;

import com.dr.framework.common.entity.StatusEntity;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.query.FormDefinitionQuery;
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
     * 默认不动物理表结构，只是保存表定义
     */
    boolean DEFAULT_TABLE_OR_FIELD_CREATE = false;

    /**
     * 添加表单定义
     *
     * @param form   表基本信息定义
     * @param fields 表字段定义
     * @return 返回表单定义
     */
    default Form addFormDefinition(Form form, Collection<Field> fields) {
        return addFormDefinition(form, fields, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    /**
     * 添加表单定义
     *
     * @param form        表基本信息定义
     * @param fields      表字段定义
     * @param createTable 是否创建表结构  true:生成； false:不生成
     * @return 返回表单定义
     */
    Form addFormDefinition(Form form, Collection<Field> fields, boolean createTable);

    /**
     * 更新表单定义
     *
     * @param form
     * @param fields
     * @return
     */
    default Form updateFormDefinition(Form form, Collection<Field> fields) {
        return updateFormDefinition(form, fields, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    /**
     * 更新表单定义
     *
     * @param form
     * @param fields
     * @param updateTable
     * @return
     */
    default Form updateFormDefinition(Form form, Collection<Field> fields, boolean updateTable) {
        return updateFormDefinition(form, fields, updateTable, updateTable);
    }

    /**
     * @param form
     * @param fields
     * @param updateTable
     * @param copyData
     * @return
     */
    Form updateFormDefinition(Form form, Collection<Field> fields, boolean updateTable, boolean copyData);

    /**
     * 查询表单定义
     *
     * @param query 查询条件
     * @return
     */
    List<? extends Form> selectFormDefinitionByQuery(FormDefinitionQuery query);

    /**
     * 根据条件查询表单定义分页
     *
     * @param query
     * @param pageIndex
     * @return
     */
    default Page<? extends Form> selectPageFormDefinition(FormDefinitionQuery query, int pageIndex) {
        return selectPageFormDefinition(query, pageIndex, (int) Page.DEFAULT_PAGE_SIZE);
    }

    /**
     * 分页查询表单定义数据
     *
     * @param query     查询条件
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<? extends Form> selectPageFormDefinition(FormDefinitionQuery query, int pageIndex, int pageSize);

    /**
     * 查询单一表单定义数据
     *
     * @param formDefinitionId
     * @return
     */
    Form selectFormDefinitionById(String formDefinitionId);

    /**
     * 根据表单定义编码查询默认版本的表单定义
     *
     * @param formCode
     * @return
     */
    default Form selectFormDefinitionByCode(String formCode) {
        return selectFormDefinitionByCodeAndVersion(formCode, null);
    }

    /**
     * 根据编码和版本查询表单定义
     *
     * @param formCode
     * @param version  版本为空则查询默认的表单
     * @return
     */
    Form selectFormDefinitionByCodeAndVersion(String formCode, Integer version);

    /**
     * 删除表定义
     *
     * @param formDefinitionId
     * @return
     */
    default Long removeFormDefinitionById(String formDefinitionId) {
        return removeFormDefinitionById(formDefinitionId, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    /**
     * 删除表单定义
     *
     * @param formDefinitionId
     * @param dropTable        是否直接删除表 true: 删  false: 不删
     * @return
     */
    Long removeFormDefinitionById(String formDefinitionId, boolean dropTable);

    /**
     * 根据code删除所有版本的表单
     *
     * @param formCode
     * @return
     */
    default Long removeFormDefinitionAllByCode(String formCode) {
        return removeFormDefinitionAllByCode(formCode, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    /**
     * 根据code删除所有版本的表单
     *
     * @param formCode
     * @param dropTable
     * @return
     */
    default Long removeFormDefinitionAllByCode(String formCode, boolean dropTable) {
        return removeFormDefinitionByCodeAndVersion(formCode, null, dropTable);
    }

    /**
     * 根据表单code删除表单定义
     *
     * @param formCode
     * @param version   版本为空则删除指定code的所有版本
     * @param dropTable
     * @return
     */
    Long removeFormDefinitionByCodeAndVersion(String formCode, Integer version, boolean dropTable);

    /**
     * 根据表单定义Id 和字段编码 查询字段信息
     *
     * @param formDefinitionId
     * @param fieldCode
     * @return
     */
    Field selectFieldByCode(String formDefinitionId, String fieldCode);

    /**
     * 根据表单定义Id 和字段编码 查询字段信息
     *
     * @param formCode
     * @param version
     * @param fieldCode
     * @return
     */
    Field selectFieldByCodeAndVersion(String formCode, Integer version, String fieldCode);

    /**
     * 添加表单字段
     *
     * @param formDefinitionId
     * @param field
     * @return
     */
    default Field addField(String formDefinitionId, Field field) {
        return addField(formDefinitionId, field, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    /**
     * 添加表单字段
     *
     * @param formDefinitionId 表单定义ID
     * @param field            新字段
     * @param updateTable      更新表结构
     * @return
     */
    default Field addField(String formDefinitionId, Field field, boolean updateTable) {
        return addField(formDefinitionId, field, updateTable, updateTable);
    }

    /**
     * 添加表单字段
     *
     * @param formDefinitionId
     * @param field
     * @param updateTable
     * @param copyData
     * @return
     */
    Field addField(String formDefinitionId, Field field, boolean updateTable, boolean copyData);

    default Field addFieldByFormCode(String formCode, Field field) {
        return addFieldByFormCode(formCode, field, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    default Field addFieldByFormCode(String formCode, Field field, boolean updateTable) {
        return addFieldByFormCode(formCode, field, updateTable, updateTable);
    }

    default Field addFieldByFormCode(String formCode, Field field, boolean updateTable, boolean copyData) {
        return addFieldByFormCode(formCode, null, field, updateTable, copyData);
    }

    default Field addFieldByFormCode(String formCode, Integer version, Field field) {
        return addFieldByFormCode(formCode, version, field, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    default Field addFieldByFormCode(String formCode, Integer version, Field field, boolean updateTable) {
        return addFieldByFormCode(formCode, version, field, updateTable, updateTable);
    }

    /**
     * 根据表单编码添加字段
     *
     * @param formCode    表单编码
     * @param version     表单版本
     * @param field       添加的字段
     * @param updateTable 更新表结构
     * @param copyData    是否复制数据
     * @return
     */
    Field addFieldByFormCode(String formCode, Integer version, Field field, boolean updateTable, boolean copyData);

    /**
     * 添加表单字段
     *
     * @param formDefinitionId
     * @param field
     * @return
     */
    default Field changeField(String formDefinitionId, Field field) {
        return changeField(formDefinitionId, field, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    /**
     * 更新字段
     *
     * @param formDefinitionId 表单定义
     * @param field            字段
     * @param updateTable      更新表结构
     * @return
     */
    default Field changeField(String formDefinitionId, Field field, boolean updateTable) {
        return changeField(formDefinitionId, field, updateTable, updateTable);
    }

    /**
     * 更新字段
     *
     * @param formDefinitionId 表单定义
     * @param field            字段
     * @param updateTable      更新表结构
     * @return
     */
    Field changeField(String formDefinitionId, Field field, boolean updateTable, boolean copyData);

    default Field changeFieldByFormCode(String formCode, Field field) {
        return changeFieldByFormCode(formCode, field, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    default Field changeFieldByFormCode(String formCode, Field field, boolean updateTable) {
        return changeFieldByFormCode(formCode, field, updateTable, updateTable);
    }

    default Field changeFieldByFormCode(String formCode, Field field, boolean updateTable, boolean copyData) {
        return changeFieldByFormCode(formCode, null, field, updateTable, copyData);
    }

    default Field changeFieldByFormCode(String formCode, Integer version, Field field) {
        return changeFieldByFormCode(formCode, version, field, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    default Field changeFieldByFormCode(String formCode, Integer version, Field field, boolean updateTable) {
        return changeFieldByFormCode(formCode, version, field, updateTable, updateTable);
    }

    /**
     * 根据表单编码和版本更新字段
     * 如果更改数据类型的话，会创建新的表结构
     *
     * @param formCode    表单编码
     * @param version     表单版本
     * @param field       字段
     * @param updateTable 更新表结构
     * @param copyData    是否复制数据
     * @return
     */
    Field changeFieldByFormCode(String formCode, Integer version, Field field, boolean updateTable, boolean copyData);

    /**
     * 删除字段Id
     *
     * @param formDefinitionId
     * @param fieldCode
     * @return
     */
    Field removeField(String formDefinitionId, String fieldCode);


    default Field removeFieldByFormCode(String formCode, String fieldCode) {
        return removeFieldByFormCode(formCode, null, fieldCode);
    }

    /**
     * 根据表单编码删除字段
     *
     * @param formCode
     * @param version
     * @param fieldCode
     * @return
     */
    Field removeFieldByFormCode(String formCode, Integer version, String fieldCode);

    /**
     * 更新指定表单  指定编码的字段的状态
     *
     * @param formDefinitionId
     * @param fieldCode
     * @param status           状态
     * @return
     */
    Field changeFieldStatus(String formDefinitionId, String fieldCode, String status);

    default Field changeFieldStatusByFormCode(String formCode, String fieldCode, String status) {
        return changeFieldStatusByFormCode(formCode, null, fieldCode, status);
    }

    /**
     * 更新指定表单  指定编码的字段的状态
     *
     * @param formCode
     * @param version
     * @param fieldCode
     * @param status
     * @return
     */
    Field changeFieldStatusByFormCode(String formCode, Integer version, String fieldCode, String status);

    /**
     * 禁用字段
     *
     * @param formDefinitionId
     * @param fieldCode
     * @return
     */
    default Field disableField(String formDefinitionId, String fieldCode) {
        return changeFieldStatus(formDefinitionId, fieldCode, StatusEntity.STATUS_DISABLE_STR);
    }

    default Field disableFieldByFormCode(String formCode, String fieldCode) {
        return disableFieldByFormCode(formCode, null, fieldCode);
    }

    /**
     * 根据表单编码禁用字段
     *
     * @param formCode
     * @param version
     * @param fieldCode
     * @return
     */
    default Field disableFieldByFormCode(String formCode, Integer version, String fieldCode) {
        return changeFieldStatusByFormCode(formCode, version, fieldCode, StatusEntity.STATUS_ENABLE_STR);
    }

    default Field enableField(String formDefinitionId, String fieldCode) {
        return changeFieldStatus(formDefinitionId, fieldCode, StatusEntity.STATUS_DISABLE_STR);
    }

    default Field enableFieldByFormCode(String formCode, String fieldCode) {
        return enableFieldByFormCode(formCode, null, fieldCode);
    }

    /**
     * 根据表单编码禁用字段
     *
     * @param formCode
     * @param version
     * @param fieldCode
     * @return
     */
    default Field enableFieldByFormCode(String formCode, Integer version, String fieldCode) {
        return changeFieldStatusByFormCode(formCode, version, fieldCode, StatusEntity.STATUS_ENABLE_STR);
    }


}
