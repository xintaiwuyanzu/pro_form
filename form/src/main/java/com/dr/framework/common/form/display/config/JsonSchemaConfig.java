package com.dr.framework.common.form.display.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 显示方案JsonSchema相关配置
 *
 * @author dr
 */
@ConfigurationProperties(prefix = "common.form.schema")
public class JsonSchemaConfig {
    /**
     * 控件宽度key
     */
    private String labelWidthKey = "ui:labelWidth";
    /**
     * 显示控件key
     */
    private String widgetKey = "ui:widget";
    /**
     * 前端扩展属性key
     */
    private String optionsKey = "ui:options";

    public String getLabelWidthKey() {
        return labelWidthKey;
    }

    public void setLabelWidthKey(String labelWidthKey) {
        this.labelWidthKey = labelWidthKey;
    }

    public String getWidgetKey() {
        return widgetKey;
    }

    public void setWidgetKey(String widgetKey) {
        this.widgetKey = widgetKey;
    }

    public String getOptionsKey() {
        return optionsKey;
    }

    public void setOptionsKey(String optionsKey) {
        this.optionsKey = optionsKey;
    }
}
