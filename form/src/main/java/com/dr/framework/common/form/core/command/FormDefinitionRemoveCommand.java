package com.dr.framework.common.form.core.command;


import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormDefinitionInfo;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.FormFieldInfo;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 删除表单定义数据
 *
 * @author dr
 */
public class FormDefinitionRemoveCommand extends AbstractFormDefinitionIdCommand<Long> {

    private boolean retain;

    public FormDefinitionRemoveCommand(String formDefinitionId, boolean retain) {
        super(formDefinitionId);
        this.retain = retain;
    }

    public FormDefinitionRemoveCommand(String version, String formDefinitionId, boolean retain) {
        super(version, formDefinitionId);
        this.retain = retain;
    }

    /**
     * 删除表单数据，表单定义
     *
     * @param context
     * @return long
     */
    @Override
    public Long execute(CommandContext context) {
        Assert.isTrue(!StringUtils.isEmpty(getFormDefinitionId()), "请选择需要删除的表单");
        //根据表单Id查询表单主表数据
        FormDefinition formDefinition = context.getMapper().selectById(FormDefinition.class, getFormDefinitionId());
        Assert.notNull(formDefinition, "你选择的表单数据不存在");
        if (!StringUtils.isEmpty(formDefinition.getFormTable())) {
            //是否直接删除表 true: 删  false: 不删
            if (retain) {
                //刪除生成的表
                removeTable(context, formDefinition.getFormTable());
            }
        }
        //根据表单数据删除表单数据中的所有字段数据
        context.getMapper().deleteByQuery(SqlQuery.from(FormField.class).equal(FormFieldInfo.FORMDEFINITIONID, getFormDefinitionId()));
        //最后删除表单定义表中的这条数据
        return context.getMapper().deleteByQuery(SqlQuery.from(FormDefinition.class).equal(FormDefinitionInfo.ID, getFormDefinitionId()));
    }

    protected void removeTable(CommandContext context, String tableName) {
        //是否生成表
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        dataBaseService.dropTable(tableName, Constants.MODULE_NAME);
    }

}
