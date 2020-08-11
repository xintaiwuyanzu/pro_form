package com.dr.framework.common.form.display.component.provider;

import com.dr.framework.common.form.display.component.FieldComponentProvider;
import com.dr.framework.common.form.engine.model.display.FieldComponent;

import java.util.List;

/**
 * 数据类型为字符串的组件提供service
 *
 * @author dr
 */
public class StringFieldComponentProvider extends AbstractFieldComponentProvider implements FieldComponentProvider {

    @Override
    public boolean accept(FieldTypeEnum type) {
        return FieldTypeEnum.STRING.equals(type);
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
