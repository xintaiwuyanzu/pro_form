package com.dr.framework.common.form.core.model;

import com.dr.framework.common.form.engine.model.FormModel;
import com.dr.framework.common.form.util.StringUtils;

/**
 * 表单
 *
 * @author dr
 */
public interface Form extends FormModel {
    /**
     * 获取表单编码
     * 表单编码生成之后
     * 同一个code可以有多个版本，多个物理表结构
     * 表名使用
     * {@link StringUtils#generateShortUuid()}
     * 和
     * {@link  com.dr.framework.common.form.core.service.FormNameGenerator}
     * 随机生成
     *
     * @return
     */
    String getFormCode();

    /**
     * 获取表单名称
     *
     * @return
     */
    String getFormName();

    /**
     * 获取表单类型
     *
     * @return
     */
    String getFormType();

    /**
     * 获取表单顺序号
     *
     * @return
     */
    Integer getFormOrder();

    /**
     * 获取状态
     *
     * @return
     */
    String getFormState();

    /**
     * 是否内部使用的表定义
     *
     * @return
     */
    boolean isBuildIn();

}
