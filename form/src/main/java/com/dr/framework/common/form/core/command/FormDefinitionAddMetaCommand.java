package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.impl.command.SetMetaCommand;
import com.dr.framework.common.form.engine.model.core.FormModel;
import org.springframework.util.Assert;

import java.util.Map;

import static com.dr.framework.common.form.core.service.FormDefinitionService.FORM_DEFINITION_META_TYPE;

/**
 * 表单添加元数据
 *
 * @author dr
 */
public class FormDefinitionAddMetaCommand extends AbstractFormDefinitionIdCommand implements SetMetaCommand {
    private Map<String, String> metas;

    public FormDefinitionAddMetaCommand(String formDefinitionId, Map<String, String> metas) {
        super(formDefinitionId);
        this.metas = metas;
    }

    public FormDefinitionAddMetaCommand(String formCode, Integer version, Map<String, String> metas) {
        super(formCode, version);
        this.metas = metas;
    }

    @Override
    public Map<String, String> getMetaMap() {
        return metas;
    }

    @Override
    public String getRefId(CommandContext context) {
        FormModel formModel = getFormDefinition(context);
        Assert.isTrue(formModel != null, "未查询到指定的表单！");
        return formModel.getId();
    }

    @Override
    public String getRefType(CommandContext context) {
        return FORM_DEFINITION_META_TYPE;
    }
}
