package com.dr.framework.common.form.display.component;

import com.dr.framework.common.form.engine.model.core.FieldType;
import com.dr.framework.common.form.engine.model.display.FieldComponent;

/**
 * 文本输入框控件
 *
 * @author dr
 */
public class InputFieldComponent extends AbstractFieldComponent implements FieldComponent {
    @Override
    public FieldType getFieldType() {
        return FieldType.STRING;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
