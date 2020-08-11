package com.dr.framework.common.form.core.service.impl;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.engine.config.FormConfig;
import com.dr.framework.core.orm.database.Dialect;

import static com.dr.framework.common.form.util.StringUtils.generateShortUuid;

/**
 * 默认表名称生成器
 *
 * @author dr
 */
public class DefaultFormNameGenerator implements FormNameGenerator {
    private final Dialect dialect;
    private final FormConfig formConfig;

    public DefaultFormNameGenerator(Dialect dialect, FormConfig formConfig) {
        this.dialect = dialect;
        this.formConfig = formConfig;
    }

    @Override
    public String genTableName(FormModel formModel) {
        //根据全局定义判断是否生成多个版本的物理表结构
        boolean versionEnable = formConfig.multiTableEnable(formModel.getFormCode());
        String tableName = formModel instanceof FormDefinition ? ((FormDefinition) formModel).getFormTable() : generateShortUuid();
        //根据数据库驱动不同，转换不同表名的大小写
        tableName = dialect.convertObjectName(tableName);
        String[] params = versionEnable ? new String[]{"f", tableName} : new String[]{"f", tableName, String.valueOf(formModel.getVersion())};
        return String.join("_", params);
    }

    @Override
    public String genFieldName(FormModel formModel, FieldModel fieldModel) {
        String fieldName = fieldModel.getFieldCode();
        if (org.springframework.util.StringUtils.isEmpty(fieldName)) {
            return generateShortUuid();
        }
        return fieldName;
    }

}
