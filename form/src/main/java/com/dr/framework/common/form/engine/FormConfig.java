package com.dr.framework.common.form.engine;

import com.dr.framework.common.form.engine.model.FormModel;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 表单默认配置项
 */
@ConfigurationProperties(prefix = "dr.form")
public class FormConfig {
    public static boolean DEFAULT_ENABLE_MULTI_VERSION_TABLE = true;
    /**
     * 是否启用多版本的表结构
     * <p>
     * 启用则表示每一个表单定义的多个版本
     * <p><b>每一个版本都生成一个物理表结构<b/>
     * <br>详细意义和介绍参考{@link FormModel#getVersion()}
     */
    private boolean enableMultiVersionTable = DEFAULT_ENABLE_MULTI_VERSION_TABLE;
    /**
     * 不管默认的多版本配置，特定声明启用多版本的表单定义表前缀名称
     * 可以是多个，以逗号隔开
     */
    private String enabledPreFix;
    /**
     * 不管默认的多版本配置，特定声明禁用多版本的表单定义表前缀名称
     * 可以是多个，以逗号隔开
     */
    private String disabledPreFix;

    /**
     * 判断指定的表单编码是否启用多版本表结构
     *
     * @param formCode
     * @return
     */
    public boolean multiTableEnable(String formCode) {
        return enableMultiVersionTable;
    }


    public boolean isEnableMultiVersionTable() {
        return enableMultiVersionTable;
    }

    public void setEnableMultiVersionTable(boolean enableMultiVersionTable) {
        this.enableMultiVersionTable = enableMultiVersionTable;
    }

    public String getEnabledPreFix() {
        return enabledPreFix;
    }

    public void setEnabledPreFix(String enabledPreFix) {
        this.enabledPreFix = enabledPreFix;
    }

    public String getDisabledPreFix() {
        return disabledPreFix;
    }

    public void setDisabledPreFix(String disabledPreFix) {
        this.disabledPreFix = disabledPreFix;
    }
}
