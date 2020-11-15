package com.dr.framework.common.form.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解拦截表单对象
 *
 * @author dr
 * @see com.dr.framework.common.form.autoconfig.FormDataArgumentResolver
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Form {
    boolean check() default false;
}
