package com.dr.framework.common.form.core.service.impl;

import com.dr.framework.common.config.model.MetaMap;
import com.dr.framework.common.form.core.command.*;
import com.dr.framework.common.form.core.query.FormDefinitionQuery;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.engine.impl.service.AbstractFormService;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.page.Page;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 默认表单定义实现
 *
 * @author dr
 */
public class DefaultFormDefinitionServiceImpl extends AbstractFormService implements FormDefinitionService {

    @Override
    public FormModel addFormDefinition(FormModel formModel, Collection<FieldModel> fieldModels, boolean createTable) {
        return execute(new FormDefinitionInsertCommand(formModel, fieldModels, createTable));
    }

    @Override
    public FormModel updateFormDefinitionBaseInfo(FormModel formModel) {
        return execute(new FormDefinitionUpdateBaseInfoCommand(formModel));
    }

    @Override
    public FormModel checkAndGenTable(String formDefinitionId) {
        return execute(new FormDefinitionGenTableCommand(formDefinitionId));
    }

    @Override
    public FormModel checkAndGenTableByCodeAndVersion(String formCode, Integer version) {
        return execute(new FormDefinitionGenTableCommand(formCode, version));
    }

    @Override
    public List<? extends FormModel> checkAndGenAllTableByCode(String formCode) {
        return execute(new FormDefinitionGenAllTableByCodeCommand(formCode));
    }

    @Override
    public List<? extends FormModel> selectFormDefinitionByQuery(FormDefinitionQuery query) {
        return execute(new FormDefinitionSelectCommand(query));
    }

    @Override
    public Page<? extends FormModel> selectPageFormDefinition(FormDefinitionQuery query, int pageIndex, int pageSize) {
        return execute(new FormDefinitionSelectPageCommand(query, pageIndex, pageSize));
    }

    @Override
    public FormModel selectFormDefinitionById(String formDefinitionId) {
        return execute(new FormDefinitionSelectByIdCommand(formDefinitionId));
    }

    @Override
    public FormModel selectFormDefinitionByCodeAndVersion(String formCode, Integer version) {
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
    public FieldModel selectFieldByCode(String formDefinitionId, String fieldCode) {
        return execute(new FormDefinitionFieldSelectOneCommand(formDefinitionId, fieldCode));
    }

    @Override
    public FieldModel selectFieldByCodeAndVersion(String formCode, Integer version, String fieldCode) {
        return execute(new FormDefinitionFieldSelectOneCommand(formCode, version, fieldCode));
    }

    @Override
    public Collection<? extends FieldModel> addFields(String formDefinitionId, Collection<? extends FieldModel> fieldModel, boolean updateTable, boolean copyData) {
        return execute(new FormDefinitionFieldAddCommand(formDefinitionId, updateTable, copyData, fieldModel.toArray(fieldModel.toArray(new FieldModel[0]))));
    }

    @Override
    public Collection<? extends FieldModel> addFieldsWithOutUpdateVersion(String formDefinitionId, Collection<? extends FieldModel> fieldModel, boolean updateTable) {
        FormDefinitionFieldAddCommand command = new FormDefinitionFieldAddCommand(formDefinitionId, updateTable, false, fieldModel.toArray(fieldModel.toArray(new FieldModel[0])));
        command.setUpdateVersion(false);
        return execute(command);
    }

    @Override
    public MetaMap setMeta(String formDefinitionId, Map<String, String> metas) {
        return execute(new FormDefinitionAddMetaCommand(formDefinitionId, metas));
    }

    @Override
    public MetaMap setMetaByFormCode(String formCode, Integer version, Map<String, String> metas) {
        return execute(new FormDefinitionAddMetaCommand(formCode, version, metas));
    }

    @Override
    public Collection<? extends FieldModel> addFieldsByFormCode(String formCode, Integer version, Collection<? extends FieldModel> fieldModel, boolean updateTable, boolean copyData) {
        return execute(new FormDefinitionFieldAddCommand(formCode, version, updateTable, copyData, fieldModel.toArray(fieldModel.toArray(new FieldModel[0]))));
    }

    @Override
    public Collection<? extends FieldModel> changeFields(String formDefinitionId, Collection<? extends FieldModel> fieldModel, boolean updateTable, boolean copyData) {
        return execute(new FormDefinitionFieldChangeCommand(formDefinitionId, updateTable, copyData, fieldModel.toArray(fieldModel.toArray(new FieldModel[0]))));
    }

    @Override
    public Collection<? extends FieldModel> changeFieldsByFormCode(String formCode, Integer version, Collection<? extends FieldModel> fieldModel, boolean updateTable, boolean copyData) {
        return execute(new FormDefinitionFieldChangeCommand(formCode, version, updateTable, copyData, fieldModel.toArray(fieldModel.toArray(new FieldModel[0]))));
    }

    @Override
    public FieldModel removeField(String formDefinitionId, String fieldCode) {
        return execute(new FormDefinitionFieldRemoveCommand(formDefinitionId, fieldCode));
    }

    @Override
    public FieldModel removeFieldByFormCode(String formCode, Integer version, String fieldCode) {
        return execute(new FormDefinitionFieldRemoveCommand(formCode, version, fieldCode));
    }

    @Override
    public FieldModel changeFieldStatus(String formDefinitionId, String fieldCode, String status) {
        return execute(new FormDefinitionFieldChangeStatusCommand(formDefinitionId, fieldCode, status));
    }

    @Override
    public MetaMap setFieldMeta(String formDefinitionId, String fieldCode, Map<String, String> metas) {
        return execute(new FormDefinitionFieldAddMetaCommand(formDefinitionId, fieldCode, metas));
    }

    @Override
    public MetaMap setFieldMetaByFormCode(String formCode, Integer version, String fieldCode, Map<String, String> metas) {
        return execute(new FormDefinitionFieldAddMetaCommand(formCode, version, fieldCode, metas));
    }

    @Override
    public FieldModel changeFieldStatusByFormCode(String formCode, Integer version, String fieldCode, String status) {
        return execute(new FormDefinitionFieldChangeStatusCommand(formCode, version, fieldCode, status));
    }


}
