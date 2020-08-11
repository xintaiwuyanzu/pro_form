package com.dr.framework.common.form.core.command;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.entity.IdEntity;
import com.dr.framework.common.entity.StatusEntity;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormDefinitionInfo;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.form.core.plugin.CreateWorkFormPlugin;
import com.dr.framework.common.form.core.service.FormNameGenerator;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.util.CacheUtil;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.service.CommonService;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.core.orm.jdbc.Column;
import com.dr.framework.core.orm.jdbc.Relation;
import com.dr.framework.core.orm.jdbc.TrueOrFalse;
import com.dr.framework.core.orm.module.ConfigedRelation;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.sql.Types;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 抽象父类，提供各个命令通用方法
 *
 * @author dr
 */
public abstract class AbstractFormDefinitionCommand {
    protected static final String FORM_NOT_DEFINITION_ERROR = "未查询到指定的表单！";
    protected static final String FORM_CAN_NOT_BE_NULL_ERROR = "表单定义不能为空！";


    protected void saveFormDefinition(CommandContext context, FormDefinition formDefinition) {
        CommonMapper mapper = context.getMapper();
        //保存字段定义
        formDefinition.getFields().forEach(mapper::insert);
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
        Assert.isTrue(formDefinition != null, FORM_CAN_NOT_BE_NULL_ERROR);
        Assert.isTrue(formDefinition.getFields() != null && !formDefinition.getFields().isEmpty(), "表单字段不能为空！");
        //表结构生成器
        DataBaseService dataBaseService = context.getDataBaseService();
        boolean tableExist = tableExist(context, formDefinition);
        boolean multiTable = context.getConfig().multiTableEnable(formDefinition.getFormCode());
        //构建表结构对象
        Relation<Column> configedRelation;
        if (multiTable) {
            Assert.isTrue(!tableExist, "指定的物理表已经存在！");
            configedRelation = newRelation(context, formDefinition);
        } else {
            configedRelation = newRelation(context, formDefinition);
            if (tableExist) {
                //根据编码查询默认版本
                FormDefinition defaultDefinition = (FormDefinition) context.getFormDefinitionService().selectFormDefinitionByCode(formDefinition.getFormCode());
                //如果表存在，过滤增加的列和长度
                Assert.isTrue(defaultDefinition != null, "表存在，但是未查询到默认表单定义：" + formDefinition.getFormCode());
                if (!defaultDefinition.getId().equals(formDefinition.getId())) {
                    Relation<Column> relation = newRelation(context, defaultDefinition);
                    for (Column column : configedRelation.getColumns()) {
                        Column defaultColumn = relation.getColumn(column.getName());
                        if (defaultColumn != null) {
                            Assert.isTrue(column.getType() == defaultColumn.getType(), "单表模式下，不能修改列数据类型");
                            //长度精度使用大的
                            if (column.getSize() < defaultColumn.getSize()) {
                                column.setSize(defaultColumn.getSize());
                            }
                            if (column.getDecimalDigits() < defaultColumn.getDecimalDigits()) {
                                column.setDecimalDigits(defaultColumn.getDecimalDigits());
                            }
                        }
                    }
                }
            }
        }
        //生成表结构
        dataBaseService.updateTable(configedRelation);
        //更新表结构
        CommonMapper mapper = context.getMapper();
        mapper.updateIgnoreNullByQuery(
                SqlQuery.from(FormDefinition.class)
                        .set(FormDefinitionInfo.FORMTABLE, formDefinition.getFormTable())
                        .equal(FormDefinitionInfo.ID, formDefinition.getId())
        );
    }

    /**
     * 判断指定的表名称是否存在
     *
     * @param context
     * @param formDefinition
     * @return
     */
    protected boolean tableExist(CommandContext context, FormDefinition formDefinition) {
        Assert.isTrue(formDefinition != null, FORM_CAN_NOT_BE_NULL_ERROR);
        String tableName = context.getFormNameGenerator().genTableName(formDefinition);
        return context.getDataBaseService().tableExist(tableName, Constants.MODULE_NAME);
    }


    /**
     * 创建表结构
     *
     * @param context
     * @param formDefinition
     * @return
     */
    protected Relation<Column> newRelation(CommandContext context, FormDefinition formDefinition) {
        FormNameGenerator formNameGenerator = context.getFormNameGenerator();
        Assert.isTrue(formDefinition != null, FORM_CAN_NOT_BE_NULL_ERROR);
        ConfigedRelation relation = new ConfigedRelation(true);
        relation.setId(formDefinition.getId());
        relation.setModule(Constants.MODULE_NAME);
        relation.setRemark(formDefinition.getRemarks());
        relation.setName(formNameGenerator.genTableName(formDefinition));

        formDefinition.getFields().forEach(field -> newColumn(relation, formDefinition, field, formNameGenerator));

        Column idColumn = new Column(relation.getName(), IdEntity.ID_COLUMN_NAME, IdEntity.ID_COLUMN_NAME);
        idColumn.setPosition(0);
        idColumn.setType(Types.VARCHAR);
        idColumn.setSize(255);
        idColumn.setNullAble(TrueOrFalse.TRUE);
        relation.addColumn(idColumn);
        relation.addPrimaryKey("", idColumn.getName(), 0);

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
        //如果开启了才需要复制表结构
        if (commandContext.getConfig().multiTableEnable(oldFormDefinition.getFormCode())) {
            //TODO
        }
    }

    /**
     * 删除指定的物理表
     *
     * @param context
     * @param tableName
     */
    protected void removeTable(CommandContext context, String tableName) {
        DataBaseService dataBaseService = context.getDataBaseService();
        dataBaseService.dropTable(tableName, Constants.MODULE_NAME);
    }

    protected void validateFieldBaseInfo(CommandContext context, FieldModel fieldModel) {
        Assert.isTrue(!StringUtils.isEmpty(fieldModel.getFormDefinitionId()), "字段所属表定义不能为空！");
        FormDefinition definition = CacheUtil.getFormDefinitionFromCache(context, fieldModel.getFormDefinitionId());
        validateFieldBaseInfo(definition, fieldModel);
    }

    /**
     * 校验字段基本参数是否正确
     *
     * @param definition
     * @param fieldModel
     */
    protected void validateFieldBaseInfo(FormDefinition definition, FieldModel fieldModel) {
        //类型不能为空
        Assert.notNull(fieldModel.getFieldType(), "字段类型不能为空！");
        //主表外键不能为空
        Set<String> existsFieldNames = definition == null ? Collections.EMPTY_SET : definition.getFieldNames();
        Set<String> existsFieldAlias = definition == null ? Collections.EMPTY_SET : definition.getFieldAlias();

        Set<String> fieldNames = new HashSet<>();

        String fieldCode = fieldModel.getFieldCode();
        //字段名称不能重复
        if (!StringUtils.isEmpty(fieldCode)) {
            Assert.isTrue(!existsFieldNames.contains(fieldCode), "字段名称不能重复！" + fieldCode);
            Assert.isTrue(!existsFieldAlias.contains(fieldCode), "字段名称不能重复！" + fieldCode);
            fieldNames.add(fieldCode);
        }
        //别名不能重复
        Collection<String> fieldAlias = fieldModel.getFieldAlias();
        if (fieldAlias != null) {
            fieldAlias.forEach(a -> {
                Assert.isTrue(!existsFieldNames.contains(a), "字段别名不能重复！" + a);
                Assert.isTrue(!existsFieldAlias.contains(a), "字段别名不能重复！" + a);
                Assert.isTrue(!fieldNames.contains(a), "字段别名不能重复！" + a);
                fieldNames.add(a);
            });
        }
    }

    protected FormField newField(FieldModel fieldModel) {
        FormField formField = new FormField(fieldModel);
        CommonService.bindCreateInfo(formField);
        if (StringUtils.isEmpty(formField.getStatus())) {
            formField.setStatus(StatusEntity.STATUS_ENABLE_STR);
        }
        return formField;
    }

    protected FormDefinition newFormDefinition(CommandContext context, FormModel formModel) {
        Assert.notNull(formModel, FORM_CAN_NOT_BE_NULL_ERROR);
        FormDefinition formDefinition = new FormDefinition(formModel);
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
        if (formDefinition.getVersion() == null) {
            formDefinition.setVersion(Constants.DEFAULT_VERSION);
        }
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
        column.setNullAble(TrueOrFalse.TRUE);
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
        //查询最大版本
        Integer max = getLastFormDefinitionVersion(context, old.getFormCode());
        formDefinition.setVersion(max + 1);

        formDefinition.setFields(
                old.getFields()
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
     * 获取指定表单的最大版本号
     *
     * @param context
     * @param formCode
     * @return
     */
    protected Integer getLastFormDefinitionVersion(CommandContext context, String formCode) {
        Assert.isTrue(!StringUtils.isEmpty(formCode), "表单编码不能为空！");
        FormDefinition max = context.getMapper()
                .selectOneByQuery(
                        SqlQuery.from(FormDefinition.class, false)
                                .column(FormDefinitionInfo.VERSION.max())
                                .equal(FormDefinitionInfo.FORMCODE, formCode)
                );
        return max == null ? Constants.DEFAULT_VERSION : max.getVersion();
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
