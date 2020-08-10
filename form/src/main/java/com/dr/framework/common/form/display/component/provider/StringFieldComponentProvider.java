package com.dr.framework.common.form.display.component.provider;

import com.dr.framework.common.form.core.model.FieldType;
import com.dr.framework.common.form.display.component.FieldComponentProvider;
import com.dr.framework.common.form.display.model.FieldComponent;

import java.util.List;

/**
 * 数据类型为字符串的组件提供service
 *
 * @author dr
 */
public class StringFieldComponentProvider extends AbstractFieldComponentProvider implements FieldComponentProvider {

    @Override
    public boolean accept(FieldType type) {
        return FieldType.STRING.equals(type);
    }

    @Override
    public List<FieldComponent> components() {
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
