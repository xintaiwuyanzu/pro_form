package com.dr.framework.common.form.init.service;

import com.dr.framework.common.form.init.model.FieldDefault;
import com.dr.framework.common.form.init.model.FormDefault;
import com.dr.framework.common.page.Page;

import java.util.Collection;
import java.util.List;

public interface FormDefaultValueService {

    /**
     * 添加表单默认值
     *
     * @param formDefault
     * @param fieldDefaultList
     * @return
     */
    FormDefault addFormDefaultValue(FormDefault formDefault, Collection<FieldDefault> fieldDefaultList);


    /**
     * 更新表单默认值
     *
     * @param formDefault
     * @param fieldDefaultList
     * @return
     */
    FormDefault updateFormDefaultValue(FormDefault formDefault, Collection<FieldDefault> fieldDefaultList);

    /**
     * 查询指定的一套表单默认值
     *
     * @param formDefinitionId
     * @param formDefaultValueId
     * @return
     */
    FormDefault SelectOneFormDefaultValue(String formDefinitionId, String formDefaultValueId);

    /**
     * 查询表单定义下的所有默认方案
     *
     * @param formDefinitionId
     * @return
     */
    List<FormDefault> SelectFormDefaultValue(String formDefinitionId);

    /**
     * 查询表单定义下的默认方案列表
     *
     * @param formDefinitionId
     * @return
     */
    Page<FormDefault> SelectPageFormDefaultValue(String formDefinitionId, int pageIndex, int pageSize);

    /**
     * 删除一套表单默认值
     *
     * @param formDefaultValueId
     * @return
     */
    long RemoveFormDefaultValue(String formDefaultValueId);

}
