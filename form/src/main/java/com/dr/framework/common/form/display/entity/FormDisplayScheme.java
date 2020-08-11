package com.dr.framework.common.form.display.entity;

import com.dr.framework.common.form.engine.entity.FormDefinitionRelation;
import com.dr.framework.common.form.engine.model.display.FieldDisplay;
import com.dr.framework.common.form.engine.model.display.FormDisplay;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;

import java.util.Collection;

/**
 * 表单显示方案
 *
 * @author dr
 */
@Table(name = Constants.TABLE_PREFIX + "FormDisplayScheme", module = Constants.MODULE_NAME, comment = "表单显示方案")
public class FormDisplayScheme extends FormDefinitionRelation implements FormDisplay {
    @Column(comment = "label显示宽度", length = 10)
    private Integer labelWidth;
    @Column(comment = "备注", length = 800)
    private String remarks;

    private Collection<FieldDisplay> fields;

    @Override
    public Integer getLabelWidth() {
        return labelWidth;
    }

    public void setLabelWidth(Integer labelWidth) {
        this.labelWidth = labelWidth;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public Collection<FieldDisplay> getFields() {
        return fields;
    }

    public void setFields(Collection<FieldDisplay> fields) {
        this.fields = fields;
    }
}

