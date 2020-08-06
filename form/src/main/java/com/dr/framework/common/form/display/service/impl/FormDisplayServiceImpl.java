package com.dr.framework.common.form.display.service.impl;

import com.dr.framework.common.form.display.model.FieldDisplay;
import com.dr.framework.common.form.display.model.FormDisplay;
import com.dr.framework.common.form.display.service.FormDisplayService;
import com.dr.framework.common.form.engine.impl.AbstractFormService;

import java.util.List;

/**
 * 显示方案service实现
 */
public class FormDisplayServiceImpl extends AbstractFormService implements FormDisplayService {
    @Override
    public FormDisplay insert(FormDisplay formDisplay, boolean modifyAllVersion) {
        return null;
    }

    @Override
    public FormDisplay update(FormDisplay formDisplay, boolean modifyAllVersion) {
        return null;
    }

    @Override
    public long deleteFormDisplay(String formDisplayId, boolean modifyAllVersion) {
        return 0;
    }

    @Override
    public FieldDisplay insertField(FieldDisplay fieldDisplay, boolean modifyAllVersion) {
        return null;
    }

    @Override
    public FieldDisplay updateField(FieldDisplay fieldDisplay, boolean modifyAllVersion) {
        return null;
    }

    @Override
    public long deleteField(String fieldId, boolean modifyAllVersion) {
        return 0;
    }

    @Override
    public FormDisplay getFormDisplayById(String formDisplayId) {
        return null;
    }

    @Override
    public List<FormDisplay> getFormDisplay(String formDefinitionId) {
        return null;
    }

    @Override
    public List<FormDisplay> getFormDisplay(String formDefinitionId, String displayType) {
        return null;
    }

    @Override
    public FormDisplay getFormDisplay(String formDefinitionId, String displayType, String displayCode) {
        return null;
    }

    @Override
    public List<FormDisplay> getFormDisplayByCode(String formCode, Integer version) {
        return null;
    }

    @Override
    public List<FormDisplay> getFormDisplayByCodeAndDisplayType(String formCode, Integer version, String displayType) {
        return null;
    }

    @Override
    public FormDisplay getFormDisplayByCodeAndDisplayType(String formCode, Integer version, String displayType, String displayCode) {
        return null;
    }
}
