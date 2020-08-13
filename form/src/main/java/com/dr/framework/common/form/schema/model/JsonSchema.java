package com.dr.framework.common.form.schema.model;

import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.form.engine.model.display.FormDisplay;

/**
 * 暂存对象，用来存储将要序列化的属性
 *
 * @author dr
 */
public class JsonSchema {
    /**
     * 表单定义
     */
    private FormModel formModel;
    /**
     * 表单显示方案
     */
    private FormDisplay formDisplay;
    //TODO   表单校验
    //TODO  表单默认值

    public FormModel getFormModel() {
        return formModel;
    }

    public void setFormModel(FormModel formModel) {
        this.formModel = formModel;
    }

    public FormDisplay getFormDisplay() {
        return formDisplay;
    }

    public void setFormDisplay(FormDisplay formDisplay) {
        this.formDisplay = formDisplay;
    }
}
