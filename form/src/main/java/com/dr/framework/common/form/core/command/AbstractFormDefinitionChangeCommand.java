package com.dr.framework.common.form.core.command;

/**
 * 更新表结构命令
 *
 * @author dr
 */
public abstract class AbstractFormDefinitionChangeCommand extends AbstractFormDefinitionIdCommand {
    /**
     * 是否生成数据库表
     */
    private boolean updateTable;
    /**
     * 更新表结构会创建新的物理表定义
     */
    private boolean copyData;

    public AbstractFormDefinitionChangeCommand(String formDefinitionId, boolean updateTable, boolean copyData) {
        super(formDefinitionId);
        this.updateTable = updateTable;
        this.copyData = copyData;
    }

    public AbstractFormDefinitionChangeCommand(String formCode, Integer version, boolean updateTable, boolean copyData) {
        super(formCode, version);
        this.updateTable = updateTable;
        this.copyData = copyData;
    }

    public boolean isUpdateTable() {
        return updateTable;
    }

    public boolean isCopyData() {
        return copyData;
    }
}
