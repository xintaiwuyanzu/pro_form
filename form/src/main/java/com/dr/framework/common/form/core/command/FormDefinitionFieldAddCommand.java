package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import org.springframework.util.Assert;

/**
 * 添加字段定义
 *
 * @author dr
 */
public class FormDefinitionFieldAddCommand extends AbstractFormDefinitionChangeCommand implements Command<FormField> {
    /**
     * 字段定义
     */
    private Field field;

    public FormDefinitionFieldAddCommand(String formDefinitionId, boolean updateTable, boolean copyData, Field field) {
        super(formDefinitionId, updateTable, copyData);
        this.field = field;
    }

    public FormDefinitionFieldAddCommand(String formCode, Integer version, boolean updateTable, boolean copyData, Field field) {
        super(formCode, version, updateTable, copyData);
        this.field = field;
    }

    @Override
    public FormField execute(CommandContext context) {
        //获取原来的表单定义
        FormDefinition old = getFormDefinition(context);
        Assert.isTrue(old != null, FORM_NOT_DEFINITION_ERROR);
        //复制新的表单对象
        FormDefinition newFormDefinition = copyFormDefinition(context, old);
        newFormDefinition.setDefault(true);
        //转换添加的字段
        FormField formField = newField(field);
        formField.setFormDefinitionId(newFormDefinition.getId());
        formField.setVersion(newFormDefinition.getVersion());

        //校验字段定义格式正确
        validateFieldBaseInfo(newFormDefinition, field);

        newFormDefinition.getFormFieldList().add(formField);
        //保存字段定义到数据库
        saveFormDefinition(context, newFormDefinition);
        //更新表结构
        if (isUpdateTable()) {
            //创建表结构
            createTable(context, newFormDefinition);
            if (isCopyData()) {
                copyTable(context, old, newFormDefinition);
            }
        }
        return formField;
    }

    public Field getField() {
        return field;
    }

}
