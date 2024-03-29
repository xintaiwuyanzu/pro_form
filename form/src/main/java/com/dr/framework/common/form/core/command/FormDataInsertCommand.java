package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.model.FormRelationWrapper;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * 插入一条表单数据
 *
 * @author dr
 */
public class FormDataInsertCommand extends AbstractFormDataCommand<FormData> implements Command<FormData> {

    private final FormData formData;

    public FormDataInsertCommand(FormData formData, boolean autoCheck) {
        super(null, autoCheck);
        Assert.isTrue(formData != null, "表单数据不能为空!");
        setFormDefinitionId(formData.getFormDefinitionId());
        setFormCode(formData.getFormDefinitionCode());
        setVersion(formData.getVersion());
        this.formData = formData;
    }

    @Override
    protected FormData doModifyData(CommandContext context, FormRelationWrapper wrapper) {
        if (StringUtils.isEmpty(formData.getId())) {
            formData.put(FormData.FORM_DATA_ID_KEY, UUID.randomUUID().toString());
        }
        FormData newData = mapFormData(wrapper, formData);
        //TODO  字段映射还有问题
        SqlQuery sqlQuery = SqlQuery.from(wrapper.getRelation());
        sqlQuery.putAll(newData);
        context.getMapper().insertByQuery(sqlQuery);
        return newData;
    }

    public FormData getFormData() {
        return formData;
    }

}
