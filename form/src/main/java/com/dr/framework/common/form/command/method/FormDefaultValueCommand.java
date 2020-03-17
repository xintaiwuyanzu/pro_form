package com.dr.framework.common.form.command.method;

import com.dr.framework.common.form.command.entity.FieldDefaultValue;
import com.dr.framework.common.form.command.entity.FormDefaultValue;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.List;
import java.util.UUID;

public class FormDefaultValueCommand implements Command<FormDefaultValue> {
    private FormDefaultValue formDefaultValue;
    private List<FieldDefaultValue> fieldDefaultValueList;

    public FormDefaultValueCommand(FormDefaultValue formDefaultValue, List<FieldDefaultValue> fieldDefaultValueList) {
        this.formDefaultValue = formDefaultValue;
        this.fieldDefaultValueList = fieldDefaultValueList;
    }

    /**
     * 保存默认值
     *
     * @param context
     * @return
     */
    @Override
    public FormDefaultValue execute(CommandContext context) {
        Assert.notNull(formDefaultValue, "默认值设置不能为空");
        Assert.isTrue(StringUtils.isNotEmpty(formDefaultValue.getFormId()), "formId不能为空");
        if (StringUtils.isEmpty(formDefaultValue.getId())) {
            formDefaultValue.setId(UUID.randomUUID().toString());
        }
        if (fieldDefaultValueList.size() > 0) {
            for (FieldDefaultValue fieldDefaultValue : fieldDefaultValueList){
                fieldDefaultValue.setFormDefaultValueId(formDefaultValue.getId());
                fieldDefaultValue.setFormId(formDefaultValue.getFormId());
                if (StringUtils.isEmpty(fieldDefaultValue.getId())){
                    fieldDefaultValue.setId(UUID.randomUUID().toString());
                }
                //保存字段默认值
                context.getMapper().insert(fieldDefaultValue);
            }
            //再保存主表信息
            context.getMapper().insert(formDefaultValue);
        }
        formDefaultValue.setFieldDefaultValueList(fieldDefaultValueList);
        return formDefaultValue;
    }
}
