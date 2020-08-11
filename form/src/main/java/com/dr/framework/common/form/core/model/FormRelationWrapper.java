package com.dr.framework.common.form.core.model;

import com.dr.framework.common.entity.IdEntity;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.core.orm.jdbc.Column;
import com.dr.framework.core.orm.jdbc.Relation;

/**
 * 表单对象对应的数据库结构基本信息
 *
 * @author dr
 */
public interface FormRelationWrapper {
    /**
     * 获取表单对象
     *
     * @return
     */
    FormModel getForm();

    /**
     * 获取数据库对象
     *
     * @return
     */
    Relation getRelation();

    /**
     * 获取运转上下文
     *
     * @return
     */
    CommandContext getContext();

    /**
     * 获取数据库列
     *
     * @param fieldCodeOrAlias
     * @return
     */
    Column getColumn(String fieldCodeOrAlias);


    /**
     * 获取主键列
     *
     * @return
     */
    default Column idColumn() {
        return getColumn(IdEntity.ID_COLUMN_NAME);
    }

}
