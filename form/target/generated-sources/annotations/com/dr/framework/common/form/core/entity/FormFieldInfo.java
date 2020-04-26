package com.dr.framework.common.form.core.entity;

import com.dr.framework.core.orm.sql.Column;
import com.dr.framework.core.orm.sql.TableInfo;

import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.List;

public class FormFieldInfo implements TableInfo {
    public static final String TABLE = "formField";
    public static final Column UPDATEDATE = new Column(TABLE, "updateDate", "updateDate", JdbcType.NUMERIC);
    public static final Column FIELDNAME = new Column(TABLE, "fieldName", "fieldName", JdbcType.VARCHAR);
    public static final Column DATAOBJECTID = new Column(TABLE, "dataObjectId", "dataObjectId", JdbcType.VARCHAR);
    public static final Column FIELDCODE = new Column(TABLE, "fieldCode", "fieldCode", JdbcType.VARCHAR);
    public static final Column CREATEPERSON = new Column(TABLE, "createPerson", "createPerson", JdbcType.VARCHAR);
    public static final Column ORDERBY = new Column(TABLE, "order_info", "orderBy", JdbcType.NUMERIC);
    public static final Column DESCRIPTION = new Column(TABLE, "description", "description", JdbcType.VARCHAR);
    public static final Column FIELDORDER = new Column(TABLE, "fieldOrder", "fieldOrder", JdbcType.NUMERIC);
    public static final Column FIELDVALUE = new Column(TABLE, "fieldValue", "fieldValue", JdbcType.VARCHAR);
    public static final Column VERSION = new Column(TABLE, "version", "version", JdbcType.VARCHAR);
    public static final Column FIELDSTATE = new Column(TABLE, "fieldState", "fieldState", JdbcType.VARCHAR);
    public static final Column HISTORYVERSION = new Column(TABLE, "historyVersion", "historyVersion", JdbcType.NUMERIC);
    public static final Column FORMDEFINITIONID = new Column(TABLE, "formDefinitionId", "formDefinitionId", JdbcType.VARCHAR);
    public static final Column ID = new Column(TABLE, "id", "id", JdbcType.VARCHAR);
    public static final Column UPDATEPERSON = new Column(TABLE, "updatePerson", "updatePerson", JdbcType.VARCHAR);
    public static final Column FIELDTYPE = new Column(TABLE, "fieldType", "fieldType", JdbcType.VARCHAR);
    public static final Column CREATEDATE = new Column(TABLE, "createDate", "createDate", JdbcType.NUMERIC);
    public static final Column STATUS = new Column(TABLE, "status_info", "status", JdbcType.VARCHAR);
    public static final Column FIELDLENGTH = new Column(TABLE, "fieldLength", "fieldLength", JdbcType.NUMERIC);

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
        columns.add(FIELDNAME);
        columns.add(DATAOBJECTID);
        columns.add(FIELDCODE);
        columns.add(CREATEPERSON);
        columns.add(ORDERBY);
        columns.add(DESCRIPTION);
        columns.add(FIELDORDER);
        columns.add(FIELDVALUE);
        columns.add(VERSION);
        columns.add(FIELDSTATE);
        columns.add(HISTORYVERSION);
        columns.add(FORMDEFINITIONID);
        columns.add(ID);
        columns.add(UPDATEPERSON);
        columns.add(FIELDTYPE);
        columns.add(CREATEDATE);
        columns.add(STATUS);
        columns.add(FIELDLENGTH);
        return columns;
    }
}
