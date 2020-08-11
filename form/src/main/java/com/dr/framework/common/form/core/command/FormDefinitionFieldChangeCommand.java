package com.dr.framework.common.form.core.command;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.engine.CommandContext;
import org.springframework.util.Assert;

import java.util.Set;
import java.util.UUID;

/**
 * 修改表单定义
 *
 * @author dr
 */
public class FormDefinitionFieldChangeCommand extends FormDefinitionFieldAddCommand {

    public FormDefinitionFieldChangeCommand(String formDefinitionId, boolean updateTable, boolean copyData, FieldModel fieldModel) {
        super(formDefinitionId, updateTable, copyData, fieldModel);
    }

    public FormDefinitionFieldChangeCommand(String formCode, Integer version, boolean updateTable, boolean copyData, FieldModel fieldModel) {
        super(formCode, version, updateTable, copyData, fieldModel);
    }

    @Override
    public FormField execute(CommandContext context) {
        //获取老的表单定义
        FormDefinition old = getFormDefinition(context);
        Assert.isTrue(getField() != null, "更新的字段不能为空");
        FormField oldField = old.getFieldByCode(getField().getFieldCode());

        Assert.isTrue(oldField != null, "字段编码不能修改！");
        //判断表结构是否有变化
        if (isFieldChange(oldField, getField())) {
            //有变化则创建新版本
            return super.execute(context);
        } else {
            if (getField().getFieldAlias() != null && !getField().getFieldAlias().isEmpty()) {
                //TODO 判断别名
                Set<String> fieldNames = old.getFieldNames();
                Set<String> fieldAlias = old.getFieldAlias();
                getField().getFieldAlias()
                        .forEach(s -> {

                        });
            }

            CommonMapper mapper = context.getMapper();
            //复制新属性
            oldField.setLabel(getField().getLabel());
            oldField.setDescription(getField().getDescription());
            oldField.setRemarks(getField().getRemarks());

            oldField.setFieldAliasStr(String.join(",", getField().getFieldAlias()));
            oldField.setLabel(getField().getLabel());

            mapper.updateById(oldField);
        }
        return oldField;
    }

    /**
     * 过滤掉之前老字段定义，使用新字段定义
     *
     * @param context
     * @param old
     * @return
     */
    @Override
    protected FormDefinition copyFormDefinition(CommandContext context, FormDefinition old) {
        FormDefinition formDefinition = super.copyFormDefinition(context, old);
        FormField oldField = formDefinition.getFieldByCode(getField().getFieldCode());
        formDefinition.getFields()
                .removeIf(f -> f.getId().equalsIgnoreCase(oldField.getId()));
        return formDefinition;
    }

    @Override
    protected FormField newField(FieldModel fieldModel) {
        FormField newField = super.newField(fieldModel);
        newField.setId(UUID.randomUUID().toString());
        return newField;
    }

    private boolean isFieldChange(FormField old, FieldModel fieldModel) {
        if (old.getFieldType().equals(fieldModel.getFieldType())) {
            if (old.getFieldLength() == fieldModel.getFieldLength()) {
                return old.getFieldScale() != fieldModel.getFieldScale();
            }
        }
        return true;
    }

}
