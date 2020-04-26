package com.dr.framework.common.form.validate.entity;

import com.dr.framework.core.orm.sql.Column;
import com.dr.framework.core.orm.sql.TableInfo;

import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.List;

public class ValidateDefinitionFormInfo implements TableInfo {
    public static final String TABLE = "ValidateDefinitionForm";
    public static final Column UPDATEDATE = new Column(TABLE, "updateDate", "updateDate", JdbcType.NUMERIC);
    public static final Column VALIDATETYPE = new Column(TABLE, "validateType", "validateType", JdbcType.VARCHAR);
    public static final Column CREATEPERSON = new Column(TABLE, "createPerson", "createPerson", JdbcType.VARCHAR);
    public static final Column ORDERBY = new Column(TABLE, "order_info", "orderBy", JdbcType.NUMERIC);
    public static final Column VALIDATECODE = new Column(TABLE, "validateCode", "validateCode", JdbcType.VARCHAR);
    public static final Column DESCRIPTION = new Column(TABLE, "description", "description", JdbcType.VARCHAR);
    public static final Column VALIDATESTATE = new Column(TABLE, "validateState", "validateState", JdbcType.VARCHAR);
    public static final Column VERSION = new Column(TABLE, "version", "version", JdbcType.VARCHAR);
    public static final Column VALIDATENAME = new Column(TABLE, "validateName", "validateName", JdbcType.VARCHAR);
    public static final Column VALIDATEORDER = new Column(TABLE, "validateOrder", "validateOrder", JdbcType.NUMERIC);
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
        columns.add(UPDATEDATE);
        columns.add(VALIDATETYPE);
        columns.add(CREATEPERSON);
        columns.add(ORDERBY);
        columns.add(VALIDATECODE);
        columns.add(DESCRIPTION);
        columns.add(VALIDATESTATE);
        columns.add(VERSION);
        columns.add(VALIDATENAME);
        columns.add(VALIDATEORDER);
        columns.add(FORMDEFINITIONID);
        columns.add(ID);
        columns.add(UPDATEPERSON);
        columns.add(CREATEDATE);
        columns.add(STATUS);
        return columns;
    }
}
