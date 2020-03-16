package com.dr.framework.common.form.command.method;

import com.dr.framework.common.form.command.entity.WorkForm;
import com.dr.framework.common.form.command.entity.WorkFormInfo;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.model.Form;
import com.dr.framework.common.form.util.Constans;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class WorkFormSelectCommand implements Command<Form> {
    private String formId;
    private String formDataId;

    public WorkFormSelectCommand(String formId, String formDataId) {
        this.formId = formId;
        this.formDataId = formDataId;
    }


    /**
     * 查询单个表单的实例数据
     *
     * @param context
     * @return
     */
    @Override
    public Form execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(formId), "表单id不能为空");
        Assert.isTrue(StringUtils.isNotEmpty(formDataId), "表单实例id不能为空");
        SqlQuery<WorkForm> sqlQuery = SqlQuery.from(WorkForm.class).equal(WorkFormInfo.ID, formId);
        WorkForm workForm = context.getMapper().selectOneByQuery(sqlQuery);
        Assert.notNull(workForm, "系统未发现该表单");
        //判断表是否存在
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        Assert.isTrue(dataBaseService.tableExist(workForm.getFormTable(), Constans.MODULE_NAME), "未发现数据实例表");
        //todo 如果存在则查询表单实例数据
        return null;
    }

}
