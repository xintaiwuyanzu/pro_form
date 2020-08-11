package com.dr.framework.common.form.core.command;


import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.entity.StatusEntity;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormDefinitionInfo;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.entity.FormFieldInfo;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.form.core.query.FormDefinitionQuery;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 删除表单定义数据
 *
 * @author dr
 */
public class FormDefinitionRemoveCommand extends AbstractFormDefinitionIdCommand implements Command<Long> {

    private final boolean dropTable;

    /**
     * 根据特定的表单定义Id删除表单
     *
     * @param formDefinitionId 表单定义Id
     * @param dropTable        是否删除物理表结构
     */
    public FormDefinitionRemoveCommand(String formDefinitionId, boolean dropTable) {
        super(formDefinitionId);
        this.dropTable = dropTable;
    }

    /**
     * 根据表单编码和版本号删除表单
     *
     * @param formCode  表单编码
     * @param version   表单版本，表单版本为null的时候，就是删除该code的所有表单定义
     * @param dropTable 是否删除物理表结构
     */
    public FormDefinitionRemoveCommand(String formCode, Integer version, boolean dropTable) {
        super(formCode, version);
        this.dropTable = dropTable;
    }

    /**
     * 删除表单数据，表单定义
     *
     * @param context
     * @return long
     */
    @Override
    public Long execute(CommandContext context) {
        FormDefinition formDefinition = getFormDefinition(context);
        long count = 0;
        if (formDefinition == null) {
            //如果没查出来表单，code不为空，version为空，则是按照code删除所有的表单定义
            if (!StringUtils.isEmpty(getFormCode()) && getVersion() == null) {
                List<? extends FormModel> formDefinitions = context.getFormDefinitionService()
                        .selectFormDefinitionByQuery(
                                new FormDefinitionQuery()
                                        .codeEqual(getFormCode())
                                        .statusAll()
                                        .versionAll()
                        );
                if (formDefinitions != null) {
                    count = formDefinitions.stream().mapToLong(f -> doRemove(context, (FormDefinition) f)).sum();
                }
            }
        } else {
            count = doRemove(context, formDefinition);
            //如果删除掉的是default版本，则设置最大可用版本为default
            if (formDefinition.isDefault()) {
                CommonMapper mapper = context.getMapper();

                FormDefinition maxForm = mapper.selectOneByQuery(
                        SqlQuery.from(FormDefinition.class, false)
                                .column(FormDefinitionInfo.VERSION.max())
                                .equal(FormDefinitionInfo.FORMCODE, formDefinition.getFormCode())
                                .equal(FormDefinitionInfo.STATUS, StatusEntity.STATUS_ENABLE_STR)
                );
                if (maxForm != null) {
                    //启用最大可用版本为default
                    count += mapper.updateIgnoreNullByQuery(
                            SqlQuery.from(FormDefinition.class)
                                    .set(FormDefinitionInfo.ISDEFAULT, true)
                                    .equal(FormDefinitionInfo.FORMCODE, formDefinition.getFormCode())
                                    .equal(FormDefinitionInfo.VERSION, maxForm.getVersion())
                    );
                }
            }
        }
        return count;

    }

    @Override
    protected FormDefinition getFormDefinition(CommandContext context) {
        //只有明确了表单定义Id或者code和版本的时候，才查询到具体的表单定义
        boolean fullParams = !StringUtils.isEmpty(getFormDefinitionId())
                || (!StringUtils.isEmpty(getFormCode()) && getVersion() != null);
        if (fullParams) {
            return super.getFormDefinition(context);
        } else {
            return null;
        }
    }

    /**
     * 直接执行删除一项表单定义
     *
     * @param context
     * @param formDefinition
     * @return 返回影响数据的条数
     */
    protected Long doRemove(CommandContext context, FormDefinition formDefinition) {
        //删除表里面的数据
        CommonMapper commonMapper = context.getMapper();
        //删除表单定义的数据
        long count = commonMapper.deleteById(FormDefinition.class, formDefinition.getId());
        //删除字段定义的数据
        count += commonMapper.deleteByQuery(
                SqlQuery.from(FormField.class)
                        .equal(FormFieldInfo.FORMDEFINITIONID, formDefinition.getId())
        );
        if (dropTable) {
            FormNameGenerator nameGenerator = context.getFormNameGenerator();
            removeTable(context, nameGenerator.genTableName(formDefinition));
            count++;
        }
        return count;
    }


}
