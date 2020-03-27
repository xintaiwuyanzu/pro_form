package com.dr.framework.common.form.core.service.impl;

import com.dr.framework.common.form.core.command.WorkFormDataSelectCommand;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.service.FormDataService;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.engine.CommandExecutor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormDataServiceImpl implements FormDataService {
    @Autowired
    protected FormDefinitionService FormDefinitionService;
    @Autowired
    protected CommandExecutor executor;


    @Override
    public Map<String, Object> addFormData(FormData formData) {
        Assert.isTrue(StringUtils.isNotEmpty(formData.get("formDefinitionId").toString()), "表单定义Id不能为空");
        Form form = FormDefinitionService.selectOneFormDefinition(formData.get("formDefinitionId") + "", formData.get("formDataId") + "");
        //todo 将formData中的数据保存在form查出来表结构里面

        return null;
    }

    @Override
    public Map<String, Object> updateFormData(FormData formData) {
        return addFormData(formData);
    }

    @Override
    public List<Map<String, Object>> selectFormData(String formDataId, String formId) {
        Form form = FormDefinitionService.selectOneFormDefinition(formId, formDataId);
        String tableName = form.getFormTable();
        //TODO 查询这个表单的所有数据 返回List

        return null;
    }

    /**
     * 查询具体表单实例数据
     *
     * @param formDataId
     * @return
     */
    @Override
    public Map<String, Object> selectOneFormData(String formDataId, String formId) {
        Map<String, Object> map = new HashMap<>();
        Object object = executor.execute(new WorkFormDataSelectCommand(formId, formDataId));
        map.put("FormData", object);
        return map;
    }

    @Override
    public Long removeFormData(String formDataId, String formId) {
        Form form = FormDefinitionService.selectOneFormDefinition(formId, formDataId);
        String tableName = form.getFormTable();
        //TODO 删除这个表单的 这条实例数据

        return null;
    }
}
