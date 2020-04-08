package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.jdbc.Relation;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class FormDataSelectOneCommand extends AbstractFormDefinitionIdCommand<FormData> {

    private String formDataId;

    public FormDataSelectOneCommand(String formDefinitionId, String formDataId) {
        super(formDefinitionId);
        this.formDataId = formDataId;
    }

    public FormDataSelectOneCommand(String version, String formDefinitionId, String formDataId) {
        super(version, formDefinitionId);
        this.formDataId = formDataId;
    }

    /**
     * 查询单个表单的实例数据
     *
     * @param context
     * @return
     */
    @Override
    public FormData execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(getFormDefinitionId()), "表单id不能为空");
        Assert.isTrue(StringUtils.isNotEmpty(formDataId), "表单实例id不能为空");
        Form form = context.getFormDefinitionService().selectOneFormDefinition(getFormDefinitionId());
        Assert.notNull(form, "系统未发现该表单");
        //判断表是否存在
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        Assert.isTrue(dataBaseService.tableExist(form.getFormTable(), Constants.MODULE_NAME), "未发现数据实例表");
        //先查出来表结构定义对象
        Relation relation = dataBaseService.getTableInfo(form.getFormTable(), Constants.MODULE_NAME);
        //拼写查询条件
        SqlQuery sqlQueryObj = SqlQuery.from(relation).equal(relation.getColumn("ID"), formDataId);
        //执行查询语句
        return (FormData) context.getMapper().selectOneByQuery(sqlQueryObj);
    }

    public String getFormDataId() {
        return formDataId;
    }
}
