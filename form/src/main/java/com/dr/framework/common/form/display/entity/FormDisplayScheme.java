package com.dr.framework.common.form.display.entity;

import com.dr.framework.common.form.engine.entity.FormDefinitionRelation;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.form.engine.model.display.FieldDisplay;
import com.dr.framework.common.form.engine.model.display.FormDisplay;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public FormDisplayScheme() {
    }

    public FormDisplayScheme(FormDisplay display, FormModel formModel) {
        if (display != null) {
            setId(display.getId());
            setLabelWidth(display.getLabelWidth());
            setRemarks(display.getRemarks());
            setRemarks(display.getRemarks());
            setFormDefinitionId(formModel.getId());
            setVersion(formModel.getVersion());
            setCode(display.getCode());
            setName(display.getName());
            setType(display.getType());
            setDescription(display.getDescription());

            setMeta(display.getMeta());
            setFields(display.getFields() == null ? Collections.EMPTY_LIST : display.getFields().stream().collect(Collectors.toList()));
        }
    }


    private List<? extends FieldDisplay> fields;

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
    public List<? extends FieldDisplay> getFields() {
        return fields;
    }

    @Override
    public FieldDisplay getFieldByCode(String fieldCode) {
        for (FieldDisplay fieldDisplay : fields) {
            if (fieldDisplay.getCode().equalsIgnoreCase(fieldCode)) {
                return fieldDisplay;
            }
        }
        return null;
    }

    public void setFields(List<? extends FieldDisplay> fields) {
        this.fields = fields;
    }
}

