package com.dr.framework.common.form.validate.model;

import com.dr.framework.common.form.core.model.Field;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 表单一组校验结果
 *
 * @param <T> 泛型用来表示不同的表单数据类型
 * @author dr
 */
public class ValidateResults<T> {
    /**
     * 表单数据
     */
    private T data;
    /**
     * 所有字段校验结果
     */
    private List<ValidateResult> results;

    /**
     * 是否校验通过
     *
     * @return
     */
    public boolean success() {
        if (results != null) {
            //所有字段都校验通过才算校验通过
            return results.stream()
                    .allMatch(ValidateResult::isSuccess);
        }
        return true;
    }

    /**
     * 获取指定字段的校验结果
     *
     * @param field
     * @return
     */
    public List<ValidateResult> getValidateResult(Field field) {
        return results.stream()
                .filter(r -> r.getField().equals(field))
                .collect(Collectors.toList());
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<ValidateResult> getResults() {
        return results;
    }

    public void setResults(List<ValidateResult> results) {
        this.results = results;
    }
}
