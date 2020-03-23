package com.dr.framework.common.form.core.service.impl;

import com.dr.framework.common.form.core.command.WorkFormInsertCommand;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.engine.CommandExecutor;
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

    @Override
    public Form addFormDefinition(Form form, Collection<Field> fields, boolean createTable) {
        return executor.execute(new WorkFormInsertCommand(form, fields, createTable));
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
