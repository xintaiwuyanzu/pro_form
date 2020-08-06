package com.dr.framework.common.form.display.model;

import com.dr.framework.common.form.engine.model.FormCodeNameModel;
import com.dr.framework.common.form.engine.model.FormDefinitionRelationModel;

import java.util.Collection;
import java.util.Collections;

/**
 * 表单的显示方案
 *
 * @author caor
 * @author dr
 */
public interface FormDisplay extends FormCodeNameModel, FormDefinitionRelationModel {
    /**
     * 获取宽度
     *
     * @return
     */
    int getWidth();

    /**
     * 获取高度
     *
     * @return
     */
    int getHeight();

    /**
     * 获取背景色
     *
     * @return
     */
    String getBGColor();

    /**
     * 获取css样式
     *
     * @return
     */
    String getCss();

    /**
     * 获取表单显示方案所有的字段显示方案定义
     *
     * @return
     */
    default Collection<FieldDisplay> getFields() {
        return Collections.EMPTY_LIST;
    }

}
