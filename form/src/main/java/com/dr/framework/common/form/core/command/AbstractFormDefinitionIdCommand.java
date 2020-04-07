package com.dr.framework.common.form.core.command;

public abstract class AbstractFormDefinitionIdCommand<T> extends AbstractVersionCommand<T> {
    private String formDefinitionId;


    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    public void setFormDefinitionId(String formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
    }
}
