package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.jdbc.Relation;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class FormDataRemoveCommand extends AbstractFormDefinitionIdCommand<Long> {

    private String formDataId;

    public FormDataRemoveCommand(String formDefinitionId, String formDataId) {
        super(formDefinitionId);
        this.formDataId = formDataId;
    }

    public FormDataRemoveCommand(String version, String formDefinitionId, String formDataId) {
        super(version, formDefinitionId);
        this.formDataId = formDataId;
    }

    @Override
    public Long execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(getFormDefinitionId()), "表单id不能为空");
        Assert.isTrue(StringUtils.isNotEmpty(formDataId), "表单实例id不能为空");
        Form form = context.getFormDefinitionService().selectOneFormDefinition(getFormDefinitionId());
        Assert.notNull(form, "系统未发现该表单");
        //判断表是否存在
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        FormNameGenerator formNameGenerator = context.getApplicationContext().getBean(FormNameGenerator.class);
        Assert.isTrue(dataBaseService.tableExist(formNameGenerator.genTableName(form), Constants.MODULE_NAME), "未发现数据实例表");
        //先查出来表结构定义对象
        Relation relation = dataBaseService.getTableInfo(formNameGenerator.genTableName(form), Constants.MODULE_NAME);
        //拼写查询条件
        SqlQuery sqlQueryObj = SqlQuery.from(relation).equal(relation.getColumn("ID"), formDataId);
        //执行查询语句
        return context.getMapper().deleteByQuery(sqlQueryObj);

    }

    public String getFormDataId() {
        return formDataId;
    }
}
