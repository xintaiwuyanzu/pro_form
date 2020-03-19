package com.dr.framework.common.form.engine;

/**
 * 一个抽象的插件父类
 */
public interface Plugin {
    /**
     * 类型
     *
     * @return
     */
    String formType();

    /**
     * 描述
     *
     * @return
     */
    String description();


}
