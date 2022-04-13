package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 更新表结构命令
 *
 * @author dr
 */
public abstract class AbstractFormDefinitionChangeCommand extends AbstractFormDefinitionIdCommand {
    /**
     * 是否生成数据库表
     */
    private final boolean updateTable;
    /**
     * 更新表结构会创建新的物理表定义
     */
    private final boolean copyData;
    /**
     * 是否更新版本号
     */
    private boolean updateVersion = true;
    /**
     * 字段定义
     */
    private final List<FieldModel> fieldModelList;


    public AbstractFormDefinitionChangeCommand(String formDefinitionId, boolean updateTable, boolean copyData, FieldModel... fieldModel) {
        super(formDefinitionId);
        this.updateTable = updateTable;
        this.copyData = copyData;
        this.fieldModelList = Arrays.asList(fieldModel);
    }

    public AbstractFormDefinitionChangeCommand(String formCode, Integer version, boolean updateTable, boolean copyData, FieldModel... fieldModel) {
        super(formCode, version);
        this.updateTable = updateTable;
        this.copyData = copyData;
        this.fieldModelList = Arrays.asList(fieldModel);
    }

    public Collection<FormField> execute(CommandContext context) {
        //获取原来的表单定义
        FormDefinition old = getFormDefinition(context);
        Assert.isTrue(old != null, FORM_NOT_DEFINITION_ERROR);
        Assert.isTrue(!ObjectUtils.isEmpty(getField()), "更新的字段不能为空");
        if (isUpdateVersion()) {
            return executeWithUpdateVersion(context, old);
        } else {
            return executeWithOutUpdateVersion(context, old);
        }
    }

    protected abstract Collection<FormField> executeWithUpdateVersion(CommandContext context, FormDefinition old);

    protected abstract List<FormField> executeWithOutUpdateVersion(CommandContext context, FormDefinition old);

    public List<FieldModel> getField() {
        return fieldModelList;
    }

    public boolean isUpdateVersion() {
        return updateVersion;
    }

    public void setUpdateVersion(boolean updateVersion) {
        this.updateVersion = updateVersion;
    }

    public boolean isUpdateTable() {
        return updateTable;
    }

    public boolean isCopyData() {
        return copyData;
    }
}
