package com.dr.framework.common.form.display.command;

import com.dr.framework.common.config.service.CommonMetaService;
import com.dr.framework.common.entity.StatusEntity;
import com.dr.framework.common.form.display.entity.FormDisplayScheme;
import com.dr.framework.common.form.display.service.FormDisplayService;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.form.engine.model.display.FieldDisplay;
import com.dr.framework.common.form.engine.model.display.FormDisplay;
import com.dr.framework.common.service.CommonService;
import org.springframework.util.Assert;

/**
 * 添加表单显示方案
 *
 * @author dr
 */
public class FormDisplayAddCommand extends AbstractFormDisplayCommand implements Command<FormDisplay> {
    /**
     * 要添加的显示方案对象
     */
    private FormDisplay formDisplay;
    /**
     * 是否更新对应表单定义的所有版本
     * TODO
     */
    private boolean modifyAllVersion;

    public FormDisplayAddCommand(FormDisplay formDisplay, boolean modifyAllVersion) {
        this.formDisplay = formDisplay;
        this.modifyAllVersion = modifyAllVersion;
    }

    @Override
    public FormDisplay execute(CommandContext context) {
        Assert.notNull(formDisplay, "表单显示方案不能为空！");
        validateFormDisplay(context, formDisplay, true);

        FormModel formModel = getFormDefinitionByFormDisplay(context, formDisplay);
        FormDisplayScheme formDisplayScheme = new FormDisplayScheme(formDisplay, formModel);
        CommonService.bindCreateInfo(formDisplayScheme);
        //添加基本信息
        formDisplayScheme.setStatus(StatusEntity.STATUS_ENABLE_STR);
        context.getMapper().insert(formDisplayScheme);

        if (formDisplayScheme.getMeta() != null && !formDisplayScheme.getMeta().isEmpty()) {
            CommonMetaService metaService = context.getApplicationContext().getBean(CommonMetaService.class);
            metaService.setMetaData(formDisplayScheme.getId(), FormDisplayService.FORM_DISPLAY_META_TYPE, formDisplayScheme.getMeta().toHashMap());
        }
        //添加元数据信息
        //添加字段信息
        if (formDisplayScheme.getFields() != null && !formDisplayScheme.getFields().isEmpty()) {
            //TODO
            for (FieldDisplay field : formDisplayScheme.getFields()) {

            }
        }
        return formDisplayScheme;
    }

    protected FormDisplay getFormDisplay() {
        return formDisplay;
    }
}
