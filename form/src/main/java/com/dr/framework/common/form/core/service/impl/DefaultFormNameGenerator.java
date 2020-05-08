package com.dr.framework.common.form.core.service.impl;

import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.core.orm.database.Dialect;

/**
 * 默认表名称生成器
 *
 * @author dr
 */
public class DefaultFormNameGenerator implements FormNameGenerator {
    private Dialect dialect;

    public DefaultFormNameGenerator(Dialect dialect) {
        this.dialect = dialect;
    }

    @Override
    public String genTableName(Form form) {
        return String.join("_",
                //根据数据库驱动不同，转换不同表名的大小写
                dialect.convertObjectName(form.getFormTable()),
                form.getVersion());
    }

    //TODO 未实现自定义字段
    @Override
    public String genFieldName(Form form, Field field) {
        return field.getFieldCode();
    }
}
