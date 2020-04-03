package com.dr.framework.common.form.init.service;

import com.dr.framework.common.form.core.model.FormData;

public interface InitDataService {

    /**
     * 获取指定环节的表单默认数据
     *
     * @param formDefinitionId
     * @param version
     * @param formDefaultValueId
     * @return
     */
    FormData getFormLinkData(String formDefinitionId, String version, String formDefaultValueId);

}
