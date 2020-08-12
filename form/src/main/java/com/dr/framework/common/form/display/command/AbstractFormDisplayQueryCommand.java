package com.dr.framework.common.form.display.command;

import com.dr.framework.common.form.display.entity.FormDisplayScheme;
import com.dr.framework.common.form.display.entity.FormDisplaySchemeInfo;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.form.engine.model.display.FormDisplay;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 查询显示方案列表
 *
 * @author dr
 */
public abstract class AbstractFormDisplayQueryCommand extends AbstractFormDisplayCommand {

    /**
     * 表单定义Id
     */
    private String formDefinitionId;
    /**
     * 表单定义编码
     */
    private String formDefinitionCode;
    /**
     * 表单定义版本
     */
    private Integer version;

    /**
     * 显示类型
     */
    private String displayType;


    public AbstractFormDisplayQueryCommand(String formDefinitionId, String displayType) {
        this.formDefinitionId = formDefinitionId;
        this.displayType = displayType;
    }

    public AbstractFormDisplayQueryCommand(String formDefinitionCode, Integer version, String displayType) {
        this.formDefinitionCode = formDefinitionCode;
        this.version = version;
        this.displayType = displayType;
    }

    protected FormDisplayScheme selectOne(CommandContext commandContext) {
        FormDisplay formDisplay = commandContext.getMapper().selectOneByQuery(buildSqlQuery(commandContext));
        Assert.notNull(formDisplay, "未查询到指定的显示方案");
        return getFormDisplayById(commandContext, formDisplay.getId());
    }

    protected List<FormDisplay> selectList(CommandContext commandContext) {
        return commandContext.getMapper()
                .selectByQuery(buildSqlQuery(commandContext))
                .stream()
                .map(f -> getFormDisplayById(commandContext, f.getId()))
                .collect(Collectors.toList());
    }

    protected SqlQuery<FormDisplayScheme> buildSqlQuery(CommandContext context) {
        SqlQuery<FormDisplayScheme> sqlQuery = SqlQuery.from(FormDisplayScheme.class, false)
                .column(FormDisplaySchemeInfo.ID)
                .orderBy(FormDisplaySchemeInfo.VERSION);
        FormModel formModel = getFormDefinition(context);

        sqlQuery.equal(FormDisplaySchemeInfo.FORMDEFINITIONID, formModel.getId());
        if (!StringUtils.isEmpty(displayType)) {
            sqlQuery.equal(FormDisplaySchemeInfo.TYPE, displayType);
        }
        return sqlQuery;
    }


    protected FormModel getFormDefinition(CommandContext context) {
        FormModel formDefinition;
        if (StringUtils.isEmpty(formDefinitionId)) {
            formDefinition = getFormDefinitionByCodeAndVersion(context, formDefinitionCode, version);
        } else {
            formDefinition = getFormDefinitionById(context, formDefinitionId);
        }
        Assert.notNull(formDefinition, "未查询到指定的表单，请检查查询条件！");
        return formDefinition;
    }


}
