package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.jdbc.Relation;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

public class FormDataSelectCommand implements Command<List> {

    @Autowired
    FormDefinitionService formDefinitionService;

    private String formId;

    public FormDataSelectCommand(String formId) {
        this.formId = formId;
    }

    /**
     * 查询这个表单下的所有实例数据
     *
     * @param context
     * @return
     */
    @Override
    public List<FormData> execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(formId), "表单id不能为空");
        Form form = formDefinitionService.selectOneFormDefinition(formId);
        Assert.notNull(form, "系统未发现该表单定义");
        //判断表是否存在
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        Assert.isTrue(dataBaseService.tableExist(form.getFormTable(), Constants.MODULE_NAME), "未发现数据实例表");
        //先查出来表结构定义对象
        Relation relation = dataBaseService.getTableInfo(form.getFormTable(), Constants.MODULE_NAME);
        //拼写查询条件
        SqlQuery sqlQueryObj = SqlQuery.from(relation).equal(relation.getColumn("formId"), formId);
        //执行查询语句
        return context.getMapper().selectByQuery(sqlQueryObj);
    }

}
