package com.dr.framework.common.form.init.entity;

import com.dr.framework.core.orm.sql.Column;
import com.dr.framework.core.orm.sql.TableInfo;

import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.List;

public class FieldDefaultValueInfo implements TableInfo {
    public static final String TABLE = "FieldDefaultValue";
    public static final Column UPDATEDATE = new Column(TABLE, "updateDate", "updateDate", JdbcType.NUMERIC);
    public static final Column FIELDNAME = new Column(TABLE, "fieldName", "fieldName", JdbcType.VARCHAR);
    public static final Column FIELDCODE = new Column(TABLE, "fieldCode", "fieldCode", JdbcType.VARCHAR);
    public static final Column DEFAULTVALUE = new Column(TABLE, "defaultValue", "defaultValue", JdbcType.VARCHAR);
    public static final Column CUSTOM = new Column(TABLE, "custom", "custom", JdbcType.VARCHAR);
    public static final Column CREATEPERSON = new Column(TABLE, "createPerson", "createPerson", JdbcType.VARCHAR);
    public static final Column ORDERBY = new Column(TABLE, "order_info", "orderBy", JdbcType.NUMERIC);
    public static final Column FORMDEFINITIONID = new Column(TABLE, "formDefinitionId", "formDefinitionId", JdbcType.VARCHAR);
    public static final Column ID = new Column(TABLE, "id", "id", JdbcType.VARCHAR);
    public static final Column UPDATEPERSON = new Column(TABLE, "updatePerson", "updatePerson", JdbcType.VARCHAR);
    public static final Column FIELDTYPE = new Column(TABLE, "fieldType", "fieldType", JdbcType.VARCHAR);
    public static final Column FORMDEFAULTVALUEID = new Column(TABLE, "formDefaultValueId", "formDefaultValueId", JdbcType.VARCHAR);
    public static final Column REMARKS = new Column(TABLE, "remarks", "remarks", JdbcType.VARCHAR);
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
        columns.add(FIELDNAME);
        columns.add(FIELDCODE);
        columns.add(DEFAULTVALUE);
        columns.add(CUSTOM);
        columns.add(CREATEPERSON);
        columns.add(ORDERBY);
        columns.add(FORMDEFINITIONID);
        columns.add(ID);
        columns.add(UPDATEPERSON);
        columns.add(FIELDTYPE);
        columns.add(FORMDEFAULTVALUEID);
        columns.add(REMARKS);
        columns.add(CREATEDATE);
        columns.add(STATUS);
        return columns;
    }
}
