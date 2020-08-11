package com.dr.framework.common.form.engine.model.display;

import com.dr.framework.common.form.engine.model.CodeNameModel;
import com.dr.framework.common.form.engine.model.FormRelationModel;
import com.dr.framework.common.form.engine.model.MetaGetterModel;
import com.dr.framework.common.form.engine.model.PermissionModel;

import java.util.Collection;

/**
 * 表单的显示方案
 *
 * @author caor
 * @author dr
 */
public interface FormDisplay extends CodeNameModel, FormRelationModel, MetaGetterModel, PermissionModel {
    /**
     * 获取label宽度
     *
     * @return
     */
    Integer getLabelWidth();

    /**
     * 获取表单显示方案所有的字段显示方案定义
     *
     * @return
     */
    Collection<FieldDisplay> getFields();

}
