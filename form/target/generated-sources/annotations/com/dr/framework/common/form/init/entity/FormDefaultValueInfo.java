package com.dr.framework.common.form.init.entity;

import com.dr.framework.core.orm.sql.Column;
import com.dr.framework.core.orm.sql.TableInfo;

import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.List;

public class FormDefaultValueInfo implements TableInfo {
    public static final String TABLE = "FormDefaultValue";
    public static final Column LINKCODE = new Column(TABLE, "linkCode", "linkCode", JdbcType.VARCHAR);
    public static final Column UPDATEDATE = new Column(TABLE, "updateDate", "updateDate", JdbcType.NUMERIC);
    public static final Column DEFAULTSTATE = new Column(TABLE, "defaultState", "defaultState", JdbcType.VARCHAR);
    public static final Column CREATEPERSON = new Column(TABLE, "createPerson", "createPerson", JdbcType.VARCHAR);
    public static final Column ORDERBY = new Column(TABLE, "order_info", "orderBy", JdbcType.NUMERIC);
    public static final Column DESCRIPTION = new Column(TABLE, "description", "description", JdbcType.VARCHAR);
    public static final Column VERSION = new Column(TABLE, "version", "version", JdbcType.VARCHAR);
    public static final Column LINKNAME = new Column(TABLE, "linkName", "linkName", JdbcType.VARCHAR);
    public static final Column DEFAULTTYPE = new Column(TABLE, "defaultType", "defaultType", JdbcType.VARCHAR);
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
        columns.add(LINKCODE);
        columns.add(UPDATEDATE);
        columns.add(DEFAULTSTATE);
        columns.add(CREATEPERSON);
        columns.add(ORDERBY);
        columns.add(DESCRIPTION);
        columns.add(VERSION);
        columns.add(LINKNAME);
        columns.add(DEFAULTTYPE);
        columns.add(FORMDEFINITIONID);
        columns.add(ID);
        columns.add(UPDATEPERSON);
        columns.add(CREATEDATE);
        columns.add(STATUS);
        return columns;
    }
}
