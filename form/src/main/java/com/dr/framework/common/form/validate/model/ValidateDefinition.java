package com.dr.framework.common.form.validate.model;

/**
 * 字段校验定义
 *
 * @author dr
 */
public interface ValidateDefinition {

    /**
     * 获取设置校验主表ID
     *
     * @return
     */
    String getId();

    /**
     * 获取设置校验主表code
     *
     * @return
     */
    String getValidateCode();

    /**
     * 获取表单定义Id
     *
     * @return
     */
    String getFormDefinitionId();


    String getValidateName();

    /**
     * 获取设置校验主表类型
     *
     * @return
     */
    String getValidateType();

    /**
     * 获取校验描述
     *
     * @return
     */
    String getDescription();

    /**
     * 获取校验状态
     *
     * @return
     */
    String getValidateState();

    /**
     * 获取顺序号
     *
     * @return
     */
    int getValidateOrder();


    String getVersion();

}
