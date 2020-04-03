package com.dr.framework.common.form.init.command;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.init.entity.FieldDefaultValue;
import com.dr.framework.common.form.init.entity.FieldDefaultValueInfo;
import com.dr.framework.common.form.init.entity.FormDefaultValue;
import com.dr.framework.common.form.init.entity.FormDefaultValueInfo;
import com.dr.framework.common.form.init.model.FormDefault;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.List;

public class FormDefaultValueSelectOneCommand implements Command<FormDefault> {
    private String formDefinitionId;
    private String formDefaultValueId;
    private String linkCode;

    public FormDefaultValueSelectOneCommand(String formDefinitionId, String formDefaultValueId) {
        this(formDefinitionId, formDefaultValueId, null);
    }

    public FormDefaultValueSelectOneCommand(String formDefinitionId, String formDefaultValueId, String linkCode) {
        this.formDefaultValueId = formDefaultValueId;
        this.formDefinitionId = formDefinitionId;
        this.linkCode = linkCode;
    }

    /**
     * 查询默认值设定对象
     *
     * @param context
     * @return
     */
    @Override
    public FormDefault execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(formDefaultValueId) || StringUtils.isNotEmpty(linkCode), "默认值主键和编码不能全为空");
        SqlQuery<FormDefaultValue> sqlQuery = SqlQuery.from(FormDefaultValue.class);
        if (StringUtils.isNotEmpty(formDefinitionId)) {
            sqlQuery.equal(FormDefaultValueInfo.FORMDEFINITIONID, formDefinitionId);
        }
        if (StringUtils.isNotEmpty(formDefaultValueId)) {
            sqlQuery.equal(FormDefaultValueInfo.ID, formDefaultValueId);
        }
        if (StringUtils.isNotEmpty(linkCode)) {
            sqlQuery.equal(FormDefaultValueInfo.LINKCODE, linkCode);
        }
        FormDefaultValue formDefaultValue = context.getMapper().selectOneByQuery(sqlQuery);
        Assert.notNull(formDefaultValue, "查询的默认值方案不存在");
        List<FieldDefaultValue> fieldDefaultValueList = context.getMapper().selectByQuery(SqlQuery.from(FieldDefaultValue.class).equal(FieldDefaultValueInfo.FORMDEFAULTVALUEID, formDefaultValue.getId()));
        formDefaultValue.setFieldDefaultList(fieldDefaultValueList);
        return formDefaultValue;
    }

}
