package com.dr.framework.common.form.display.command;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.display.entity.FormDisplayScheme;
import com.dr.framework.common.form.display.entity.FormDisplaySchemeInfo;
import com.dr.framework.common.form.engine.model.display.FormDisplay;
import com.dr.framework.common.form.display.model.SimpleFormDisplay;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import static com.dr.framework.common.form.display.service.FormDisplayService.DEFAULT_MODIFY_ALL_VERSION;

/**
 * 根据显示方案Id查询的命令
 *
 * @author dr
 */
public class AbstractFormDisplayIdCommand {
    /**
     * 表单显示方案Id
     */
    private final String formDisplayId;
    /**
     * 是否更新所有的版本
     */
    private boolean modifyAllVersion = DEFAULT_MODIFY_ALL_VERSION;

    public AbstractFormDisplayIdCommand(String formDisplayId, boolean modifyAllVersion) {
        this.formDisplayId = formDisplayId;
        this.modifyAllVersion = modifyAllVersion;
    }

    public AbstractFormDisplayIdCommand(String formDisplayId) {
        this.formDisplayId = formDisplayId;
    }

    protected FormDisplay getFormDisplayById(CommandContext context) {
        return getFormDisplayById(context, formDisplayId);
    }


    /**
     * 根据Id获取表单显示方案
     *
     * @param context
     * @param formDisplayId
     * @return
     */
    protected FormDisplay getFormDisplayById(CommandContext context, String formDisplayId) {
        Assert.isTrue(!StringUtils.isEmpty(formDisplayId), "表单显示方案Id不能为空！");
        CommonMapper commonMapper = context.getMapper();
        SimpleFormDisplay simpleFormDisplay = commonMapper.selectOneByQuery(
                buildFormDisplayQuery()
                        .equal(FormDisplaySchemeInfo.ID, formDisplayId)
        );
        Assert.isTrue(simpleFormDisplay != null, "未找到指定的显示方案");

        return simpleFormDisplay;
    }


    protected SqlQuery<SimpleFormDisplay> buildFormDisplayQuery() {
        return SqlQuery.from(FormDisplayScheme.class, false)
                .column()
                .setReturnClass(SimpleFormDisplay.class);
    }

}
