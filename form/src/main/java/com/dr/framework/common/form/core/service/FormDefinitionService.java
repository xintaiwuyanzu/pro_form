package com.dr.framework.common.form.core.service;

import com.dr.framework.common.config.model.CommonMeta;
import com.dr.framework.common.config.model.MetaMap;
import com.dr.framework.common.entity.StatusEntity;
import com.dr.framework.common.form.core.query.FormDefinitionQuery;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.page.Page;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 表单定义service，用来抽象封装表单定义相关service
 *
 * @author dr
 */
public interface FormDefinitionService {


    /**
     * 表单定义元数据refType
     */
    String FORM_DEFINITION_META_TYPE = "formDefinition";
    /**
     * 字段元数据refType
     */
    String FORM_DEFINITION_FIELD_META_TYPE = "formDefinitionField";


    /**
     * 默认不动物理表结构，只是保存表定义
     */
    boolean DEFAULT_TABLE_OR_FIELD_CREATE = false;

    /**
     * 添加表单定义
     *
     * @param formModel   表基本信息定义
     * @param fieldModels 表字段定义
     * @return 返回表单定义
     */
    default FormModel addFormDefinition(FormModel formModel, Collection<FieldModel> fieldModels) {
        return addFormDefinition(formModel, fieldModels, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    /**
     * 添加表单定义
     *
     * @param formModel   表基本信息定义
     * @param fieldModels 表字段定义
     * @param createTable 是否创建表结构  true:生成； false:不生成
     * @return 返回表单定义
     */
    FormModel addFormDefinition(FormModel formModel, Collection<FieldModel> fieldModels, boolean createTable);

    /**
     * 更新表单基本信息
     *
     * @param formModel
     * @return
     */
    FormModel updateFormDefinitionBaseInfo(FormModel formModel);

    /**
     * 根据表单定义生成表结构
     *
     * @param formDefinitionId
     */
    FormModel checkAndGenTable(String formDefinitionId);

    /**
     * 根据表单定义生成表结构
     *
     * @param formCode
     */
    FormModel checkAndGenTableByCodeAndVersion(String formCode, Integer version);

    /**
     * 根据表单编码生成查找默认的表单定义，并生成表结构
     *
     * @param formCode
     * @return
     */
    default FormModel checkAndGenDefaultTableByCode(String formCode) {
        return checkAndGenTableByCodeAndVersion(formCode, null);
    }

    /**
     * 根据表单编码生成所有的表结构
     *
     * @param formCode
     * @return
     */
    List<? extends FormModel> checkAndGenAllTableByCode(String formCode);

    /**
     * 查询表单定义
     *
     * @param query 查询条件
     * @return
     */
    List<? extends FormModel> selectFormDefinitionByQuery(FormDefinitionQuery query);

    /**
     * 根据条件查询表单定义分页
     *
     * @param query
     * @param pageIndex
     * @return
     */
    default Page<? extends FormModel> selectPageFormDefinition(FormDefinitionQuery query, int pageIndex) {
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
    Page<? extends FormModel> selectPageFormDefinition(FormDefinitionQuery query, int pageIndex, int pageSize);

    /**
     * 查询单一表单定义数据
     *
     * @param formDefinitionId
     * @return
     */
    FormModel selectFormDefinitionById(String formDefinitionId);

    /**
     * 根据表单定义编码查询默认版本的表单定义
     *
     * @param formCode
     * @return
     */
    default FormModel selectFormDefinitionByCode(String formCode) {
        return selectFormDefinitionByCodeAndVersion(formCode, null);
    }

    /**
     * 根据编码和版本查询表单定义
     *
     * @param formCode
     * @param version  版本为空则查询默认的表单
     * @return
     */
    FormModel selectFormDefinitionByCodeAndVersion(String formCode, Integer version);

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
    FieldModel selectFieldByCode(String formDefinitionId, String fieldCode);

    /**
     * 根据表单定义Id 和字段编码 查询字段信息
     *
     * @param formCode
     * @param version
     * @param fieldCode
     * @return
     */
    FieldModel selectFieldByCodeAndVersion(String formCode, Integer version, String fieldCode);

    /**
     * 添加表单字段
     *
     * @param formDefinitionId
     * @param fieldModel
     * @return
     */
    default FieldModel addField(String formDefinitionId, FieldModel fieldModel) {
        return addField(formDefinitionId, fieldModel, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    /**
     * 添加表单字段
     *
     * @param formDefinitionId 表单定义ID
     * @param fieldModel       新字段
     * @param updateTable      更新表结构
     * @return
     */
    default FieldModel addField(String formDefinitionId, FieldModel fieldModel, boolean updateTable) {
        return addField(formDefinitionId, fieldModel, updateTable, updateTable);
    }

    /**
     * 添加表单字段
     *
     * @param formDefinitionId
     * @param fieldModel
     * @param updateTable
     * @param copyData
     * @return
     */
    FieldModel addField(String formDefinitionId, FieldModel fieldModel, boolean updateTable, boolean copyData);

    /**
     * 根据Id设置元数据
     *
     * @param formDefinitionId
     * @param key
     * @param value
     * @return
     */
    default CommonMeta setMeta(String formDefinitionId, String key, String value) {
        return setMeta(formDefinitionId, Collections.singletonMap(key, value)).getMeta(0);
    }

    /**
     * 根据hashMap 设置元数据
     *
     * @param formDefinitionId
     * @param metas
     * @return
     */
    MetaMap setMeta(String formDefinitionId, Map<String, String> metas);

    default CommonMeta setMetaByFormCode(String formCode, String key, String value) {
        return setMetaByFormCode(formCode, null, key, value);
    }

    /**
     * 根据表单编码和版本设置表单元数据
     *
     * @param formCode
     * @param version
     * @param key
     * @param value
     * @return
     */
    default CommonMeta setMetaByFormCode(String formCode, Integer version, String key, String value) {
        return setMetaByFormCode(formCode, version, Collections.singletonMap(key, value)).getMeta(0);
    }

    default MetaMap setMetaByFormCode(String formCode, Map<String, String> metas) {
        return setMetaByFormCode(formCode, null, metas);
    }

    /**
     * 根据表单编码和版本 hashMap 设置表单元数据
     *
     * @param formCode
     * @param version
     * @param metas
     * @return
     */
    MetaMap setMetaByFormCode(String formCode, Integer version, Map<String, String> metas);

    default FieldModel addFieldByFormCode(String formCode, FieldModel fieldModel) {
        return addFieldByFormCode(formCode, fieldModel, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    default FieldModel addFieldByFormCode(String formCode, FieldModel fieldModel, boolean updateTable) {
        return addFieldByFormCode(formCode, fieldModel, updateTable, updateTable);
    }

    default FieldModel addFieldByFormCode(String formCode, FieldModel fieldModel, boolean updateTable, boolean copyData) {
        return addFieldByFormCode(formCode, null, fieldModel, updateTable, copyData);
    }

    default FieldModel addFieldByFormCode(String formCode, Integer version, FieldModel fieldModel) {
        return addFieldByFormCode(formCode, version, fieldModel, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    default FieldModel addFieldByFormCode(String formCode, Integer version, FieldModel fieldModel, boolean updateTable) {
        return addFieldByFormCode(formCode, version, fieldModel, updateTable, updateTable);
    }

    /**
     * 根据表单编码添加字段
     *
     * @param formCode    表单编码
     * @param version     表单版本
     * @param fieldModel  添加的字段
     * @param updateTable 更新表结构
     * @param copyData    是否复制数据
     * @return
     */
    FieldModel addFieldByFormCode(String formCode, Integer version, FieldModel fieldModel, boolean updateTable, boolean copyData);

    /**
     * 添加表单字段
     *
     * @param formDefinitionId
     * @param fieldModel
     * @return
     */
    default FieldModel changeField(String formDefinitionId, FieldModel fieldModel) {
        return changeField(formDefinitionId, fieldModel, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    /**
     * 更新字段
     *
     * @param formDefinitionId 表单定义
     * @param fieldModel       字段
     * @param updateTable      更新表结构
     * @return
     */
    default FieldModel changeField(String formDefinitionId, FieldModel fieldModel, boolean updateTable) {
        return changeField(formDefinitionId, fieldModel, updateTable, updateTable);
    }

    /**
     * 更新字段
     *
     * @param formDefinitionId 表单定义
     * @param fieldModel       字段
     * @param updateTable      更新表结构
     * @return
     */
    FieldModel changeField(String formDefinitionId, FieldModel fieldModel, boolean updateTable, boolean copyData);

    default FieldModel changeFieldByFormCode(String formCode, FieldModel fieldModel) {
        return changeFieldByFormCode(formCode, fieldModel, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    default FieldModel changeFieldByFormCode(String formCode, FieldModel fieldModel, boolean updateTable) {
        return changeFieldByFormCode(formCode, fieldModel, updateTable, updateTable);
    }

    default FieldModel changeFieldByFormCode(String formCode, FieldModel fieldModel, boolean updateTable, boolean copyData) {
        return changeFieldByFormCode(formCode, null, fieldModel, updateTable, copyData);
    }

    default FieldModel changeFieldByFormCode(String formCode, Integer version, FieldModel fieldModel) {
        return changeFieldByFormCode(formCode, version, fieldModel, DEFAULT_TABLE_OR_FIELD_CREATE);
    }

    default FieldModel changeFieldByFormCode(String formCode, Integer version, FieldModel fieldModel, boolean updateTable) {
        return changeFieldByFormCode(formCode, version, fieldModel, updateTable, updateTable);
    }

    /**
     * 根据表单编码和版本更新字段
     * 如果更改数据类型的话，会创建新的表结构
     *
     * @param formCode    表单编码
     * @param version     表单版本
     * @param fieldModel  字段
     * @param updateTable 更新表结构
     * @param copyData    是否复制数据
     * @return
     */
    FieldModel changeFieldByFormCode(String formCode, Integer version, FieldModel fieldModel, boolean updateTable, boolean copyData);

    /**
     * 删除字段Id
     *
     * @param formDefinitionId
     * @param fieldCode
     * @return
     */
    FieldModel removeField(String formDefinitionId, String fieldCode);


    default FieldModel removeFieldByFormCode(String formCode, String fieldCode) {
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
    FieldModel removeFieldByFormCode(String formCode, Integer version, String fieldCode);

    /**
     * 更新指定表单  指定编码的字段的状态
     *
     * @param formDefinitionId
     * @param fieldCode
     * @param status           状态
     * @return
     */
    FieldModel changeFieldStatus(String formDefinitionId, String fieldCode, String status);

    default FieldModel changeFieldStatusByFormCode(String formCode, String fieldCode, String status) {
        return changeFieldStatusByFormCode(formCode, null, fieldCode, status);
    }

    /**
     * 根据Id和编码设置元数据
     *
     * @param formDefinitionId
     * @param fieldCode
     * @param key
     * @param value
     * @return
     */
    default CommonMeta setFieldMeta(String formDefinitionId, String fieldCode, String key, String value) {
        return setFieldMeta(formDefinitionId, fieldCode, Collections.singletonMap(key, value)).getMeta(0);
    }

    /**
     * 根据hashMap 设置元数据
     *
     * @param formDefinitionId
     * @param metas
     * @return
     */
    MetaMap setFieldMeta(String formDefinitionId, String fieldCode, Map<String, String> metas);

    default CommonMeta setFieldMetaByFormCode(String formCode, String fieldCode, String key, String value) {
        return setFieldMetaByFormCode(formCode, null, fieldCode, key, value);
    }

    /**
     * 根据表单编码和版本设置表单元数据
     *
     * @param formCode
     * @param version
     * @param key
     * @param value
     * @return
     */
    default CommonMeta setFieldMetaByFormCode(String formCode, Integer version, String fieldCode, String key, String value) {
        return setFieldMetaByFormCode(formCode, version, fieldCode, Collections.singletonMap(key, value)).getMeta(0);
    }

    default MetaMap setFieldMetaByFormCode(String formCode, String fieldCode, Map<String, String> metas) {
        return setFieldMetaByFormCode(formCode, null, fieldCode, metas);
    }

    /**
     * 根据表单编码和版本 hashMap 设置表单元数据
     *
     * @param formCode
     * @param version
     * @param metas
     * @return
     */
    MetaMap setFieldMetaByFormCode(String formCode, Integer version, String fieldCode, Map<String, String> metas);

    /**
     * 更新指定表单  指定编码的字段的状态
     *
     * @param formCode
     * @param version
     * @param fieldCode
     * @param status
     * @return
     */
    FieldModel changeFieldStatusByFormCode(String formCode, Integer version, String fieldCode, String status);

    /**
     * 禁用字段
     *
     * @param formDefinitionId
     * @param fieldCode
     * @return
     */
    default FieldModel disableField(String formDefinitionId, String fieldCode) {
        return changeFieldStatus(formDefinitionId, fieldCode, StatusEntity.STATUS_DISABLE_STR);
    }

    default FieldModel disableFieldByFormCode(String formCode, String fieldCode) {
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
    default FieldModel disableFieldByFormCode(String formCode, Integer version, String fieldCode) {
        return changeFieldStatusByFormCode(formCode, version, fieldCode, StatusEntity.STATUS_DISABLE_STR);
    }

    default FieldModel enableField(String formDefinitionId, String fieldCode) {
        return changeFieldStatus(formDefinitionId, fieldCode, StatusEntity.STATUS_ENABLE_STR);
    }

    default FieldModel enableFieldByFormCode(String formCode, String fieldCode) {
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
    default FieldModel enableFieldByFormCode(String formCode, Integer version, String fieldCode) {
        return changeFieldStatusByFormCode(formCode, version, fieldCode, StatusEntity.STATUS_ENABLE_STR);
    }


}
