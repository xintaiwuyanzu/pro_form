package com.dr.framework.common.form.core.service.impl;

import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.service.FormNameGenerator;

/**
 * 默认表名称生成器
 *
 * @author dr
 */
public class DefaultFormNameGenerator implements FormNameGenerator {
    @Override
    public String genTableName(Form form) {
        return String.join("_", form.getFormTable(), form.getVersion());
    }

    @Override
    public String genFieldName(Form form, Field field) {
        return String.join("_",
                "c",
                field.getFieldType(),
                String.valueOf(form.getFormOrder()));
    }
}
