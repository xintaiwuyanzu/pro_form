package com.dr.framework.common.form.engine.model;

/**
 * 表单定义关联实体
 *
 * @author
 */
public interface FormRelationModel {
    /**
     * 获取表单定义名称
     *
     * @return
     */
    String getFormDefinitionName();

    /**
     * 获取表单定义Id
     *
     * @return
     */
    String getFormDefinitionId();

    /**
     * 获取表单定义编码
     *
     * @return
     */
    String getFormDefinitionCode();

    /**
     * 获取表单定义版本
     * <p>
     * 表单关联实体版本没意义，
     * 表单关联实体的版本也是对应表单定义的版本
     *
     * @return
     */
    Integer getVersion();
}
