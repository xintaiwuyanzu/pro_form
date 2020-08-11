package com.dr.framework.common.form.init.command;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.init.entity.FieldDefaultValue;
import com.dr.framework.common.form.init.entity.FormDefaultValue;
import com.dr.framework.common.form.init.model.FieldDefault;
import com.dr.framework.common.form.init.model.FormDefault;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;

public class FormDefaultValueInsertCommand implements Command<FormDefault> {
    private final FormDefault formDefault;
    private final Collection<FieldDefault> fieldDefaultList;

    public FormDefaultValueInsertCommand(FormDefault formDefault, Collection<FieldDefault> fieldDefaultList) {
        this.formDefault = formDefault;
        this.fieldDefaultList = fieldDefaultList;
    }

    /**
     * 保存默认值
     *
     * @param context
     * @return
     */
    @Override
    public FormDefault execute(CommandContext context) {
        Assert.notNull(formDefault, "默认值设置不能为空");
        Assert.isTrue(StringUtils.isNotEmpty(formDefault.getFormDefinitionId()), "formId不能为空");
        FormDefaultValue formDefaultValue = getFormDefaultValue(formDefault);
        Collection<FieldDefaultValue> fieldDefaultValueList = getFormFieldDefault(formDefaultValue);
        if (fieldDefaultValueList.size() > 0) {
            for (FieldDefaultValue fieldDefaultValue : fieldDefaultValueList) {
                //保存字段默认值
                context.getMapper().insert(fieldDefaultValue);
            }
            //再保存主表信息
            context.getMapper().insert(formDefaultValue);
        }
        formDefaultValue.setFieldDefaultList(fieldDefaultValueList);
        return formDefaultValue;
    }

    public FormDefaultValue getFormDefaultValue(FormDefault formDefault) {
        FormDefaultValue formDefaultValue = new FormDefaultValue();
        formDefaultValue.setFormDefinitionId(formDefault.getFormDefinitionId());
        formDefaultValue.setDefaultType(formDefault.getDefaultType());
        formDefaultValue.setLinkCode(formDefault.getLinkCode());
        formDefaultValue.setLinkName(formDefault.getLinkName());
        formDefaultValue.setCreateDate(System.currentTimeMillis());
        formDefaultValue.setId(formDefault.getId());
        return formDefaultValue;
    }

    public Collection<FieldDefaultValue> getFormFieldDefault(FormDefaultValue formDefaultValue) {
        Collection<FieldDefaultValue> fieldDefaultValues = new ArrayList<>();
        if (fieldDefaultList.size() > 0) {
            for (FieldDefault fieldDefault : fieldDefaultList) {
                FieldDefaultValue fieldDefaultValue = new FieldDefaultValue();
                fieldDefaultValue.setId(fieldDefault.getId());
                fieldDefaultValue.setCustom(fieldDefault.getCustom());
                fieldDefaultValue.setDefaultValue(fieldDefault.getDefaultValue());
                fieldDefaultValue.setFieldName(fieldDefault.getFieldName());
                fieldDefaultValue.setFieldType(fieldDefault.getFieldType());
                fieldDefaultValue.setFormDefaultValueId(formDefaultValue.getId());
                fieldDefaultValue.setFormDefinitionId(fieldDefault.getFormDefaultValueId());
                fieldDefaultValue.setCreateDate(System.currentTimeMillis());
                fieldDefaultValues.add(fieldDefaultValue);
            }
        }
        return fieldDefaultValues;
    }

}
