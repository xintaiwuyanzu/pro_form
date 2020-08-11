package com.dr.framework.common.form.engine.model.core;

import com.dr.framework.common.form.engine.model.FormRelationModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 表单数据
 *
 * @author dr
 */
public interface FormDataModel extends FormRelationModel {
    /**
     * 获取表单数据Id
     *
     * @return
     */
    String getId();

    /**
     * 获取value
     *
     * @param key
     * @param <V>
     * @return
     */
    <V extends Serializable> V get(String key);

    /**
     * 设置数据
     *
     * @param key
     * @param value
     * @return
     */
    <V extends Serializable> V put(String key, V value);

    /**
     * 获取指定字段的数据
     *
     * @param field
     * @param <T>
     * @return
     */
    <T> T getFieldValue(FieldModel field);

    /**
     * 获取字符串value
     *
     * @param codeOrAlias
     * @return
     */
    String getString(String codeOrAlias);

    /**
     * 获取boolean Value
     *
     * @param codeOrAlias
     * @return
     */
    Boolean getBoolean(String codeOrAlias);

    /**
     * 获取Int value
     *
     * @param codeOrAlias
     * @return
     */
    Integer getInteger(String codeOrAlias);


    /**
     * 获取long value
     *
     * @param codeOrAlias
     * @return
     */

    Long getLong(String codeOrAlias);

    /**
     * 获取float value
     *
     * @param codeOrAlias
     * @return
     */

    Float getFloat(String codeOrAlias);

    /**
     * 获取double value
     *
     * @param codeOrAlias
     * @return
     */
    Double getDouble(String codeOrAlias);

    /**
     * 获取日期类型数据
     *
     * @param codeOrAlias
     * @return
     */
    Date getDate(String codeOrAlias);


}
