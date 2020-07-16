package com.dr.framework.common.form.init.service.impl;

import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.init.command.FormDataDefaultValueCommand;
import com.dr.framework.common.form.init.service.InitValueService;
import org.springframework.beans.factory.annotation.Autowired;

public class InitValueServiceImpl implements InitValueService {

    @Autowired
    CommandExecutor executor;

    /**
     * 获取配置的表单默认值
     *
     * @param formDefinitionId
     * @param version
     * @return
     */
    @Override
    public FormData getFormLinkData(String formDefinitionId, Integer version, String formDefaultValueId) {
        return executor.execute(new FormDataDefaultValueCommand(formDefinitionId, version, formDefaultValueId));
    }


}
