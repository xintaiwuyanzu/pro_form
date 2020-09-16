package com.dr.framework.common.form.core.model;

import com.dr.framework.common.entity.IdEntity;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.engine.model.core.FormDataModel;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

/**
 * 表单数据对象
 * TODO code和别名的映射，key大小写问题
 *
 * @author dr
 */
public class FormData extends HashMap<String, Serializable> implements FormDataModel {
    public static final String FORM_DATA_ID_KEY = IdEntity.ID_COLUMN_NAME;
    /**
     * 表单定义Id
     */
    private String formDefinitionId;

    /**
     * 表单定义编码
     */
    private String formCode;
    /**
     * 表单定义版本
     */
    private Integer formVersion;

    private String formDefinitionName;

    public FormData(String formDefinitionId) {
        this(formDefinitionId, null);
    }

    public FormData(String formDefinitionId, String formDataId) {
        this(formDefinitionId, null, null, formDataId);
    }

    public FormData(String formCode, Integer version, String formDataId) {
        this(null, formCode, version, formDataId);
    }

    public FormData(String formDefinitionId, String formCode, Integer version, String formDataId) {
        this.formDefinitionId = formDefinitionId;
        this.formCode = formCode;
        this.formVersion = version;
        //表单数据Id
        put(FORM_DATA_ID_KEY, formDataId);
    }

    public void setFormDefinitionName(String formDefinitionName) {
        this.formDefinitionName = formDefinitionName;
    }

    @Override
    public String getFormDefinitionName() {
        return formDefinitionName;
    }

    @Override
    public String getFormDefinitionId() {
        return formDefinitionId;
    }

    public void setFormDefinitionId(String formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
    }

    public void setFormDefinitionCode(String formDefinitionCode) {
        this.formCode = formDefinitionCode;
    }


    @Override
    public String getFormDefinitionCode() {
        return formCode;
    }

    @Override
    public Integer getVersion() {
        return formVersion;
    }

    public void setVersion(Integer formVersion) {
        this.formVersion = formVersion;
    }

    @Override
    public <T> T getFieldValue(FieldModel field) {
        if (field != null) {
            String fieldCode = field.getFieldCode();

            switch (field.getFieldType()) {
                case STRING:
                    return (T) getString(fieldCode);
                case DATE:
                case LONG:
                    return (T) getLong(fieldCode);
                case NUMBER:
                    return (T) getNumber(fieldCode);
                case BYTES:
                    //TODO
                default:
                    return null;
            }
        }
        return null;
    }

    @Override
    public String getId() {
        return getString(FORM_DATA_ID_KEY);
    }

    public void setId(String id) {
        put(FORM_DATA_ID_KEY, id);
    }

    @Override
    public Serializable put(String key, Serializable value) {
        return super.put(key, value);
    }

    @Override
    public <V extends Serializable> V get(String key) {
        return (V) super.get(key);
    }

    /**
     * 获取字符串值
     *
     * @param codeOrAlias
     * @return
     */
    @Override
    public String getString(String codeOrAlias) {
        Serializable sValue = get(codeOrAlias);
        if (sValue != null) {
            return sValue.toString();
        }
        return null;
    }

    /**
     * 获取boolean类型的值
     *
     * @param codeOrAlias
     * @return
     */
    @Override
    public Boolean getBoolean(String codeOrAlias) {
        Serializable sValue = get(codeOrAlias);
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
     * @param codeOrAlias
     * @return
     */
    @Override
    public Integer getInteger(String codeOrAlias) {
        Number answer = getNumber(codeOrAlias);
        if (answer == null) {
            return null;
        }
        if (answer instanceof Integer) {
            return (Integer) answer;
        }
        return answer.intValue();
    }

    /**
     * 获取number值
     *
     * @param codeOrAlias
     * @return
     */
    public Number getNumber(String codeOrAlias) {
        Serializable answer = get(codeOrAlias);
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
     * @param codeOrAlias
     * @return
     */
    @Override
    public Long getLong(String codeOrAlias) {
        Number answer = getNumber(codeOrAlias);
        if (answer == null) {
            return null;
        }
        if (answer instanceof Long) {
            return (Long) answer;
        }
        return answer.longValue();
    }

    /**
     * 获取Float
     *
     * @param codeOrAlias
     * @return
     */
    @Override
    public Float getFloat(String codeOrAlias) {
        Number answer = getNumber(codeOrAlias);
        if (answer == null) {
            return null;
        }
        if (answer instanceof Float) {
            return (Float) answer;
        }
        return answer.floatValue();
    }

    /**
     * 获取Double值
     *
     * @param codeOrAlias
     * @return
     */
    @Override
    public Double getDouble(String codeOrAlias) {
        Number answer = getNumber(codeOrAlias);
        if (answer == null) {
            return null;
        }
        if (answer instanceof Double) {
            return (Double) answer;
        }
        return answer.doubleValue();
    }

    /**
     * 获取日期类型的数据
     *
     * @param codeOrAlias
     * @return
     */
    @Override
    public Date getDate(String codeOrAlias) {
        Serializable answer = get(codeOrAlias);
        if (answer instanceof Date) {
            return (Date) answer;
        }
        if (answer instanceof Long) {
            return new Date((Long) answer);
        }
        long v = getLong(codeOrAlias);
        return new Date(v);
    }


}
