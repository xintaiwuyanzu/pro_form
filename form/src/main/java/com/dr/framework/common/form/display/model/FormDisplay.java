package com.dr.framework.common.form.display.model;

import com.dr.framework.common.form.engine.model.FormCodeNameModel;
import com.dr.framework.common.form.engine.model.FormDefinitionRelationModel;
import com.dr.framework.common.form.engine.model.FormMetaGetter;

import java.util.Collection;
import java.util.Collections;

/**
 * 表单的显示方案
 *
 * @author caor
 * @author dr
 */
public interface FormDisplay extends FormCodeNameModel, FormDefinitionRelationModel, FormMetaGetter {
    /**
     * 获取label宽度
     *
     * @return
     */
    int getLabelWidth();

    /**
     * 获取表单显示方案所有的字段显示方案定义
     *
     * @return
     */
    default Collection<FieldDisplay> getFields() {
        return Collections.EMPTY_LIST;
    }

}
