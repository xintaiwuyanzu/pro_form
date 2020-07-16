package com.dr.framework.common.form.core.model;

import java.util.Collection;

/**
 * 表单字段抽象定义
 *
 * @author caor
 */
public interface Field {
    /**
     * 获取字段主键
     *
     * @return
     */
    String getId();

    /**
     * 获取表单定义Id
     *
     * @return
     */
    String getFormDefinitionId();

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
    int getFieldOrder();


    /**
     * 获取状态
     *
     * @return
     */
    String getFieldState();

    /**
     * 获取版本号
     *
     * @return
     */


    Integer getVersion();

    /**
     * 获取中文名称
     *
     * @return
     */
    String getLabel();

    /**
     * 获取字段描述
     *
     * @return
     */
    String getDescription();

    /**
     * 获取备注信息
     *
     * @return
     */
    String getRemarks();

    /**
     * 获取数据权限Id
     *
     * @return
     */
    String getDataObjectId();

}
