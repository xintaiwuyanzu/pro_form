package com.dr.framework.common.form.core.service.impl;

import com.dr.framework.common.form.core.command.*;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.service.FormDataService;
import com.dr.framework.common.form.core.service.SqlBuilder;
import com.dr.framework.common.form.engine.impl.service.AbstractFormService;
import com.dr.framework.common.page.Page;

import java.util.List;

public class FormDataServiceImpl extends AbstractFormService implements FormDataService {

    @Override
    public FormData addFormData(FormData formData, boolean autoCheck) {
        return execute(new FormDataInsertCommand(formData, autoCheck));
    }

    @Override
    public FormData updateFormDataById(FormData formData) {
        return execute(new FormDataUpdateCommand(formData, false));
    }

    @Override
    public FormData updateFormDataIgnoreNullById(FormData formData) {
        return execute(new FormDataUpdateCommand(formData, true));
    }

    @Override
    public Long updateFormDataBySqlBuilder(String formDefinitionId, SqlBuilder sqlBuilder, boolean autoCheck) {
        return execute(new FormDataUpdateBySqlCommand(formDefinitionId, autoCheck, sqlBuilder, false));
    }

    @Override
    public Long updateFormDataIgnoreNullBySqlBuilder(String formDefinitionId, SqlBuilder sqlBuilder, boolean autoCheck) {
        return execute(new FormDataUpdateBySqlCommand(formDefinitionId, autoCheck, sqlBuilder, true));
    }

    @Override
    public Long updateFormDataBySqlBuilderAndFormCode(String formCode, Integer version, SqlBuilder sqlBuilder, boolean autoCheck) {
        return execute(new FormDataUpdateBySqlCommand(formCode, version, autoCheck, sqlBuilder, false));
    }

    @Override
    public Long updateFormDataIgnoreNullBySqlBuilderAndFormCode(String formCode, Integer version, SqlBuilder sqlBuilder, boolean autoCheck) {
        return execute(new FormDataUpdateBySqlCommand(formCode, version, autoCheck, sqlBuilder, true));
    }


    @Override
    public List<FormData> selectFormData(String formDefinitionId, SqlBuilder sqlBuilder) {
        return execute(new FormDataSelectCommand(formDefinitionId, false, sqlBuilder, true));
    }

    @Override
    public List<FormData> selectSelfColumnFormData(String formDefinitionId, SqlBuilder sqlBuilder) {
        return execute(new FormDataSelectCommand(formDefinitionId, false, sqlBuilder, false));
    }

    @Override
    public List<FormData> selectFormDataByFormCode(String formCode, Integer version, SqlBuilder sqlBuilder) {
        return execute(new FormDataSelectCommand(formCode, version, false, sqlBuilder, true));
    }

    @Override
    public List<FormData> selectSelfColumnFormDataByFormCode(String formCode, Integer version, SqlBuilder sqlBuilder) {
        return execute(new FormDataSelectCommand(formCode, version, false, sqlBuilder, false));
    }

    @Override
    public FormData selectOneFormData(String formDefinitionId, String formDataId) {
        return execute(new FormDataSelectOneCommand(formDefinitionId, false, formDataId));
    }

    @Override
    public FormData selectOneFormDataByFormCode(String formCode, Integer version, String formDataId) {
        return execute(new FormDataSelectOneCommand(formCode, version, false, formDataId));
    }

    @Override
    public Page<FormData> selectPageFormData(String formDefinitionId, SqlBuilder sqlBuilder, int pageIndex, int pageSize) {
        return execute(new FormDataSelectPageCommand(formDefinitionId, false, sqlBuilder, pageIndex, pageSize));
    }

    @Override
    public Page<FormData> selectPageFormDataByFormCode(String formCode, Integer version, SqlBuilder sqlBuilder, int pageIndex, int pageSize) {
        return execute(new FormDataSelectPageCommand(formCode, version, false, sqlBuilder, pageIndex, pageSize));
    }

    @Override
    public Long removeFormData(String formDefinitionId, SqlBuilder sqlBuilder) {
        return execute(new FormDataRemoveBySqlCommand(formDefinitionId, false, sqlBuilder));
    }

    @Override
    public Long removeFormDataByFormCode(String formCode, Integer version, SqlBuilder sqlBuilder) {
        return execute(new FormDataRemoveBySqlCommand(formCode, version, false, sqlBuilder));
    }

    @Override
    public Long countId(String formDefinitionId, SqlBuilder sqlBuilder) {
        return execute(new FormDataCountIdBySqlCommand(formDefinitionId, false, sqlBuilder));
    }

    @Override
    public Long countId(String formCode, Integer version, SqlBuilder sqlBuilder) {
        return execute(new FormDataCountIdBySqlCommand(formCode, version, false, sqlBuilder));
    }

    @Override
    public <T> List<T> countSelf(String formDefinitionId, SqlBuilder<T> sqlBuilder) {
        return (List<T>) execute(new FormDataCountSelfBySqlCommand(formDefinitionId, false, sqlBuilder));
    }

    @Override
    public <T> List<T> countSelf(String formCode, Integer version, SqlBuilder<T> sqlBuilder) {
        return (List<T>) execute(new FormDataCountSelfBySqlCommand(formCode, version, false, sqlBuilder));
    }
}
