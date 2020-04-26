package com.dr.framework.common.form.init.entity;

import com.dr.framework.core.orm.sql.Column;
import com.dr.framework.core.orm.sql.TableInfo;

import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.List;

public class ProcessTypeInfo implements TableInfo {
    public static final String TABLE = "ProcessType";
    public static final Column UPDATEDATE = new Column(TABLE, "updateDate", "updateDate", JdbcType.NUMERIC);
    public static final Column TYPEDESCRIBE = new Column(TABLE, "typeDescribe", "typeDescribe", JdbcType.VARCHAR);
    public static final Column TYPEORDER = new Column(TABLE, "typeOrder", "typeOrder", JdbcType.NUMERIC);
    public static final Column TYPENAME = new Column(TABLE, "typeName", "typeName", JdbcType.VARCHAR);
    public static final Column CREATEPERSON = new Column(TABLE, "createPerson", "createPerson", JdbcType.VARCHAR);
    public static final Column ORDERBY = new Column(TABLE, "order_info", "orderBy", JdbcType.NUMERIC);
    public static final Column TYPECODE = new Column(TABLE, "typeCode", "typeCode", JdbcType.VARCHAR);
    public static final Column TYPEBUILTIN = new Column(TABLE, "typeBuiltIn", "typeBuiltIn", JdbcType.NUMERIC);
    public static final Column ID = new Column(TABLE, "id", "id", JdbcType.VARCHAR);
    public static final Column ORGANISEID = new Column(TABLE, "organiseId", "organiseId", JdbcType.VARCHAR);
    public static final Column UPDATEPERSON = new Column(TABLE, "updatePerson", "updatePerson", JdbcType.VARCHAR);
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
        columns.add(TYPEDESCRIBE);
        columns.add(TYPEORDER);
        columns.add(TYPENAME);
        columns.add(CREATEPERSON);
        columns.add(ORDERBY);
        columns.add(TYPECODE);
        columns.add(TYPEBUILTIN);
        columns.add(ID);
        columns.add(ORGANISEID);
        columns.add(UPDATEPERSON);
        columns.add(REMARKS);
        columns.add(CREATEDATE);
        columns.add(STATUS);
        return columns;
    }
}
