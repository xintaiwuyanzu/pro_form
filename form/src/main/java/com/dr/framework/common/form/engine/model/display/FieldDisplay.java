package com.dr.framework.common.form.engine.model.display;

import com.dr.framework.common.form.engine.model.CodeNameModel;
import com.dr.framework.common.form.engine.model.MetaGetterModel;

/**
 * 字段的显示方案
 *
 * @author lic
 * @author dr
 */
public interface FieldDisplay extends CodeNameModel, MetaGetterModel {
    /**
     * 获取label宽度
     *
     * @return
     */
    Integer getLabelWidth();

}
