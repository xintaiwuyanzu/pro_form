package com.dr.framework.common.form.core.service;

import com.dr.framework.common.form.engine.TypeComponent;
import com.dr.framework.common.form.engine.model.core.FieldModel;

import java.util.Collection;
import java.util.Collections;

/**
 * 回调扩展接口，用来完成档案类型扩展
 * 用来初始化表单数据
 *
 * @author dr
 */
public interface FormDefinitionTypeProvider extends TypeComponent {
    /**
     * 获取获取该类型表单默认字段
     *
     * @return
     */
    default Collection<FieldModel> getInitFields() {
        return Collections.EMPTY_LIST;
    }

}
