package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.FormFieldInfo;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * 根据字段编码获取单个字段信息
 *
 * @author dr
 */
public class FieldDataSelectOneCommand extends AbstractVersionCommand<Field> {

    private String formDefinitionId;
    private String fieldCode;
    private String fieldName;

    public FieldDataSelectOneCommand(String formDefinitionId, String fieldCode) {
        this(formDefinitionId, fieldCode, null);
    }

    public FieldDataSelectOneCommand(String formDefinitionId, String fieldCode, String fieldName) {
        super();
        this.formDefinitionId = formDefinitionId;
        this.fieldCode = fieldCode;
        this.fieldName = fieldName;
    }

    public FieldDataSelectOneCommand(String version, String formDefinitionId, String fieldCode, String fieldName) {
        super(version);
        this.formDefinitionId = formDefinitionId;
        this.fieldCode = fieldCode;
        this.fieldName = fieldName;
    }


    @Override
    public Field execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(formDefinitionId), "表单定义Id不能为空");
        Assert.isTrue(StringUtils.isNotEmpty(fieldCode), "表单定义Id不能为空");
        SqlQuery<FormField> sqlQuery = SqlQuery.from(FormField.class);
        if (StringUtils.isNotEmpty(formDefinitionId)) {
            sqlQuery.equal(FormFieldInfo.FORMDEFINITIONID, formDefinitionId);
        }
        if (StringUtils.isNotEmpty(fieldCode)) {
            sqlQuery.equal(FormFieldInfo.FIELDCODE, fieldCode);
        }
        if (StringUtils.isNotEmpty(fieldName)) {
            sqlQuery.equal(FormFieldInfo.FIELDNAME, fieldName);
        }
        sqlQuery.equal(FormFieldInfo.VERSION, getVersion());

        FormField formField = context.getMapper().selectOneByQuery(sqlQuery);
        return formField;
    }

}
