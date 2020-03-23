package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.FormFieldInfo;
import com.dr.framework.common.form.core.entity.WorkForm;
import com.dr.framework.common.form.core.entity.WorkFormInfo;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.Constans;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class WorkFormRemoveCommand implements Command<Long> {

    private String formId;

    private boolean retain;

    public WorkFormRemoveCommand(String formId, boolean retain) {
        this.formId = formId;
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
        Assert.isTrue(!StringUtils.isEmpty(formId), "请选择需要删除的表单");
        //根据表单Id查询表单主表数据
        WorkForm workForm = context.getMapper().selectById(WorkForm.class, formId);
        Assert.notNull(workForm, "你选择的表单数据不存在");
        if (!StringUtils.isEmpty(workForm.getFormTable())) {
            //是否直接删除表 true: 删  false: 不删
            if (retain) {
                //刪除生成的表
                removeTable(context, workForm.getFormTable());
            }
        }
        //根据表单数据删除表单数据中的所有字段数据
        context.getMapper().deleteByQuery(SqlQuery.from(FormField.class).equal(FormFieldInfo.FORMID, formId));
        //最后删除表单定义表中的这条数据
        return context.getMapper().deleteByQuery(SqlQuery.from(WorkForm.class).equal(WorkFormInfo.ID, formId));
    }

    protected void removeTable(CommandContext context, String tableName) {
        //是否生成表
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        dataBaseService.dropTable(tableName, Constans.MODULE_NAME);
    }

}
