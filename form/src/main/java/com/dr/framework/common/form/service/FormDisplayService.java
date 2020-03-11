package com.dr.framework.common.form.service;

import com.dr.framework.common.form.baseobject.Button;
import com.dr.framework.common.form.baseobject.FieldDisplay;
import com.dr.framework.common.form.baseobject.FormDisplay;
import com.dr.framework.common.form.model.Field;

import java.util.List;

/**
 * @author caor
 */
public interface FormDisplayService {
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
}
