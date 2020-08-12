package com.dr.framework.common.form.display.entity;

import com.dr.framework.common.form.engine.entity.FormDefinitionRelation;
import com.dr.framework.common.form.engine.model.display.FieldDisplay;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.core.orm.annotations.Column;
import com.dr.framework.core.orm.annotations.Table;

/**
 * 表单字段显示方案
 *
 * @author dr
 */
@Table(name = Constants.TABLE_PREFIX + "FieldDisplayScheme", module = Constants.MODULE_NAME, comment = "表单字段显示方案")
public class FieldDisplayScheme extends FormDefinitionRelation implements FieldDisplay {

    @Column(comment = "主表外键", length = 100)
    private String formDisplayId;

    @Column(comment = "label显示宽度", length = 10)
    private Integer labelWidth;
    @Column(comment = "备注", length = 800)
    private String remarks;

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

    public String getFormDisplayId() {
        return formDisplayId;
    }

    public void setFormDisplayId(String formDisplayId) {
        this.formDisplayId = formDisplayId;
    }
}
