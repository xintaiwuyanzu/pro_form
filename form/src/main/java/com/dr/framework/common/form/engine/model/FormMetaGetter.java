package com.dr.framework.common.form.engine.model;

import java.util.Map;

/**
 * 对象有元数据
 *
 * @author dr
 */
public interface FormMetaGetter {
    /**
     * 获取元数据，基本信息不能够完全包含属性，需要扩展字段处理
     * <p>
     * 一般是使用clob存放
     *
     * @return
     */
    Map<String, Object> getMeta();
}
