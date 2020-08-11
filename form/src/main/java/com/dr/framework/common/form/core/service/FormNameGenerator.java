package com.dr.framework.common.form.core.service;

import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.engine.model.core.FormModel;

/**
 * 表单名称生成器
 *
 * @author
 */
public interface FormNameGenerator {
    /**
     * 生成表单表名称
     *
     * @param formModel
     * @return
     */
    String genTableName(FormModel formModel);

    /**
     * 生成字段名称
     *
     * @param formModel
     * @param fieldModel
     * @return
     */
    String genFieldName(FormModel formModel, FieldModel fieldModel);
}
