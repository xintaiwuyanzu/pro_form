package com.dr.framework.common.form.display.service;

import com.dr.framework.common.form.core.model.FieldType;
import com.dr.framework.common.form.display.model.FieldComponent;
import org.springframework.core.Ordered;

import java.util.List;

/**
 * 用来扩展自定义展示组件
 *
 * @author dr
 */
public interface FieldComponentProvider extends Ordered {

    /**
     * 是否支持指定的数据库类型
     *
     * @param type
     * @return
     */
    boolean accept(FieldType type);

    /**
     * 获取能够支持的控件列表
     *
     * @return
     */
    List<FieldComponent> components();


    //获取默认属性，用来辅助配置
    //保存字段显示扩展属性
}
