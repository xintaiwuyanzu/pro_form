package com.dr.framework.common.form.display.service;

import com.dr.framework.common.form.display.model.FieldDisplay;
import com.dr.framework.common.form.display.model.FormDisplay;

import java.util.List;

/**
 * 表单显示方案相关的service
 * <p>
 * 表单显示方案没有版本的概念，版本只是用来联系表单定义的
 * <p>
 * 需要使用{@link com.dr.framework.common.form.engine.CommandPlugin}拦截命令模式
 * 拦截表单定义的增删改查，同步不同版本的表单显示方案定义
 *
 * @author caor
 */
public interface FormDisplayService {
    /**
     * 增删改的时候是否同步更新所有版本的显示方案定义
     */
    boolean DEFAULT_MODIFY_ALL_VERSION = true;

    /**
     * 添加表单显示方案
     *
     * @param formDisplay
     * @return
     */
    default FormDisplay insert(FormDisplay formDisplay) {
        return insert(formDisplay, DEFAULT_MODIFY_ALL_VERSION);
    }

    /**
     * 添加表单显示方案，并且更新所有版本的表单定义的显示方案
     *
     * @param formDisplay
     * @param modifyAllVersion
     * @return
     */
    FormDisplay insert(FormDisplay formDisplay, boolean modifyAllVersion);

    /**
     * 更新表单显示方案
     *
     * @param formDisplay
     * @return
     */
    default FormDisplay update(FormDisplay formDisplay) {
        return update(formDisplay, DEFAULT_MODIFY_ALL_VERSION);
    }

    /**
     * 更新表单显示方案，并且更新所有版本的表单定义
     *
     * @param formDisplay
     * @param modifyAllVersion
     * @return
     */
    FormDisplay update(FormDisplay formDisplay, boolean modifyAllVersion);

    /**
     * 删除表单显示方案定义
     *
     * @param formDisplayId
     * @return
     */
    default long deleteFormDisplay(String formDisplayId) {
        return deleteFormDisplay(formDisplayId, DEFAULT_MODIFY_ALL_VERSION);
    }

    /**
     * 根据表单显示方案Id删除表单显示方案定义
     *
     * @param formDisplayId
     * @param modifyAllVersion
     * @return
     */
    long deleteFormDisplay(String formDisplayId, boolean modifyAllVersion);

    /*
     * =======================================
     * 上面是表单显示方案基本信息的增删改
     * 下面是表单显示方案字段方案的增删改
     *
     * =======================================
     */

    default FieldDisplay insertField(FieldDisplay fieldDisplay) {
        return insertField(fieldDisplay, DEFAULT_MODIFY_ALL_VERSION);
    }

    /**
     * 添加表单字段显示方案定义
     *
     * @param fieldDisplay
     * @param modifyAllVersion
     * @return
     */
    FieldDisplay insertField(FieldDisplay fieldDisplay, boolean modifyAllVersion);

    default FieldDisplay updateField(FieldDisplay fieldDisplay) {
        return updateField(fieldDisplay, DEFAULT_MODIFY_ALL_VERSION);
    }

    /**
     * 更新字段显示方案
     *
     * @param fieldDisplay
     * @param modifyAllVersion
     * @return
     */
    FieldDisplay updateField(FieldDisplay fieldDisplay, boolean modifyAllVersion);

    /**
     * 删除表单字段显示方案
     *
     * @param fieldId
     * @return
     */
    default long deleteField(String fieldId) {
        return deleteFormDisplay(fieldId, DEFAULT_MODIFY_ALL_VERSION);
    }

    /**
     * 删除表单字段显示方案
     *
     * @param fieldId
     * @param modifyAllVersion
     * @return
     */
    long deleteField(String fieldId, boolean modifyAllVersion);


    /*
     * =======================================
     * 以下是查询方法
     * =======================================
     */

    /**
     * 根据主键直接获取一条显示方案
     *
     * @param formDisplayId
     * @return
     */
    FormDisplay getFormDisplayById(String formDisplayId);

    /**
     * 获取指定表单定义的所有显示方案
     *
     * @param formDefinitionId
     * @return
     */
    List<FormDisplay> getFormDisplay(String formDefinitionId);

    /**
     * 获取指定表单定义的指定显示类型的显示方案
     *
     * @param formDefinitionId
     * @param displayType
     * @return
     */
    List<FormDisplay> getFormDisplay(String formDefinitionId, String displayType);

    /**
     * 根据表单定义Id，显示类型，显示编码获取特定的一条显示方案
     *
     * @param formDefinitionId
     * @param displayType
     * @param displayCode
     * @return
     */
    FormDisplay getFormDisplay(String formDefinitionId, String displayType, String displayCode);

    /**
     * 根据表单编码获取指定所有的显示方案
     *
     * @param formCode
     * @return
     */
    default List<FormDisplay> getFormDisplayByCode(String formCode) {
        return getFormDisplayByCode(formCode, null);
    }

    /**
     * 根据表单编码和版本获取指定所有的显示方案
     *
     * @param formCode
     * @param version
     * @return
     */
    List<FormDisplay> getFormDisplayByCode(String formCode, Integer version);

    /**
     * 根据表单编码获取指定所有的显示方案
     *
     * @param formCode
     * @return
     */
    default List<FormDisplay> getFormDisplayByCodeAndDisplayType(String formCode, String displayType) {
        return getFormDisplayByCodeAndDisplayType(formCode, (Integer) null, displayType);
    }

    /**
     * 根据表单编码和版本获取指定所有的显示方案
     *
     * @param formCode
     * @param version
     * @return
     */
    List<FormDisplay> getFormDisplayByCodeAndDisplayType(String formCode, Integer version, String displayType);

    /**
     * 根据表单编码，默认版本，显示类型，显示编码获取特定的一条显示方案
     *
     * @param formCode
     * @param displayType
     * @param displayCode
     * @return
     */
    default FormDisplay getFormDisplayByCodeAndDisplayType(String formCode, String displayType, String displayCode) {
        return getFormDisplayByCodeAndDisplayType(formCode, displayType, displayCode);
    }

    /**
     * 根据表单编码，版本，显示类型，显示编码获取特定的一条显示方案
     *
     * @param formCode
     * @param version
     * @param displayType
     * @param displayCode
     * @return
     */
    FormDisplay getFormDisplayByCodeAndDisplayType(String formCode, Integer version, String displayType, String displayCode);
}
