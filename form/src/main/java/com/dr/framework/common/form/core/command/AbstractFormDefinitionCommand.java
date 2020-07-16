package com.dr.framework.common.form.core.command;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.entity.IdEntity;
import com.dr.framework.common.entity.StatusEntity;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormDefinitionInfo;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.model.Field;
import com.dr.framework.common.form.core.model.Form;
import com.dr.framework.common.form.core.plugin.CreateWorkFormPlugin;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.CacheUtil;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.service.CommonService;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.jdbc.Column;
import com.dr.framework.core.orm.jdbc.Relation;
import com.dr.framework.core.orm.module.ConfigedRelation;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.sql.Types;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 抽象父类，提供各个命令通用方法
 */
public abstract class AbstractFormDefinitionCommand<T> implements Command<T> {

    protected void saveFormDefinition(CommandContext context, FormDefinition formDefinition) {
        CommonMapper mapper = context.getMapper();
        //保存字段定义
        formDefinition.getFormFieldList().forEach(f -> mapper.insert(f));
        //保存定义数据
        mapper.insert(formDefinition);
        //更新其他的默认表单为非默认
        mapper.updateIgnoreNullByQuery(
                SqlQuery.from(FormDefinition.class)
                        .set(FormDefinitionInfo.ISDEFAULT, false)
                        .equal(FormDefinitionInfo.FORMCODE, formDefinition.getFormCode())
                        .notEqual(FormDefinitionInfo.ID, formDefinition.getId())
        );
        CacheUtil.removeCache(context, formDefinition.getId());
    }

    /**
     * 直接执行创建表结构
     *
     * @param context
     * @param formDefinition
     */
    protected void createTable(CommandContext context, FormDefinition formDefinition) {
        Assert.isTrue(formDefinition != null, "表单定义不能为空！");
        Assert.isTrue(formDefinition.getFormFieldList() != null && !formDefinition.getFormFieldList().isEmpty(), "表单字段不能为空！");

        //表结构生成器
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        //表名称生成器
        FormNameGenerator formNameGenerator = context.getApplicationContext().getBean(FormNameGenerator.class);
        //构建表结构对象
        Relation configedRelation = newRelation(context, formNameGenerator, formDefinition);
        //生成表结构
        dataBaseService.updateTable(configedRelation);

        //更新表结构
        CommonMapper mapper = context.getMapper();
        mapper.updateIgnoreNullByQuery(SqlQuery.from(FormDefinition.class).set(FormDefinitionInfo.FORMTABLE, formDefinition.getFormTable()));
    }

    /**
     * 创建表结构
     *
     * @param context
     * @param formNameGenerator
     * @param formDefinition
     * @return
     */
    protected Relation newRelation(CommandContext context, FormNameGenerator formNameGenerator, FormDefinition formDefinition) {
        Assert.isTrue(formDefinition != null, "表单对象不能为空");
        ConfigedRelation relation = new ConfigedRelation(true);
        relation.setId(formDefinition.getId());
        relation.setModule(Constants.MODULE_NAME);
        relation.setRemark(formDefinition.getRemarks());
        relation.setName(formNameGenerator.genTableName(formDefinition));

        formDefinition.getFormFieldList().forEach(field -> newColumn(relation, formDefinition, field, formNameGenerator));

        Column idColumn = new Column(relation.getName(), IdEntity.ID_COLUMN_NAME, IdEntity.ID_COLUMN_NAME);
        idColumn.setPosition(0);
        idColumn.setType(Types.VARCHAR);
        idColumn.setSize(255);
        relation.addColumn(idColumn);
        relation.addPrimaryKey("pk", idColumn.getName(), 0);

        //通过插件拦截创建表结构行为
        Map<String, CreateWorkFormPlugin> workFormPluginMap = context.getApplicationContext().getBeansOfType(CreateWorkFormPlugin.class);
        for (String beanName : workFormPluginMap.keySet()) {
            CreateWorkFormPlugin plugin = workFormPluginMap.get(beanName);
            if (formDefinition.getFormType().equals(plugin.type())) {
                plugin.beforeCreate(relation);
            }
        }
        return relation;
    }


    /**
     * 直接执行创建表结构
     *
     * @param commandContext
     * @param oldFormDefinition 旧表
     * @param newFormDefinition 新表
     */
    protected void copyTable(CommandContext commandContext, FormDefinition oldFormDefinition, FormDefinition newFormDefinition) {
        //TODO
    }

    /**
     * 删除指定的物理表
     *
     * @param context
     * @param tableName
     */
    protected void removeTable(CommandContext context, String tableName) {
        DataBaseService dataBaseService = context.getApplicationContext().getBean(DataBaseService.class);
        dataBaseService.dropTable(tableName, Constants.MODULE_NAME);
    }

    protected void validateFieldBaseInfo(CommandContext context, Field field) {
        Assert.isTrue(!StringUtils.isEmpty(field.getFormDefinitionId()), "字段所属表定义不能为空！");
        FormDefinition definition = CacheUtil.getFormDefinitionFromCache(context, field.getFormDefinitionId());
        validateFieldBaseInfo(definition, field);
    }

    /**
     * 校验字段基本参数是否正确
     *
     * @param definition
     * @param field
     */
    protected void validateFieldBaseInfo(FormDefinition definition, Field field) {
        //类型不能为空
        Assert.notNull(field.getFieldType(), "字段类型不能为空！");
        //主表外键不能为空
        Set<String> existsFieldNames = definition == null ? Collections.EMPTY_SET : definition.getFieldNames();
        Set<String> existsFieldAlias = definition == null ? Collections.EMPTY_SET : definition.getFieldAlias();

        Set<String> fieldNames = new HashSet<>();

        String fieldCode = field.getFieldCode();
        //字段名称不能重复
        if (!StringUtils.isEmpty(fieldCode)) {
            Assert.isTrue(!existsFieldNames.contains(fieldCode), "字段名称不能重复！" + fieldCode);
            Assert.isTrue(!existsFieldAlias.contains(fieldCode), "字段名称不能重复！" + fieldCode);
            fieldNames.add(fieldCode);
        }
        //别名不能重复
        Collection<String> fieldAlias = field.getFieldAlias();
        if (fieldAlias != null) {
            fieldAlias.forEach(a -> {
                Assert.isTrue(!existsFieldNames.contains(a), "字段别名不能重复！" + a);
                Assert.isTrue(!existsFieldAlias.contains(a), "字段别名不能重复！" + a);
                Assert.isTrue(!fieldNames.contains(a), "字段别名不能重复！" + a);
                fieldNames.add(a);
            });
        }
    }

    protected FormField newField(Field field) {
        FormField formField = new FormField(field);
        CommonService.bindCreateInfo(formField);
        return formField;
    }

    protected FormDefinition newFormDefinition(CommandContext context, Form form) {
        Assert.notNull(form, "表单对象不能为空！");
        FormDefinition formDefinition = new FormDefinition(form);
        CommonService.bindCreateInfo(formDefinition);
        return formDefinition;
    }


    /**
     * 填充默认表单定义
     *
     * @param formDefinition
     */
    protected void fillFormDefinition(FormDefinition formDefinition) {
        //类型不能为空
        if (StringUtils.isEmpty(formDefinition.getFormType())) {
            formDefinition.setFormType("default");
        }
        //状态格式
        if (!StringUtils.isEmpty(formDefinition.getStatus())) {
            Assert.isTrue(validateStatus(formDefinition.getStatus()), "表单状态格式不正确！");
        } else {
            formDefinition.setStatus(StatusEntity.STATUS_ENABLE_STR);
        }
        CommonService.bindCreateInfo(formDefinition);
    }

    protected boolean validateStatus(String status) {
        return StatusEntity.STATUS_ENABLE_STR.equalsIgnoreCase(status) || StatusEntity.STATUS_DISABLE_STR.equalsIgnoreCase(status);
    }

    /**
     * @param relation
     * @param formDefinition
     * @param field
     * @param generate
     * @return
     */
    protected void newColumn(ConfigedRelation relation, FormDefinition formDefinition, FormField field, FormNameGenerator generate) {
        String columnName = generate.genFieldName(formDefinition, field);

        Column column = new Column(relation.getName(), columnName, columnName);
        column.setType(field.getFieldType().getSqlType());
        column.setSize(field.getFieldLength());
        column.setDecimalDigits(field.getFieldScale());
        column.setPosition(field.getFieldOrder());
        relation.addColumn(column);
    }

    /**
     * 根据旧的表单定义复制创建新的表单定义
     *
     * @param context
     * @param old
     * @return
     */
    protected FormDefinition copyFormDefinition(CommandContext context, FormDefinition old) {
        FormDefinition formDefinition = new FormDefinition(old);
        CommonService.bindCreateInfo(formDefinition);
        formDefinition.setId(UUID.randomUUID().toString());
        formDefinition.setVersion(old.getVersion() + 1);

        formDefinition.setFormFieldList(
                old.getFormFieldList()
                        .stream()
                        .map(f -> {
                            FormField formField = newField(f);
                            formField.setId(UUID.randomUUID().toString());
                            formField.setVersion(formDefinition.getVersion());
                            formField.setFormDefinitionId(formDefinition.getId());
                            CommonService.bindCreateInfo(formField);
                            return formField;
                        })
                        .sorted()
                        .collect(Collectors.toList())
        );
        return formDefinition;
    }

    /**
     * 判断指定的版本和code表单是否存在
     *
     * @param context
     * @param formCode
     * @param version
     * @return
     */
    protected boolean existByCodeAndVersion(CommandContext context, String formCode, Integer version) {
        if (!StringUtils.isEmpty(formCode)) {
            CommonMapper commonMapper = context.getMapper();
            SqlQuery sqlQuery = SqlQuery.from(FormDefinition.class);
            buildCodeAndVersionSqlQuery(sqlQuery, formCode, version);
            return commonMapper.existsByQuery(sqlQuery);
        }
        return false;
    }


    protected FormDefinition getFormDefinitionByCodeAndVersion(CommandContext context, String formCode, Integer version) {
        if (!StringUtils.isEmpty(formCode)) {
            CommonMapper commonMapper = context.getMapper();
            SqlQuery<FormDefinition> sqlQuery = SqlQuery.from(FormDefinition.class, false)
                    .column(FormDefinitionInfo.ID);
            buildCodeAndVersionSqlQuery(sqlQuery, formCode, version);
            FormDefinition formDefinition = commonMapper.selectOneByQuery(sqlQuery);
            if (formDefinition != null) {
                return CacheUtil.getFormDefinitionFromCache(context, formDefinition.getId());
            }
        }
        return null;
    }

    private void buildCodeAndVersionSqlQuery(SqlQuery<FormDefinition> sqlQuery, String formCode, Integer version) {
        sqlQuery.equal(FormDefinitionInfo.FORMCODE, formCode);
        if (version == null) {
            sqlQuery.equal(FormDefinitionInfo.ISDEFAULT, true);
        } else {
            sqlQuery.equal(FormDefinitionInfo.VERSION, version);
        }
    }

}
