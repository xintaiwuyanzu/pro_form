package com.dr.framework.common.form.validate.model;

import com.dr.framework.common.form.engine.model.core.FieldModel;

/**
 * 单条校验结果
 *
 * @author dr
 */
public class ValidateResult {

    public static final String SUCCESS_MESSAGE = "校验通过";

    /**
     * 是否校验通过
     */
    private boolean success;
    /**
     * 校验消息
     */
    private String message;
    /**
     * 校验字段
     */
    private FieldModel fieldModel;

    public static ValidateResult success(FieldModel fieldModel) {
        return success(SUCCESS_MESSAGE, fieldModel);
    }

    public static ValidateResult success(String message, FieldModel fieldModel) {
        return new ValidateResult(true, message, fieldModel);
    }

    public static ValidateResult fail(String message, FieldModel fieldModel) {
        return new ValidateResult(false, message, fieldModel);
    }

    public ValidateResult(boolean success, String message, FieldModel fieldModel) {
        this.success = success;
        this.message = message;
        this.fieldModel = fieldModel;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FieldModel getField() {
        return fieldModel;
    }

    public void setField(FieldModel fieldModel) {
        this.fieldModel = fieldModel;
    }
}
