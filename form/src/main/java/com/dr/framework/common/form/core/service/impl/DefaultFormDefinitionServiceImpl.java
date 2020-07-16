package com.dr.framework.common.form.core.service.impl;

import com.dr.framework.common.form.core.command.*;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.query.FormDefinitionQuery;
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
public class DefaultFormDefinitionServiceImpl implements FormDefinitionService {

    @Autowired
    protected CommandExecutor executor;

    @Override
    public Form addFormDefinition(Form form, Collection<Field> fields, boolean createTable) {
        return executor.execute(new FormDefinitionInsertCommand(form, fields, createTable));
    }

    //TODO
    @Override
    public Form updateFormDefinition(Form form, Collection<Field> fields, boolean updateTable, boolean copyData) {
        return null;
    }

    @Override
    public List<? extends Form> selectFormDefinitionByQuery(FormDefinitionQuery query) {
        return executor.execute(new FormDefinitionSelectCommand(query));
    }

    @Override
    public Page<? extends Form> selectPageFormDefinition(FormDefinitionQuery query, int pageIndex, int pageSize) {
        return executor.execute(new FormDefinitionSelectPageCommand(query, pageIndex, pageSize));
    }

    @Override
    public Form selectFormDefinitionById(String formDefinitionId) {
        return executor.execute(new FormDefinitionSelectByIdCommand(formDefinitionId));
    }

    @Override
    public Form selectFormDefinitionByCodeAndVersion(String formCode, Integer version) {
        return executor.execute(new FormDefinitionSelectByCodeAndVersionCommand(formCode, version));
    }

    @Override
    public Long removeFormDefinitionById(String formDefinitionId, boolean dropTable) {
        return executor.execute(new FormDefinitionRemoveCommand(formDefinitionId, dropTable));
    }

    @Override
    public Long removeFormDefinitionByCodeAndVersion(String formCode, Integer version, boolean dropTable) {
        return executor.execute(new FormDefinitionRemoveCommand(formCode, version, dropTable));
    }

    @Override
    public Field selectFieldByCode(String formDefinitionId, String fieldCode) {
        return executor.execute(new FormDefinitionFieldSelectOneCommand(formDefinitionId, fieldCode));
    }

    @Override
    public Field selectFieldByCodeAndVersion(String formCode, Integer version, String fieldCode) {
        return executor.execute(new FormDefinitionFieldSelectOneCommand(formCode, version, fieldCode));
    }

    @Override
    public Field addField(String formDefinitionId, Field field, boolean updateTable, boolean copyData) {
        return executor.execute(new FormDefinitionFieldAddCommand(formDefinitionId, updateTable, copyData, field));
    }

    @Override
    public Field addFieldByFormCode(String formCode, Integer version, Field field, boolean updateTable, boolean copyData) {
        return executor.execute(new FormDefinitionFieldAddCommand(formCode, version, updateTable, copyData, field));
    }

    @Override
    public Field changeField(String formDefinitionId, Field field, boolean updateTable, boolean copyData) {
        return executor.execute(new FormDefinitionFieldChangeCommand(formDefinitionId, updateTable, copyData, field));
    }

    @Override
    public Field changeFieldByFormCode(String formCode, Integer version, Field field, boolean updateTable, boolean copyData) {
        return executor.execute(new FormDefinitionFieldChangeCommand(formCode, version, updateTable, copyData, field));
    }

    @Override
    public Field removeField(String formDefinitionId, String fieldCode) {
        return executor.execute(new FormDefinitionFieldRemoveCommand(formDefinitionId, fieldCode));
    }

    @Override
    public Field removeFieldByFormCode(String formCode, Integer version, String fieldCode) {
        return executor.execute(new FormDefinitionFieldRemoveCommand(formCode, version, fieldCode));
    }

    @Override
    public Field changeFieldStatus(String formDefinitionId, String fieldCode, String status) {
        return executor.execute(new FormDefinitionFieldChangeStatusCommand(formDefinitionId, fieldCode, status));
    }

    @Override
    public Field changeFieldStatusByFormCode(String formCode, Integer version, String fieldCode, String status) {
        return executor.execute(new FormDefinitionFieldChangeStatusCommand(formCode, version, fieldCode, status));
    }


}
