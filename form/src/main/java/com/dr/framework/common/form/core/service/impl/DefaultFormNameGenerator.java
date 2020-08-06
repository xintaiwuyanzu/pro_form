package com.dr.framework.common.form.core.service.impl;

import com.dr.framework.common.form.engine.FormConfig;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.core.orm.database.Dialect;

import static com.dr.framework.common.form.util.StringUtils.generateShortUuid;

/**
 * 默认表名称生成器
 *
 * @author dr
 */
public class DefaultFormNameGenerator implements FormNameGenerator {
    private Dialect dialect;
    //TODO 根据全局定义判断是否生成多个版本的物理表结构
    private FormConfig formConfig;

    public DefaultFormNameGenerator(Dialect dialect, FormConfig formConfig) {
        this.dialect = dialect;
        this.formConfig = formConfig;
    }

    @Override
    public String genTableName(Form form) {
        String tableName = form instanceof FormDefinition ? ((FormDefinition) form).getFormTable() : generateShortUuid();
        return String.join("_",
                "f",
                //根据数据库驱动不同，转换不同表名的大小写
                dialect.convertObjectName(tableName),
                String.valueOf(form.getVersion()));
    }

    @Override
    public String genFieldName(Form form, Field field) {
        String fieldName = field.getFieldCode();
        if (org.springframework.util.StringUtils.isEmpty(fieldName)) {
            return generateShortUuid();
        }
        return fieldName;
    }

}
