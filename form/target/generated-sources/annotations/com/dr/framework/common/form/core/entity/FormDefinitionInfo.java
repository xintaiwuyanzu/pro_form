package com.dr.framework.common.form.core.entity;

import com.dr.framework.core.orm.sql.Column;
import com.dr.framework.core.orm.sql.TableInfo;

import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.List;

public class FormDefinitionInfo implements TableInfo {
    public static final String TABLE = "FormDefinition";
    public static final Column UPDATEDATE = new Column(TABLE, "updateDate", "updateDate", JdbcType.NUMERIC);
    public static final Column FORMTYPE = new Column(TABLE, "formType", "formType", JdbcType.VARCHAR);
    public static final Column DATAOBJECTID = new Column(TABLE, "dataObjectId", "dataObjectId", JdbcType.VARCHAR);
    public static final Column FORMCODE = new Column(TABLE, "formCode", "formCode", JdbcType.VARCHAR);
    public static final Column CREATEPERSON = new Column(TABLE, "createPerson", "createPerson", JdbcType.VARCHAR);
    public static final Column ORDERBY = new Column(TABLE, "order_info", "orderBy", JdbcType.NUMERIC);
    public static final Column DESCRIPTION = new Column(TABLE, "description", "description", JdbcType.VARCHAR);
    public static final Column VERSION = new Column(TABLE, "version", "version", JdbcType.VARCHAR);
    public static final Column FORMTABLE = new Column(TABLE, "formTable", "formTable", JdbcType.VARCHAR);
    public static final Column FORMORDER = new Column(TABLE, "formOrder", "formOrder", JdbcType.NUMERIC);
    public static final Column FORMNAME = new Column(TABLE, "formName", "formName", JdbcType.VARCHAR);
    public static final Column HISTORYVERSION = new Column(TABLE, "historyVersion", "historyVersion", JdbcType.NUMERIC);
    public static final Column FORMSTATE = new Column(TABLE, "formState", "formState", JdbcType.VARCHAR);
    public static final Column ID = new Column(TABLE, "id", "id", JdbcType.VARCHAR);
    public static final Column ORGANISEID = new Column(TABLE, "organiseId", "organiseId", JdbcType.VARCHAR);
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
        columns.add(UPDATEDATE);
        columns.add(FORMTYPE);
        columns.add(DATAOBJECTID);
        columns.add(FORMCODE);
        columns.add(CREATEPERSON);
        columns.add(ORDERBY);
        columns.add(DESCRIPTION);
        columns.add(VERSION);
        columns.add(FORMTABLE);
        columns.add(FORMORDER);
        columns.add(FORMNAME);
        columns.add(HISTORYVERSION);
        columns.add(FORMSTATE);
        columns.add(ID);
        columns.add(ORGANISEID);
        columns.add(UPDATEPERSON);
        columns.add(CREATEDATE);
        columns.add(STATUS);
        return columns;
    }
}
