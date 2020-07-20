package com.dr.framework.common.form.core.command;

import com.dr.framework.common.form.core.model.FormData;
import com.dr.framework.common.form.core.model.FormRelationWrapper;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 根据主键查询
 * 查询一条表单数据
 *
 * @author dr
 */
public class FormDataSelectOneCommand extends AbstractFormDataCommand<FormData> implements Command<FormData> {
    /**
     * 表单数据Id
     */
    private String formDataId;

    public FormDataSelectOneCommand(String formDefinitionId, boolean autoCheck, String formDataId) {
        super(formDefinitionId, autoCheck);
        this.formDataId = formDataId;
    }

    public FormDataSelectOneCommand(String formCode, Integer version, boolean autoCheck, String formDataId) {
        super(formCode, version, autoCheck);
        this.formDataId = formDataId;
    }

    @Override
    public FormData execute(CommandContext context) {
        Assert.isTrue(!StringUtils.isEmpty(formDataId), "表单数据Id不能为空!");
        return super.execute(context);
    }

    @Override
    protected FormData doModifyTable(CommandContext context, FormRelationWrapper wrapper) {
        HashMap<String, Serializable> data =
                context.getMapper()
                        .selectOneByQuery(
                                SqlQuery.from(wrapper.getRelation())
                                        .equal(wrapper.idColumn(), formDataId)
                                        .setReturnClass(HashMap.class)
                        );
        return mapFormData(wrapper, data);
    }

    public String getFormDataId() {
        return formDataId;
    }
}
