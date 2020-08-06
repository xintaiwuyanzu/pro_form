package com.dr.framework.common.form.core.service.impl;

import com.dr.framework.common.form.core.command.*;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.query.FormDefinitionQuery;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.engine.impl.AbstractFormService;
import com.dr.framework.common.page.Page;

import java.util.Collection;
import java.util.List;

/**
 * 默认表单定义实现
 *
 * @author dr
 */
public class DefaultFormDefinitionServiceImpl extends AbstractFormService implements FormDefinitionService {

    @Override
    public Form addFormDefinition(Form form, Collection<Field> fields, boolean createTable) {
        return execute(new FormDefinitionInsertCommand(form, fields, createTable));
    }

    @Override
    public Form updateFormDefinitionBaseInfo(Form form) {
        return execute(new FormDefinitionUpdateBaseInfoCommand(form));
    }

    @Override
    public Form checkAndGenTable(String formDefinitionId) {
        return execute(new FormDefinitionGenTableCommand(formDefinitionId));
    }

    @Override
    public Form checkAndGenTableByCodeAndVersion(String formCode, Integer version) {
        return execute(new FormDefinitionGenTableCommand(formCode, version));
    }

    @Override
    public List<? extends Form> checkAndGenAllTableByCode(String formCode) {
        return execute(new FormDefinitionGenAllTableByCodeCommand(formCode));
    }

    @Override
    public List<? extends Form> selectFormDefinitionByQuery(FormDefinitionQuery query) {
        return execute(new FormDefinitionSelectCommand(query));
    }

    @Override
    public Page<? extends Form> selectPageFormDefinition(FormDefinitionQuery query, int pageIndex, int pageSize) {
        return execute(new FormDefinitionSelectPageCommand(query, pageIndex, pageSize));
    }

    @Override
    public Form selectFormDefinitionById(String formDefinitionId) {
        return execute(new FormDefinitionSelectByIdCommand(formDefinitionId));
    }

    @Override
    public Form selectFormDefinitionByCodeAndVersion(String formCode, Integer version) {
        return execute(new FormDefinitionSelectByCodeAndVersionCommand(formCode, version));
    }

    @Override
    public Long removeFormDefinitionById(String formDefinitionId, boolean dropTable) {
        return execute(new FormDefinitionRemoveCommand(formDefinitionId, dropTable));
    }

    @Override
    public Long removeFormDefinitionByCodeAndVersion(String formCode, Integer version, boolean dropTable) {
        return execute(new FormDefinitionRemoveCommand(formCode, version, dropTable));
    }

    @Override
    public Field selectFieldByCode(String formDefinitionId, String fieldCode) {
        return execute(new FormDefinitionFieldSelectOneCommand(formDefinitionId, fieldCode));
    }

    @Override
    public Field selectFieldByCodeAndVersion(String formCode, Integer version, String fieldCode) {
        return execute(new FormDefinitionFieldSelectOneCommand(formCode, version, fieldCode));
    }

    @Override
    public Field addField(String formDefinitionId, Field field, boolean updateTable, boolean copyData) {
        return execute(new FormDefinitionFieldAddCommand(formDefinitionId, updateTable, copyData, field));
    }

    @Override
    public Field addFieldByFormCode(String formCode, Integer version, Field field, boolean updateTable, boolean copyData) {
        return execute(new FormDefinitionFieldAddCommand(formCode, version, updateTable, copyData, field));
    }

    @Override
    public Field changeField(String formDefinitionId, Field field, boolean updateTable, boolean copyData) {
        return execute(new FormDefinitionFieldChangeCommand(formDefinitionId, updateTable, copyData, field));
    }

    @Override
    public Field changeFieldByFormCode(String formCode, Integer version, Field field, boolean updateTable, boolean copyData) {
        return execute(new FormDefinitionFieldChangeCommand(formCode, version, updateTable, copyData, field));
    }

    @Override
    public Field removeField(String formDefinitionId, String fieldCode) {
        return execute(new FormDefinitionFieldRemoveCommand(formDefinitionId, fieldCode));
    }

    @Override
    public Field removeFieldByFormCode(String formCode, Integer version, String fieldCode) {
        return execute(new FormDefinitionFieldRemoveCommand(formCode, version, fieldCode));
    }

    @Override
    public Field changeFieldStatus(String formDefinitionId, String fieldCode, String status) {
        return execute(new FormDefinitionFieldChangeStatusCommand(formDefinitionId, fieldCode, status));
    }

    @Override
    public Field changeFieldStatusByFormCode(String formCode, Integer version, String fieldCode, String status) {
        return execute(new FormDefinitionFieldChangeStatusCommand(formCode, version, fieldCode, status));
    }


}
