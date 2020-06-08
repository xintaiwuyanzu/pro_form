package com.dr.framework.common.form.init.model;

public interface FormDefault {

    /**
     * 获取表单默认值
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
     * 默认环节名称
     *
     * @return
     */
    String getLinkName();

    /**
     * 默认环节编码
     *
     * @return
     */
    String getLinkCode();

    /**
     * 默认环节类型
     *
     * @return
     */
    String getDefaultType();

    /**
     * 默认环节状态
     *
     * @return
     */
    String getDefaultState();

    /**
     * 描述
     *
     * @return
     */
    String getDescription();

}
