package com.dr.framework.common.form.core.service.impl;

import com.dr.framework.common.form.core.command.*;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.service.FormDataService;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.page.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FormDataServiceImpl implements FormDataService {

    @Autowired
    protected CommandExecutor executor;

    /**
     * 插入表单实例数据
     *
     * @param formData
     * @return
     */
    @Override
    public FormData addFormData(FormData formData) {
        return executor.execute(new FormDataInsertCommand(formData));
    }

    /**
     * 更新表单实例数据
     *
     * @param formData
     * @return
     */
    @Override
    public FormData updateFormData(FormData formData) {
        return addFormData(formData);
    }

    /**
     * 查询表单数据
     *
     * @param formId
     * @return list
     */
    @Override
    public List<FormData> selectFormData(String formId) {
        return executor.execute(new FormDataSelectCommand(formId));
    }

    /**
     * 查询具体表单实例数据
     *
     * @param formDataId
     * @return
     */
    @Override
    public FormData selectOneFormData(String formId, String formDataId) {
        return executor.execute(new FormDataSelectOneCommand(formId, formDataId));
    }

    /**
     * 分页查询表单实例数据
     *
     * @param formData
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Page<FormData> selectPageFormDefinition(FormData formData, int pageIndex, int pageSize) {
        return executor.execute(new FormDataSelectPageCommand(formData, pageIndex, pageSize));
    }

    /**
     * 删除表单实例数据
     *
     * @param formId
     * @param formDataId
     * @return
     */
    @Override
    public Long removeFormData(String formId, String formDataId) {
        return executor.execute(new FormDataRemoveCommand(formId, formDataId));
    }
}
