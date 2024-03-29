package com.dr.framework.common.form.engine.model;

/**
 * code
 * name
 * type
 *
 *
 * @author dr
 */
public interface CodeNameModel extends BaseModel {
    /**
     * 获取编码
     *
     * @return
     */
    String getCode();

    /**
     * 获取名称描述
     *
     * @return
     */
    String getName();

    /**
     * 获取类型
     *
     * @return
     */
    String getType();

    /**
     * 获取排序
     *
     * @return
     */
    Integer getOrder();

}
