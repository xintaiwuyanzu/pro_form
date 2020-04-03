package com.dr.framework.common.form.validate.model;

public interface ValidateDefinitionField {

    /**
     * 获取校验字段主键
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
     * 获取表单校验主表Id
     *
     * @return
     */
    String getValidateFormId();

    /**
     * 获取字段名称
     *
     * @return
     */
    String getFieldName();

    /**
     * 获取字段编码
     *
     * @return
     */
    String getFieldCode();

    /**
     * 获取字段类型
     *
     * @return
     */
    String getFieldType();

    /**
     * 是否为空
     *
     * @return
     */
    boolean isNotNull();

    /**
     * 校验匹配1
     *
     * @return
     */
    String getCheckConfig();

    /**
     * 校验匹配2
     *
     * @return
     */
    String getCheckConfigTwo();

    /**
     * 校验匹配3
     *
     * @return
     */
    String getCheckConfigThree();

    /**
     * 顺序号
     *
     * @return
     */
    int getOrder();

    /**
     * 自定义
     *
     * @return
     */
    String getCustom();

    /**
     * 备用字段1
     *
     * @return
     */
    String getText1();

    /**
     * 备用字段2
     *
     * @return
     */
    String getText2();

    /**
     * 备用字段3
     *
     * @return
     */
    String getText3();
}
