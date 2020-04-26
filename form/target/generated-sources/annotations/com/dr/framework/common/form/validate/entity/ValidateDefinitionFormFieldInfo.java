package com.dr.framework.common.form.validate.entity;

import com.dr.framework.core.orm.sql.Column;
import com.dr.framework.core.orm.sql.TableInfo;

import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.List;

public class ValidateDefinitionFormFieldInfo implements TableInfo {
    public static final String TABLE = "ValidateDefinitionFormField";
    public static final Column ISNOTNULL = new Column(TABLE, "isNotNull", "isNotNull", JdbcType.NUMERIC);
    public static final Column UPDATEDATE = new Column(TABLE, "updateDate", "updateDate", JdbcType.NUMERIC);
    public static final Column FIELDNAME = new Column(TABLE, "fieldName", "fieldName", JdbcType.VARCHAR);
    public static final Column FIELDCODE = new Column(TABLE, "fieldCode", "fieldCode", JdbcType.VARCHAR);
    public static final Column CUSTOM = new Column(TABLE, "custom", "custom", JdbcType.VARCHAR);
    public static final Column CREATEPERSON = new Column(TABLE, "createPerson", "createPerson", JdbcType.VARCHAR);
    public static final Column ORDERBY = new Column(TABLE, "order_info", "orderBy", JdbcType.NUMERIC);
    public static final Column CHECKCONFIG = new Column(TABLE, "checkConfig", "checkConfig", JdbcType.VARCHAR);
    public static final Column VALIDATEFORMID = new Column(TABLE, "ValidateFormId", "ValidateFormId", JdbcType.VARCHAR);
    public static final Column TEXT3 = new Column(TABLE, "text3", "text3", JdbcType.VARCHAR);
    public static final Column TEXT1 = new Column(TABLE, "text1", "text1", JdbcType.VARCHAR);
    public static final Column TEXT2 = new Column(TABLE, "text2", "text2", JdbcType.VARCHAR);
    public static final Column CHECKCONFIGTHREE = new Column(TABLE, "checkConfigThree", "checkConfigThree", JdbcType.VARCHAR);
    public static final Column FORMDEFINITIONID = new Column(TABLE, "formDefinitionId", "formDefinitionId", JdbcType.VARCHAR);
    public static final Column CHECKCONFIGTWO = new Column(TABLE, "checkConfigTwo", "checkConfigTwo", JdbcType.VARCHAR);
    public static final Column ID = new Column(TABLE, "id", "id", JdbcType.VARCHAR);
    public static final Column UPDATEPERSON = new Column(TABLE, "updatePerson", "updatePerson", JdbcType.VARCHAR);
    public static final Column FIELDTYPE = new Column(TABLE, "fieldType", "fieldType", JdbcType.VARCHAR);
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
        columns.add(ISNOTNULL);
        columns.add(UPDATEDATE);
        columns.add(FIELDNAME);
        columns.add(FIELDCODE);
        columns.add(CUSTOM);
        columns.add(CREATEPERSON);
        columns.add(ORDERBY);
        columns.add(CHECKCONFIG);
        columns.add(VALIDATEFORMID);
        columns.add(TEXT3);
        columns.add(TEXT1);
        columns.add(TEXT2);
        columns.add(CHECKCONFIGTHREE);
        columns.add(FORMDEFINITIONID);
        columns.add(CHECKCONFIGTWO);
        columns.add(ID);
        columns.add(UPDATEPERSON);
        columns.add(FIELDTYPE);
        columns.add(REMARKS);
        columns.add(CREATEDATE);
        columns.add(STATUS);
        return columns;
    }
}
