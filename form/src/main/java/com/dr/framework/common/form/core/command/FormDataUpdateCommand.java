package com.dr.framework.common.form.core.command;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.model.FormRelationWrapper;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.StringUtils;

/**
 * 更新一条表单数据
 *
 * @author dr
 */
public class FormDataUpdateCommand extends FormDataInsertCommand {
    private boolean ignoreNull;

    public FormDataUpdateCommand(FormData formData, boolean ignoreNull) {
        super(formData, false);
        this.ignoreNull = ignoreNull;
    }

    @Override
    protected FormData doModifyTable(CommandContext context, FormRelationWrapper wrapper) {
        CommonMapper mapper = context.getMapper();
        //如果没有主键，则是添加数据
        if (StringUtils.isEmpty(getFormData().getId())) {
            return super.doModifyTable(context, wrapper);
        } else {
            SqlQuery sqlQuery = SqlQuery.from(wrapper.getRelation()).equal(wrapper.idColumn(), getFormData().getId());
            if (mapper.existsByQuery(sqlQuery)) {
                sqlQuery.putAll(getFormData());
                if (ignoreNull) {
                    mapper.updateIgnoreNullByQuery(sqlQuery);
                } else {
                    mapper.updateByQuery(sqlQuery);
                }
                return getFormData();
            } else {
                return super.doModifyTable(context, wrapper);
            }
        }
    }
}
