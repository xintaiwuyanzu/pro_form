package com.dr.framework.common.form.init.service;

import com.dr.framework.common.form.core.model.FormData;

public interface InitDataService {
    /**
     * 获取指定版本表单定义的默认值对象
     * 用来传给前台，做默认显示
     *
     * @param formDefinitionId
     * @param version
     * @return
     */
    FormData getFormDefaultData(String formDefinitionId, String version);

}
