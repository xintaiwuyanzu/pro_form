package com.dr.framework.common.form.validate.model;

import com.dr.framework.common.form.core.model.Field;

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
    private Field field;

    public static ValidateResult success(Field field) {
        return success(SUCCESS_MESSAGE, field);
    }

    public static ValidateResult success(String message, Field field) {
        return new ValidateResult(true, message, field);
    }

    public static ValidateResult fail(String message, Field field) {
        return new ValidateResult(false, message, field);
    }

    public ValidateResult(boolean success, String message, Field field) {
        this.success = success;
        this.message = message;
        this.field = field;
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

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
