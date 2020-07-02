package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.jdbc.Relation;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.Collection;

public class FormDataInsertCommand implements Command<FormData> {

    private FormData formData;

    public FormDataInsertCommand(FormData formData) {
        this.formData = formData;
    }

    @Override
    public FormData execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(formData.get("formDefinitionId") + ""), "表单定义Id不能为空！");
        FormDefinition formDefinition = (FormDefinition) context.getFormDefinitionService().selectOneFormDefinition(formData.get("formDefinitionId") + "");
        Assert.notNull(formDefinition, "系统未发现该表单");
        //判断表是否存在
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        FormNameGenerator formNameGenerator = context.getApplicationContext().getBean(FormNameGenerator.class);
        Assert.isTrue(dataBaseService.tableExist(formNameGenerator.genTableName(formDefinition), Constants.MODULE_NAME), "未发现数据实例表");
        //获取表单字段
        Collection<FormField> list = formDefinition.getFormFieldList();
        //先查出来表结构定义对象
        Relation relation = dataBaseService.getTableInfo(formNameGenerator.genTableName(formDefinition), Constants.MODULE_NAME);
        if (list.size() > 0) {
            //拼写插入的数据
            SqlQuery sqlQuery = SqlQuery.from(relation);
            for (FormField formField : list) {
                sqlQuery.set(relation.getColumn(formField.getFieldCode()), formData.get(formField.getFieldCode()));
            }
            context.getMapper().insertByQuery(sqlQuery);
        }
        return formData;
    }

    public FormData getFormData() {
        return formData;
    }

}
