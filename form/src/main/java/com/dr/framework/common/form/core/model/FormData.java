package com.dr.framework.common.form.core.model;

import com.dr.framework.common.entity.IdEntity;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

/**
 * 表单数据对象
 *
 * @author dr
 */
public class FormData extends HashMap<String, Serializable> {
    public static final String FORM_DATA_ID_KEY = IdEntity.ID_COLUMN_NAME;

    private String formDefinitionId;
    private String formDataId;

    public FormData(String formDefinitionId, String formDataId) {
        this.formDefinitionId = formDefinitionId;
        this.formDataId = formDataId;
        put(FORM_DATA_ID_KEY, formDataId);
    }

    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    public String getFormDataId() {
        return formDataId;
    }

    public String getStringValue(String keyOrAlias) {
        return null;
    }

    public <T> T getFieldValue(Field field) {
        return null;
    }

    /**
     * 获取字符串值
     *
     * @param keyOrAlias
     * @return
     */
    public String getString(String keyOrAlias) {
        Serializable sValue = get(keyOrAlias);
        if (sValue != null) {
            return sValue.toString();
        }
        return null;
    }

    /**
     * 获取boolean类型的值
     *
     * @param keyOrAlias
     * @return
     */
    public Boolean getBoolean(String keyOrAlias) {
        Serializable sValue = get(keyOrAlias);
        if (sValue instanceof Boolean) {
            return (Boolean) sValue;
        }
        if (sValue instanceof String) {
            return Boolean.valueOf((String) sValue);
        }
        if (sValue instanceof Number) {
            Number n = (Number) sValue;
            return n.intValue() != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        return null;
    }

    /**
     * 获取 Integer值
     *
     * @param keyOrAlias
     * @return
     */
    public Integer getInteger(String keyOrAlias) {
        Number answer = getNumber(keyOrAlias);
        if (answer == null) {
            return null;
        }
        if (answer instanceof Integer) {
            return (Integer) answer;
        }
        return Integer.valueOf(answer.intValue());
    }

    /**
     * 获取number值
     *
     * @param keyOrAlias
     * @return
     */
    public Number getNumber(String keyOrAlias) {
        Serializable answer = get(keyOrAlias);
        if (answer != null) {
            if (answer instanceof Number) {
                return (Number) answer;
            }
            if (answer instanceof String) {
                try {
                    final String text = (String) answer;
                    return NumberFormat.getInstance().parse(text);
                } catch (final ParseException e) {
                }
            }
        }
        return null;
    }

    /**
     * 获取long类型值
     *
     * @param keyOrAlias
     * @return
     */
    public Long getLong(String keyOrAlias) {
        Number answer = getNumber(keyOrAlias);
        if (answer == null) {
            return null;
        }
        if (answer instanceof Long) {
            return (Long) answer;
        }
        return Long.valueOf(answer.longValue());
    }

    /**
     * 获取Float
     *
     * @param keyOrAlias
     * @return
     */
    public Float getFloat(String keyOrAlias) {
        Number answer = getNumber(keyOrAlias);
        if (answer == null) {
            return null;
        }
        if (answer instanceof Float) {
            return (Float) answer;
        }
        return Float.valueOf(answer.floatValue());
    }

    /**
     * 获取Double值
     *
     * @param keyOrAlias
     * @return
     */
    public Double getDouble(String keyOrAlias) {
        Number answer = getNumber(keyOrAlias);
        if (answer == null) {
            return null;
        }
        if (answer instanceof Double) {
            return (Double) answer;
        }
        return Double.valueOf(answer.doubleValue());
    }

    /**
     * 获取日期类型的数据
     *
     * @param keyOrAlias
     * @return
     */
    public Date getDate(String keyOrAlias) {
        Serializable answer = get(keyOrAlias);
        if (answer instanceof Date) {
            return (Date) answer;
        }
        if (answer instanceof Long) {
            return new Date((Long) answer);
        }
        long v = getLong(keyOrAlias);
        return new Date(v);
    }


}
