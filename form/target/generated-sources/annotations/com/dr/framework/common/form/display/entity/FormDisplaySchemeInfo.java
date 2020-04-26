package com.dr.framework.common.form.display.entity;

import com.dr.framework.core.orm.sql.Column;
import com.dr.framework.core.orm.sql.TableInfo;

import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.List;

public class FormDisplaySchemeInfo implements TableInfo {
    public static final String TABLE = "FormDisplayScheme";
    public static final Column SCHEMECODE = new Column(TABLE, "schemeCode", "schemeCode", JdbcType.VARCHAR);
    public static final Column UPDATEDATE = new Column(TABLE, "updateDate", "updateDate", JdbcType.NUMERIC);
    public static final Column SCHEMETYPE = new Column(TABLE, "schemeType", "schemeType", JdbcType.VARCHAR);
    public static final Column SCHEMENAME = new Column(TABLE, "schemeName", "schemeName", JdbcType.VARCHAR);
    public static final Column CREATEPERSON = new Column(TABLE, "createPerson", "createPerson", JdbcType.VARCHAR);
    public static final Column ORDERBY = new Column(TABLE, "order_info", "orderBy", JdbcType.NUMERIC);
    public static final Column FORMDEFINITIONID = new Column(TABLE, "formDefinitionId", "formDefinitionId", JdbcType.VARCHAR);
    public static final Column ID = new Column(TABLE, "id", "id", JdbcType.VARCHAR);
    public static final Column UPDATEPERSON = new Column(TABLE, "updatePerson", "updatePerson", JdbcType.VARCHAR);
    public static final Column CREATEDATE = new Column(TABLE, "createDate", "createDate", JdbcType.NUMERIC);
    public static final Column STATUS = new Column(TABLE, "status_info", "status", JdbcType.VARCHAR);

    @Override
    public String moudle() {
        return  "form";
    }

    @Override
    public String table() {
        return TABLE;
    }

    @Override
    public Column pk() {
        return ID;
    }

    @Override
    public List<Column> columns() {
        List<Column> columns = new ArrayList<>();
        columns.add(SCHEMECODE);
        columns.add(UPDATEDATE);
        columns.add(SCHEMETYPE);
        columns.add(SCHEMENAME);
        columns.add(CREATEPERSON);
        columns.add(ORDERBY);
        columns.add(FORMDEFINITIONID);
        columns.add(ID);
        columns.add(UPDATEPERSON);
        columns.add(CREATEDATE);
        columns.add(STATUS);
        return columns;
    }
}
