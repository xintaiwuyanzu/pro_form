package com.dr.framework.common.form.core.service.impl;

import com.dr.framework.common.config.model.MetaMap;
import com.dr.framework.common.entity.IdEntity;
import com.dr.framework.common.entity.StatusEntity;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.core.service.FormDefinitionTypeProvider;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.engine.model.core.FieldType;
import com.dr.framework.common.form.util.Constants;
import com.dr.framework.common.service.DataBaseService;
import com.dr.framework.common.service.DefaultDataBaseService;
import com.dr.framework.core.orm.database.Dialect;
import com.dr.framework.core.orm.database.tools.AnnotationTableReaderUtil;
import com.dr.framework.core.orm.jdbc.Column;
import com.dr.framework.core.orm.module.EntityRelation;
import org.springframework.context.support.ApplicationObjectSupport;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 抽象工具父类，通过声明 {@link #getEntityClass()}带有{@link com.dr.framework.core.orm.annotations.Table}
 * <p>
 * 注解的实体类，从注解中读取默认字段
 *
 * @author dr
 */
public abstract class AbstractEntityFormDefinitionTypeProvider extends ApplicationObjectSupport implements FormDefinitionTypeProvider {
    private List<FieldModel> fieldModels;

    @Override
    public Collection<FieldModel> getInitFields() {
        if (fieldModels == null) {
            Class entityClazz = getEntityClass();
            synchronized (entityClazz) {
                if (fieldModels == null) {
                    readFieldFromClass(entityClazz);
                }
            }
        }
        return fieldModels;
    }


    /**
     * 从实体类上读取字段类型注解
     *
     * @param entityClazz
     */
    protected void readFieldFromClass(Class entityClazz) {
        DefaultDataBaseService dataBaseService = (DefaultDataBaseService) getApplicationContext().getBean(DataBaseService.class);
        Dialect dialect = dataBaseService.getDataBaseMetaDataByModuleName(Constants.MODULE_NAME).getDialect();
        EntityRelation relation = new EntityRelation(false);
        AnnotationTableReaderUtil.readColumnInfo(relation, entityClazz, dialect);
        fieldModels = relation.getColumns()
                .stream().filter(f -> !f.getName().equalsIgnoreCase(IdEntity.ID_COLUMN_NAME))
                .map(ColumnFieldModel::new)
                .collect(Collectors.toList());
    }

    public abstract Class getEntityClass();

    static class ColumnFieldModel implements FieldModel {
        private final Column column;

        public ColumnFieldModel(Column column) {
            this.column = column;
        }

        @Override
        public String getFieldCode() {
            return column.getName();
        }

        @Override
        public Collection<String> getFieldAlias() {
            return Collections.emptyList();
        }

        @Override
        public FieldType getFieldType() {
            //TODO
            return FieldType.STRING;
        }

        @Override
        public int getFieldLength() {
            return column.getSize();
        }

        @Override
        public int getFieldScale() {
            return column.getDecimalDigits();
        }

        @Override
        public Integer getFieldOrder() {
            return column.getPosition();
        }

        @Override
        public String getFieldState() {
            return StatusEntity.STATUS_ENABLE_STR;
        }

        @Override
        public String getLabel() {
            return column.getRemark();
        }

        @Override
        public String getId() {
            return null;
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public String getRemarks() {
            return column.getRemark();
        }

        @Override
        public String getFormDefinitionName() {
            return null;
        }

        @Override
        public String getFormDefinitionId() {
            return null;
        }

        @Override
        public String getFormDefinitionCode() {
            return null;
        }

        @Override
        public Integer getVersion() {
            return null;
        }

        @Override
        public MetaMap getMeta() {
            return null;
        }
    }
}
