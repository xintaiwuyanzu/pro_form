package com.dr.framework.common.form.init.service.impl;

import com.dr.framework.common.form.engine.CommandExecutor;
import com.dr.framework.common.form.init.command.*;
import com.dr.framework.common.form.init.model.FieldDefault;
import com.dr.framework.common.form.init.model.FormDefault;
import com.dr.framework.common.form.init.service.FormDefaultValueService;
import com.dr.framework.common.page.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

public class FormDefaultValueServiceImpl implements FormDefaultValueService {

    @Autowired
    protected CommandExecutor executor;

    /**
     * 添加表单默认值
     *
     * @param formDefault
     * @param fieldDefaultList
     * @return
     */
    @Override
    public FormDefault addFormDefaultValue(FormDefault formDefault, Collection<FieldDefault> fieldDefaultList) {
        return executor.execute(new FormDefaultValueInsertCommand(formDefault, fieldDefaultList));
    }

    /**
     * 更新表单默认值
     *
     * @param formDefault
     * @param fieldDefaultList
     * @return
     */
    @Override
    public FormDefault updateFormDefaultValue(FormDefault formDefault, Collection<FieldDefault> fieldDefaultList) {
        return addFormDefaultValue(formDefault, fieldDefaultList);
    }

    /**
     * 查询单个表单默认数据
     *
     * @param formDefinitionId
     * @param formDefaultValueId
     * @return
     */
    @Override
    public FormDefault SelectOneFormDefaultValue(String formDefinitionId, String formDefaultValueId) {
        return executor.execute(new FormDefaultValueSelectOneCommand(formDefinitionId, formDefaultValueId));
    }

    /**
     * 查询表单定义下的所有默认方案
     *
     * @param formDefinitionId
     * @return
     */
    @Override
    public List<FormDefault> SelectFormDefaultValue(String formDefinitionId) {
        return executor.execute(new FormDefaultSelectCommand(formDefinitionId));
    }

    /**
     * 查询表单定义下的默认方案列表
     *
     * @param formDefinitionId
     * @return
     */
    @Override
    public Page<FormDefault> SelectPageFormDefaultValue(String formDefinitionId, int pageIndex, int pageSize) {
        return executor.execute(new FormDefaultSelectPageCommand(formDefinitionId, pageIndex, pageSize));
    }

    /**
     * 删除一套表单默认值
     *
     * @param formDefaultValueId
     * @return
     */
    @Override
    public long RemoveFormDefaultValue(String formDefaultValueId) {
        return executor.execute(new FormDefaultValueRemoveCommand(formDefaultValueId));
    }

}
