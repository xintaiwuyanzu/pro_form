package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.Constans;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.jdbc.Relation;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class WorkFormDataRemoveCommand implements Command<Long> {

    private String formId;
    private String formDataId;

    public WorkFormDataRemoveCommand(String formId, String formDataId) {
        this.formId = formId;
        this.formDataId = formDataId;
    }

    @Override
    public Long execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(formId), "表单id不能为空");
        Assert.isTrue(StringUtils.isNotEmpty(formDataId), "表单实例id不能为空");
        Form form = new WorkFormSelectOneCommand(formId, null).execute(context);
        Assert.notNull(form, "系统未发现该表单");
        //判断表是否存在
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        Assert.isTrue(dataBaseService.tableExist(form.getFormTable(), Constans.MODULE_NAME), "未发现数据实例表");
        //先查出来表结构定义对象
        Relation relation = dataBaseService.getTableInfo(form.getFormTable(), Constans.MODULE_NAME);
        //拼写查询条件
        SqlQuery sqlQueryObj = SqlQuery.from(relation).equal(relation.getColumn("ID"), formDataId);
        //执行查询语句
        return context.getMapper().deleteByQuery(sqlQueryObj);

    }

}
