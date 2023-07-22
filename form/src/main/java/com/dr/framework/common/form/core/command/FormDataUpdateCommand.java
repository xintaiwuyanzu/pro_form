package com.dr.framework.common.form.core.command;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.model.FormRelationWrapper;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.Column;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 更新一条表单数据
 *
 * @author dr
 */
public class FormDataUpdateCommand extends FormDataInsertCommand {
    private final boolean ignoreNull;

    public FormDataUpdateCommand(FormData formData, boolean ignoreNull) {
        super(formData, false);
        this.ignoreNull = ignoreNull;
    }

    @Override
    protected FormData doModifyData(CommandContext context, FormRelationWrapper wrapper) {
        CommonMapper mapper = context.getMapper();
        //如果没有主键，则是添加数据
        if (StringUtils.isEmpty(getFormData().getId())) {
            return super.doModifyData(context, wrapper);
        } else {
            SqlQuery sqlQuery = SqlQuery.from(wrapper.getRelation()).equal(wrapper.idColumn(), getFormData().getId());
            if (mapper.existsByQuery(sqlQuery)) {
                FormData formData = getFormData();
                wrapper.getRelation().getColumns()
                        .forEach(c -> {
                            Column column = (Column) c;
                            String name = column.getName();
                            if (formData.containsKey(name)) {
                                sqlQuery.set(column, (Serializable) formData.get(null == column.getAlias() ? name : column.getAlias().replace("\"", "")));
                            }
                        });
                if (ignoreNull) {
                    mapper.updateIgnoreNullByQuery(sqlQuery);
                } else {
                    mapper.updateByQuery(sqlQuery);
                }
                mapFormData(wrapper, getFormData());
                return getFormData();
            } else {
                return super.doModifyData(context, wrapper);
            }
        }
    }
}
