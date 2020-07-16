package com.dr.framework.common.form.core.command;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.engine.CommandContext;
import org.springframework.util.Assert;

import java.util.Set;

/**
 * 修改表单定义
 *
 * @author dr
 */
public class FormDefinitionFieldChangeCommand extends FormDefinitionFieldAddCommand {

    public FormDefinitionFieldChangeCommand(String formDefinitionId, boolean updateTable, boolean copyData, Field field) {
        super(formDefinitionId, updateTable, copyData, field);
    }

    public FormDefinitionFieldChangeCommand(String formCode, Integer version, boolean updateTable, boolean copyData, Field field) {
        super(formCode, version, updateTable, copyData, field);
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
            oldField.setDataObjectId(getField().getDataObjectId());

            oldField.setFieldAlias(String.join(",", getField().getFieldAlias()));
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
        FormField oldField = old.getFieldByCode(getField().getFieldCode());
        formDefinition.getFormFieldList()
                .removeIf(f -> f.getId().equalsIgnoreCase(oldField.getId()));
        return formDefinition;
    }

    private boolean isFieldChange(FormField old, Field field) {
        if (old.getFieldType().equals(field.getFieldType())) {
            if (old.getFieldLength() == field.getFieldLength()) {
                if (old.getFieldScale() == field.getFieldScale()) {
                    return false;
                }
            }
        }
        return true;
    }

}
