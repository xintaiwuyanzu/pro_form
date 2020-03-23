package com.dr.framework.common.form.engine;

/**
 * 一个抽象接口，用来统一type和描述，用来给前端展示使用
 *
 * @author dr
 */
public interface TypeObject {
    /**
     * 类型
     *
     * @return
     */
    String type();

    /**
     * 描述
     *
     * @return
     */
    String description();

    /**
     * 执行排序
     *
     * @return
     */
    default int order() {
        return 0;
    }
}
