package com.dr.framework.common.form.engine.model.core;

import com.dr.framework.common.form.engine.model.BaseModel;
import com.dr.framework.common.form.engine.model.FormRelationModel;
import com.dr.framework.common.form.engine.model.MetaGetterModel;

import java.util.Collection;

/**
 * 表单字段抽象定义
 * TODO 添加是否默认生成的字段
 *
 * @author caor
 * @author dr
 */
public interface FieldModel extends BaseModel, FormRelationModel, MetaGetterModel {
    /**
     * 获取字段编码，唯一，当作字段名称使用
     *
     * @return
     */
    String getFieldCode();

    /**
     * 获取字段别名
     *
     * @return
     */
    Collection<String> getFieldAlias();

    /**
     * 获取字段类型
     *
     * @return
     */
    FieldType getFieldType();

    /**
     * 获取字段长度
     *
     * @return
     */
    int getFieldLength();

    /**
     * 当字段类型为float的时候
     * 获取字段精度
     *
     * @return
     */
    int getFieldScale();

    /**
     * 获取字段顺序号
     *
     * @return
     */
    Integer getFieldOrder();

    /**
     * 获取状态
     *
     * @return
     */
    String getFieldState();

    /**
     * 获取中文名称
     *
     * @return
     */
    String getLabel();
}
