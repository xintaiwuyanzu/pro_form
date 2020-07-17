package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.page.Page;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.jdbc.Relation;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class FormDataSelectPageCommand extends AbstractFormDefinitionIdCommand implements Command<Page> {


    private FormData formData;
    private int pageIndex;
    private int pageSize;

    public FormDataSelectPageCommand(FormData formData, int pageIndex, int pageSize) {
        super(null, null);
        this.formData = formData;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public FormDataSelectPageCommand(String formDefinitionId, FormData formData, int pageIndex, int pageSize) {
        super(formDefinitionId);
        this.formData = formData;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public FormDataSelectPageCommand(String formDefinitionId, Integer version, FormData formData, int pageIndex, int pageSize) {
        super(formDefinitionId, version);
        this.formData = formData;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    /**
     * 根据表单定义id， 查询表单实例数据
     *
     * @param context
     * @return
     */
    @Override
    public Page execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(formData.get("formDefinitionId") + ""), "表单id不能为空");
        Form form = context.getFormDefinitionService().selectFormDefinitionById(formData.get("formDefinitionId") + "");
        Assert.notNull(form, "系统未发现该表单定义");
        //判断表是否存在
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        FormNameGenerator formNameGenerator = context.getApplicationContext().getBean(FormNameGenerator.class);
        Assert.isTrue(dataBaseService.tableExist(formNameGenerator.genTableName(form), Constants.MODULE_NAME), "未发现数据实例表");
        //先查出来表结构定义对象
        Relation relation = dataBaseService.getTableInfo(formNameGenerator.genTableName(form), Constants.MODULE_NAME);
        //拼写查询条件
        SqlQuery sqlQueryObj = SqlQuery.from(relation).equal(relation.getColumn("FORMDEFINITIONID"), formData.get("formDefinitionId") + "");
        //执行查询语句
        return context.getMapper().selectPageByQuery(sqlQueryObj, pageIndex * pageSize, (pageIndex + 1) * pageSize);
    }

}
