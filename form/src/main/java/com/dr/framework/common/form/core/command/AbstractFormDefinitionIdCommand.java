package com.dr.framework.common.form.core.command;

public abstract class AbstractFormDefinitionIdCommand<T> extends AbstractVersionCommand<T> {
    private String formDefinitionId;

    public AbstractFormDefinitionIdCommand() {
    }


    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    public void setFormDefinitionId(String formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
    }

    public AbstractFormDefinitionIdCommand(String formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
    }

    public AbstractFormDefinitionIdCommand(String version, String formDefinitionId) {
        super(version);
        this.formDefinitionId = formDefinitionId;
    }
}
