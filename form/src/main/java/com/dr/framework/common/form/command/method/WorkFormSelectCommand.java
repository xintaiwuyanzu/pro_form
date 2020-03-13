package com.dr.framework.common.form.command.method;

public class WorkFormSelectCommand {
    private String formType;
    private String formId;
    private String formName;

    public WorkFormSelectCommand(String formType, String formId, String formName) {
        this.formType = formType;
        this.formId = formId;
        this.formName = formName;
    }
}
