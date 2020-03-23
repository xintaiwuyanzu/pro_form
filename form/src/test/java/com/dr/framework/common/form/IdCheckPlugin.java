package com.dr.framework.common.form;

import com.dr.framework.common.form.core.plugin.CreateWorkFormPlugin;
import com.dr.framework.core.orm.jdbc.Column;
import com.dr.framework.core.orm.jdbc.Relation;
import com.dr.framework.core.orm.jdbc.TrueOrFalse;

import java.sql.Types;

public class IdCheckPlugin implements CreateWorkFormPlugin {
    @Override
    public void beforeCreate(Relation relation) {
        Column column = new Column(relation.getName(), fieldCode(), "");
        column.setName(fieldName());
        column.setTableName(relation.getName());
        column.setType(Types.VARCHAR);
        column.setSize(100);
        column.setNullAble(TrueOrFalse.FALSE);
        relation.addColumn(column);
    }

    @Override
    public String type() {
        return "gongWen";
    }

    @Override
    public String description() {
        return "检查公文类型的表是否有这些字段";
    }

    public String fieldName() {
        return "流程环节Id";
    }

    public String fieldCode() {
        return "processId";
    }

}
