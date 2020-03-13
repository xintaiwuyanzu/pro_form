package com.dr.framework.common.form.command.service;

import com.dr.framework.common.form.command.entity.FormField;
import com.dr.framework.common.form.command.entity.WorkForm;
import com.dr.framework.common.form.command.method.WorkFormInsertCommand;
import com.dr.framework.common.form.engine.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WorkFormDataService {
    @Autowired
    CommandExecutor executor;

    /**
     * 保存表单数据
     *
     * @param workForm
     * @param formFieldList
     * @return
     */
    public void insert(WorkForm workForm, List<FormField> formFieldList,boolean flage) {
        executor.execute(new WorkFormInsertCommand(workForm,formFieldList,flage));
    }

}
