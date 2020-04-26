package com.dr.framework.common.form.child.entity;

import com.dr.framework.core.orm.sql.Column;
import com.dr.framework.core.orm.sql.TableInfo;

import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.List;

public class SubFormDefinitionInfo implements TableInfo {
    public static final String TABLE = "SubForm";
    public static final Column UPDATEDATE = new Column(TABLE, "updateDate", "updateDate", JdbcType.NUMERIC);
    public static final Column DATAOBJECTID = new Column(TABLE, "dataObjectId", "dataObjectId", JdbcType.VARCHAR);
    public static final Column CREATEPERSON = new Column(TABLE, "createPerson", "createPerson", JdbcType.VARCHAR);
    public static final Column ORDERBY = new Column(TABLE, "order_info", "orderBy", JdbcType.NUMERIC);
    public static final Column DESCRIPTION = new Column(TABLE, "description", "description", JdbcType.VARCHAR);
    public static final Column SUBFORMTYPE = new Column(TABLE, "subFormType", "subFormType", JdbcType.VARCHAR);
    public static final Column VERSION = new Column(TABLE, "version", "version", JdbcType.VARCHAR);
    public static final Column FORMORDER = new Column(TABLE, "formOrder", "formOrder", JdbcType.NUMERIC);
    public static final Column SUBFORMTABLE = new Column(TABLE, "subFormTable", "subFormTable", JdbcType.VARCHAR);
    public static final Column HISTORYVERSION = new Column(TABLE, "historyVersion", "historyVersion", JdbcType.NUMERIC);
    public static final Column FORMDEFINITIONID = new Column(TABLE, "formDefinitionId", "formDefinitionId", JdbcType.VARCHAR);
    public static final Column SUBFORMSTATE = new Column(TABLE, "subFormState", "subFormState", JdbcType.VARCHAR);
    public static final Column ID = new Column(TABLE, "id", "id", JdbcType.VARCHAR);
    public static final Column UPDATEPERSON = new Column(TABLE, "updatePerson", "updatePerson", JdbcType.VARCHAR);
    public static final Column SUBFORMNAME = new Column(TABLE, "subFormName", "subFormName", JdbcType.VARCHAR);
    public static final Column CREATEDATE = new Column(TABLE, "createDate", "createDate", JdbcType.NUMERIC);
    public static final Column STATUS = new Column(TABLE, "status_info", "status", JdbcType.VARCHAR);
    public static final Column SUBFORMCODE = new Column(TABLE, "subFormCode", "subFormCode", JdbcType.VARCHAR);

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
        columns.add(DATAOBJECTID);
        columns.add(CREATEPERSON);
        columns.add(ORDERBY);
        columns.add(DESCRIPTION);
        columns.add(SUBFORMTYPE);
        columns.add(VERSION);
        columns.add(FORMORDER);
        columns.add(SUBFORMTABLE);
        columns.add(HISTORYVERSION);
        columns.add(FORMDEFINITIONID);
        columns.add(SUBFORMSTATE);
        columns.add(ID);
        columns.add(UPDATEPERSON);
        columns.add(SUBFORMNAME);
        columns.add(CREATEDATE);
        columns.add(STATUS);
        columns.add(SUBFORMCODE);
        return columns;
    }
}
