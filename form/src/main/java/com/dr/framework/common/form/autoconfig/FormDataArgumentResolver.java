package com.dr.framework.common.form.autoconfig;

import com.dr.framework.common.form.annotations.Form;
import com.dr.framework.common.form.core.model.FormData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Iterator;

/**
 * 自定义表单数据参数拦截器
 */
public class FormDataArgumentResolver implements HandlerMethodArgumentResolver {
    Logger logger = LoggerFactory.getLogger(FormDataArgumentResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Form.class) && FormData.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String formDefinitionId = webRequest.getParameter("formDefinitionId");
        if (StringUtils.isEmpty(formDefinitionId)) {
            logger.warn("表单请求路径{}的请求定义为空，可能造成逻辑问题", mavContainer.getViewName());
        }
        String formDataId = webRequest.getParameter(FormData.FORM_DATA_ID_KEY);
        FormData formData = new FormData(formDefinitionId, formDataId);
        Iterator<String> iterator = webRequest.getParameterNames();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = webRequest.getParameter(key);
            formData.put(key, value);
        }
        return formData;
    }
}
