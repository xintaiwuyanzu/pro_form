package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.core.service.SqlBuilder;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.page.Page;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.jdbc.Relation;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.List;

public class FormDataSelectCommand extends AbstractFormDefinitionIdCommand implements Command<List<FormData>> {

    private SqlBuilder sqlBuilder;

    public FormDataSelectCommand(String formDefinitionId, SqlBuilder sqlBuilder) {
        super(formDefinitionId);
        this.sqlBuilder = sqlBuilder;
    }

    public FormDataSelectCommand(Integer version, String formDefinitionId, SqlBuilder sqlBuilder) {
        super(formDefinitionId, version);
        this.sqlBuilder = sqlBuilder;
    }

    /**
     * 查询这个表单下的所有实例数据
     *
     * @param context
     * @return
     */
    @Override
    public List<FormData> execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(getFormDefinitionId()), "表单id不能为空");
        Form form = context.getFormDefinitionService().selectFormDefinitionById(getFormDefinitionId());
        Assert.notNull(form, "系统未发现该表单定义");
        //判断表是否存在
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        FormNameGenerator formNameGenerator = context.getApplicationContext().getBean(FormNameGenerator.class);
        Assert.isTrue(dataBaseService.tableExist(formNameGenerator.genTableName(form), Constants.MODULE_NAME), "未发现数据实例表");
        //先查出来表结构定义对象
        Relation relation = dataBaseService.getTableInfo(formNameGenerator.genTableName(form), Constants.MODULE_NAME);
        //拼写查询条件
        SqlQuery sqlQueryObj = SqlQuery.from(relation).setReturnClass(FormData.class);
        if (sqlBuilder != null) {
            sqlBuilder.buildSql(sqlQueryObj, relation);
        }
        //执行查询语句
        return context.getMapper().selectByQuery(sqlQueryObj);
    }

    public SqlBuilder getSqlBuilder() {
        return sqlBuilder;
    }
}
