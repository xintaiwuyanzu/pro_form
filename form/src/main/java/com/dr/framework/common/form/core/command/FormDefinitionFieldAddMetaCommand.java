package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import org.springframework.util.Assert;

import java.util.Map;

import static com.dr.framework.common.form.core.service.FormDefinitionService.FORM_DEFINITION_FIELD_META_TYPE;

/**
 * 表单添加元数据
 *
 * @author dr
 */
public class FormDefinitionFieldAddMetaCommand extends FormDefinitionAddMetaCommand {
    private String formCode;

    public FormDefinitionFieldAddMetaCommand(String formDefinitionId, String formCode, Map<String, String> metas) {
        super(formDefinitionId, metas);
        this.formCode = formCode;
    }

    public FormDefinitionFieldAddMetaCommand(String formCode, Integer version, String formCode1, Map<String, String> metas) {
        super(formCode, version, metas);
        this.formCode = formCode1;
    }

    @Override
    public String getRefId(CommandContext context) {
        FormDefinition formModel = getFormDefinition(context);
        Assert.isTrue(formModel != null, "未查询到指定的表单！");
        FieldModel fieldModel = formModel.getFieldByCode(formCode);
        Assert.isTrue(fieldModel != null, "未查询到指定的字段！");
        return fieldModel.getId();
    }

    @Override
    public String getRefType(CommandContext context) {
        return FORM_DEFINITION_FIELD_META_TYPE;
    }
}
