package com.dr.framework.common.form.core.service.impl;

import com.dr.framework.common.form.core.command.*;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.service.FormDataService;
import com.dr.framework.common.form.core.service.SqlBuilder;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.page.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FormDataServiceImpl implements FormDataService {

    @Autowired
    protected CommandExecutor executor;

    @Override
    public FormData addFormData(FormData formData, boolean autoCheck) {
        return executor.execute(new FormDataInsertCommand(formData, autoCheck));
    }

    @Override
    public FormData updateFormDataById(FormData formData) {
        return executor.execute(new FormDataUpdateCommand(formData, false));
    }

    @Override
    public FormData updateFormDataIgnoreNullById(FormData formData) {
        return executor.execute(new FormDataUpdateCommand(formData, true));
    }

    @Override
    public Long updateFormDataBySqlBuilder(String formDefinitionId, SqlBuilder sqlBuilder, boolean autoCheck) {
        return executor.execute(new FormDataUpdateBySqlCommand(formDefinitionId, autoCheck, sqlBuilder, false));
    }

    @Override
    public Long updateFormDataIgnoreNullBySqlBuilder(String formDefinitionId, SqlBuilder sqlBuilder, boolean autoCheck) {
        return executor.execute(new FormDataUpdateBySqlCommand(formDefinitionId, autoCheck, sqlBuilder, true));
    }

    @Override
    public Long updateFormDataBySqlBuilderAndFormCode(String formCode, Integer version, SqlBuilder sqlBuilder, boolean autoCheck) {
        return executor.execute(new FormDataUpdateBySqlCommand(formCode, version, autoCheck, sqlBuilder, false));
    }

    @Override
    public Long updateFormDataIgnoreNullBySqlBuilderAndFormCode(String formCode, Integer version, SqlBuilder sqlBuilder, boolean autoCheck) {
        return executor.execute(new FormDataUpdateBySqlCommand(formCode, version, autoCheck, sqlBuilder, true));
    }


    @Override
    public List<FormData> selectFormData(String formDefinitionId, SqlBuilder sqlBuilder) {
        return executor.execute(new FormDataSelectCommand(formDefinitionId, false, sqlBuilder, true));
    }

    @Override
    public List<FormData> selectSelfColumnFormData(String formDefinitionId, SqlBuilder sqlBuilder) {
        return executor.execute(new FormDataSelectCommand(formDefinitionId, false, sqlBuilder, false));
    }

    @Override
    public List<FormData> selectFormDataByFormCode(String formCode, Integer version, SqlBuilder sqlBuilder) {
        return executor.execute(new FormDataSelectCommand(formCode, version, false, sqlBuilder, true));
    }

    @Override
    public List<FormData> selectSelfColumnFormDataByFormCode(String formCode, Integer version, SqlBuilder sqlBuilder) {
        return executor.execute(new FormDataSelectCommand(formCode, version, false, sqlBuilder, false));
    }

    @Override
    public FormData selectOneFormData(String formDefinitionId, String formDataId) {
        return executor.execute(new FormDataSelectOneCommand(formDefinitionId, false, formDataId));
    }

    @Override
    public FormData selectOneFormDataByFormCode(String formCode, Integer version, String formDataId) {
        return executor.execute(new FormDataSelectOneCommand(formCode, version, false, formDataId));
    }

    @Override
    public Page<FormData> selectPageFormData(String formDefinitionId, SqlBuilder sqlBuilder, int pageIndex, int pageSize) {
        return executor.execute(new FormDataSelectPageCommand(formDefinitionId, false, sqlBuilder, pageIndex, pageSize));
    }

    @Override
    public Page<FormData> selectPageFormDataByFormCode(String formCode, Integer version, SqlBuilder sqlBuilder, int pageIndex, int pageSize) {
        return executor.execute(new FormDataSelectPageCommand(formCode, version, false, sqlBuilder, pageIndex, pageSize));
    }

    @Override
    public Long removeFormData(String formDefinitionId, SqlBuilder sqlBuilder) {
        return executor.execute(new FormDataRemoveBySqlCommand(formDefinitionId, false, sqlBuilder));
    }

    @Override
    public Long removeFormDataByFormCode(String formCode, Integer version, SqlBuilder sqlBuilder) {
        return executor.execute(new FormDataRemoveBySqlCommand(formCode, version, false, sqlBuilder));
    }
}
