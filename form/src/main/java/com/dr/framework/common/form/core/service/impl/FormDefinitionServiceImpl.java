package com.dr.framework.common.form.core.service.impl;

import com.dr.framework.common.form.core.command.*;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.page.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * 默认表单定义实现
 *
 * @author dr
 */
public class FormDefinitionServiceImpl implements FormDefinitionService {
    @Autowired
    protected CommandExecutor executor;

    /**
     * 添加表单定义
     *
     * @param form        表基本信息定义
     * @param fields      表字段定义
     * @param createTable 是否创建表结构
     */
    @Override
    public Form addFormDefinition(Form form, Collection<Field> fields, boolean createTable) {
        return executor.execute(new FormDefinitionInsertCommand(form, fields, createTable));
    }

    /**
     * 更新表单定义
     *
     * @param form
     * @param fields
     * @param createTable
     * @return
     */
    @Override
    public Form updateFormDefinition(Form form, Collection<Field> fields, boolean createTable) {
        return executor.execute(new FormDefinitionInsertCommand(form, fields, createTable));
    }

    /**
     * 查询表单定义
     *
     * @param formId
     * @param formCode
     * @param formType
     * @param formName
     * @return
     */
    @Override
    public List<Form> selectFormDefinition(String formId, String formCode, String formType, String formName) {
        return executor.execute(new FormDefinitionSelectCommand(formId, formCode, formType, formName));
    }

    /**
     * 分页查询表单定义数据
     *
     * @param form
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Page<Form> selectPageFormDefinition(Form form, int pageIndex, int pageSize) {
        return executor.execute(new FormDefinitionSelectPageCommand(form, pageIndex, pageSize));
    }

    @Override
    public Form selectOneFormDefinition(String formId, String formDtaId) {
        return executor.execute(new FormDefinitionSelectOneCommand(formId, formDtaId));
    }

    /**
     * 删除表单定义
     *
     * @param formId
     * @param retain
     * @return
     */
    @Override
    public Long removeFormDefinition(String formId, boolean retain) {
        return executor.execute(new FormDefinitionRemoveCommand(formId, retain));
    }

    @Override
    public List<Field> getFieldListByTabId(String tabId) {
        return null;
    }

    @Override
    public List<Field> getFieldListByTabCode(String tabCode) {
        return null;
    }

    @Override
    public List<Field> getTabListByTabId(String tabId) {
        return null;
    }

    @Override
    public List<Field> getFieldList() {
        return null;
    }

    @Override
    public List<Field> getFieldListByFormId(String formId) {
        return null;
    }

    @Override
    public List<Field> getFieldListByFormCode(String formCode) {
        return null;
    }

    @Override
    public int addFieldByFormId(String formId, Field field) {
        return 0;
    }

    @Override
    public int updateFieldByFormId(String formId, Field field) {
        return 0;
    }

    @Override
    public int deleteFieldByFormId(String formId, Field field) {
        return 0;
    }
}
