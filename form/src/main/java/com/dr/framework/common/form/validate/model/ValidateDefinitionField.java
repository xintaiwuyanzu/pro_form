package com.dr.framework.common.form.validate.model;

public interface ValidateDefinitionField {

    /**
     * 获取校验字段主键
     *
     * @return
     */
    String getId();

    String getFormDefinitionId();

    String getValidateFormId();

    String getFieldName();

    String getFieldType();

    boolean isNotNull();

    String getCheckConfig();

    String getCheckConfigTwo();

    String getCheckConfigThree();

    String getCustom();

    String getText1();

    String getText2();

    String getText3();
}
