package com.dr.framework.common.form.core.service;

import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.Form;

/**
 * 表单名称生成器
 *
 * @author
 */
public interface FormNameGenerator {
    /**
     * 生成表单表名称
     *
     * @param form
     * @return
     */
    String genTableName(Form form);

    /**
     * 生成字段名称
     *
     * @param form
     * @param field
     * @return
     */
    String genFieldName(Form form, Field field);
}
