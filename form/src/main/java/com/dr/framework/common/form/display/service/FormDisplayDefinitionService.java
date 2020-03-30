package com.dr.framework.common.form.display.service;

import com.dr.framework.common.form.button.model.Button;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.display.model.FieldDisplay;
import com.dr.framework.common.form.display.model.FormDisplay;

import java.util.List;

/**
 * @author caor
 */
public interface FormDisplayDefinitionService {
    /**
     * 获取全部表单显示方案列表
     *
     * @return
     */
    List<FormDisplay> getFormDisplayList();

    /**
     * 根据表单id获取表单的显示方案
     *
     * @return
     */
    List<Field> getFieldListByFormId(String formId);

    /**
     * 根据表单code获取表单的显示方案
     *
     * @return
     */
    List<Field> getFieldListByFormCode(String formCode);

    /**
     * 根据表单的显示方案id和字段id查询该显示方案下的某个字段的显示方案
     *
     * @param formDisplayId
     * @param fieldId
     * @return
     */
    List<FieldDisplay> getFieldDisplayByFormDisplayIdAndFieldId(String formDisplayId, String fieldId);

    /**
     * 根据表单显示方案id查询按钮
     *
     * @param displayId
     * @return
     */
    List<Button> getButtonListByFormDisplayId(String displayId);

    /**
     * 获取字段显示方案
     *
     * @param fieldId
     * @return
     */
    FieldDisplay getFieldDisplayByFieldId(String fieldId);

}
