package com.dr.framework.common.form.display.service.impl;

import com.dr.framework.common.config.model.MetaMap;
import com.dr.framework.common.form.display.command.*;
import com.dr.framework.common.form.display.service.FormDisplayService;
import com.dr.framework.common.form.engine.impl.service.AbstractFormService;
import com.dr.framework.common.form.engine.model.display.FieldDisplay;
import com.dr.framework.common.form.engine.model.display.FormDisplay;

import java.util.List;
import java.util.Map;

/**
 * 显示方案service实现
 *
 * @author dr
 */
public class FormDisplayServiceImpl extends AbstractFormService implements FormDisplayService {
    @Override
    public FormDisplay insert(FormDisplay formDisplay, boolean modifyAllVersion) {
        return execute(new FormDisplayAddCommand(formDisplay, modifyAllVersion));
    }

    @Override
    public MetaMap setMeta(String formDisplayId, Map<String, String> metas) {
        return execute(new FormDisplayAddMetaCommand(formDisplayId, metas));
    }

    @Override
    public FormDisplay update(FormDisplay formDisplay, boolean modifyAllVersion) {
        return execute(new FormDisplayUpdateCommand(formDisplay, modifyAllVersion));
    }

    @Override
    public long deleteFormDisplay(String formDisplayId, boolean modifyAllVersion) {
        return execute(new FormDisplayRemoveCommand(formDisplayId, modifyAllVersion));
    }

    @Override
    public FieldDisplay insertField(FieldDisplay fieldDisplay, boolean modifyAllVersion) {
        return execute(new FormDisplayFieldAddCommand(fieldDisplay, modifyAllVersion));
    }

    @Override
    public MetaMap setFieldMeta(String displayId, String fieldCode, Map<String, String> metas) {
        return execute(new FormDisplayFieldAddMetaCommand(displayId, fieldCode, metas));
    }


    @Override
    public FieldDisplay updateField(FieldDisplay fieldDisplay, boolean modifyAllVersion) {
        return execute(new FormDisplayFieldUpdateCommand(fieldDisplay, modifyAllVersion));
    }

    @Override
    public long deleteField(String fieldId, boolean modifyAllVersion) {
        return execute(new FormDisplayFieldRemoveCommand(fieldId, modifyAllVersion));
    }

    @Override
    public FormDisplay getFormDisplayById(String formDisplayId) {
        return execute(new FormDisplaySelectByIdCommand(formDisplayId));
    }

    @Override
    public List<FormDisplay> getFormDisplay(String formDefinitionId, String displayType) {
        return execute(new FormDisplaySelectListCommand(formDefinitionId, displayType));
    }

    @Override
    public List<FormDisplay> getFormDisplayByCodeAndDisplayType(String formCode, Integer version, String displayType) {
        return execute(new FormDisplaySelectListCommand(formCode, version, displayType));
    }

    @Override
    public FormDisplay getFormDisplay(String formDefinitionId, String displayType, String displayCode) {
        return execute(new FormDisplaySelectOneCommand(formDefinitionId, displayType, displayCode));
    }


    @Override
    public FormDisplay getFormDisplayByCodeAndDisplayType(String formCode, Integer version, String displayType, String displayCode) {
        return execute(new FormDisplaySelectOneCommand(formCode, version, displayType, displayCode));
    }
}
