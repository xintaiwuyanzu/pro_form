package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.util.Constants;

public abstract class AbstractVersionCommand<T> implements Command<T> {
    /**
     * 版本号默认条件
     */
    private String version = Constants.DEFAULT_VERSION;

    public AbstractVersionCommand() {
    }

    public AbstractVersionCommand(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
