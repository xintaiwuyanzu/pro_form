package com.dr.framework.common.form.core.command;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.entity.FormField;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import com.dr.framework.common.form.engine.model.core.FieldModel;
import com.dr.framework.common.form.util.CacheUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * TODO 判断别名
 * 修改表单定义
 *
 * @author dr
 */
public class FormDefinitionFieldChangeCommand extends AbstractFormDefinitionChangeCommand implements Command<Collection<FormField>> {

    public FormDefinitionFieldChangeCommand(String formDefinitionId, boolean updateTable, boolean copyData, FieldModel... fieldModel) {
        super(formDefinitionId, updateTable, copyData, fieldModel);
    }

    public FormDefinitionFieldChangeCommand(String formCode, Integer version, boolean updateTable, boolean copyData, FieldModel... fieldModel) {
        super(formCode, version, updateTable, copyData, fieldModel);
    }

    @Override
    protected Collection<FormField> executeWithUpdateVersion(CommandContext context, FormDefinition old) {

        FormDefinition newFormDefinition = copyFormDefinition(context, old);

        List<FormField> formFieldList = new ArrayList<>();
        boolean change = false;

        //转换添加的字段
        for (FieldModel fieldModel : getField()) {
            FormField oldField = old.getFieldByCode(fieldModel.getFieldCode());
            if (oldField == null || isFieldChange(oldField, fieldModel)) {
                //添加字段的场景
                change = true;
                //转换添加的字段
                oldField = newField(fieldModel);
            } else {
                //值更新字段其他定义的情况
                //复制新属性
                oldField.setLabel(fieldModel.getLabel());
                oldField.setDescription(fieldModel.getDescription());
                oldField.setRemarks(fieldModel.getRemarks());

                oldField.setFieldAliasStr(String.join(",", fieldModel.getFieldAlias()));
                oldField.setExt1(fieldModel.getExt1());
                oldField.setExt2(fieldModel.getExt2());
                oldField.setExt3(fieldModel.getExt3());
            }
            //校验字段定义格式正确
            validateFieldBaseInfo(newFormDefinition, oldField);
            newFormDefinition.getFields().add(oldField);
            formFieldList.add(oldField);
        }
        if (change) {
            newFormDefinition.setDefault(true);
            //保存字段定义到数据库
            saveFormDefinition(context, newFormDefinition);
            if (isUpdateTable()) {
                //更新表结构了创建表结构
                createTable(context, newFormDefinition);
                if (isCopyData()) {
                    copyTable(context, old, newFormDefinition);
                }
            }
        } else {
            //只是其他非关键字段更新
            CommonMapper commonMapper = context.getMapper();
            for (FormField formField : formFieldList) {
                FormField oldField = old.getFieldByCode(formField.getFieldCode());
                formField.setId(oldField.getId());
                commonMapper.updateIgnoreNullById(formField);
            }
            CacheUtil.removeFormDefinitionCache(context, old.getId());
        }
        return formFieldList;
    }

    @Override
    protected List<FormField> executeWithOutUpdateVersion(CommandContext context, FormDefinition old) {
        //创建临时表单定义，不然会污染表单定义
        FormDefinition newFormDefinition = copyFormDefinition(context, old);

        List<FormField> formFieldList = new ArrayList<>();
        boolean change = false;

        //转换添加的字段
        for (FieldModel fieldModel : getField()) {
            FormField oldField = old.getFieldByCode(fieldModel.getFieldCode());
            if (oldField == null || isFieldChange(oldField, fieldModel)) {
                //添加字段的场景
                change = true;
                //转换添加的字段
                FormField formField = newField(fieldModel);

                //校验字段定义格式正确
                validateFieldBaseInfo(newFormDefinition, fieldModel);

                newFormDefinition.getFields().add(formField);
                formFieldList.add(formField);
            } else {
                //值更新字段其他定义的情况
                //复制新属性
                oldField.setLabel(fieldModel.getLabel());
                oldField.setDescription(fieldModel.getDescription());
                oldField.setRemarks(fieldModel.getRemarks());

                oldField.setFieldAliasStr(String.join(",", fieldModel.getFieldAlias()));
                oldField.setExt1(fieldModel.getExt1());
                oldField.setExt2(fieldModel.getExt2());
                oldField.setExt3(fieldModel.getExt3());
                formFieldList.add(oldField);
                newFormDefinition.getFields().add(oldField);
            }


        }
        if (change) {
            //保存字段定义到数据库
            for (FormField formField : formFieldList) {


            }
        } else {
            //只是其他非关键字段更新
            CommonMapper commonMapper = context.getMapper();
            for (FormField formField : formFieldList) {
                commonMapper.updateIgnoreNullById(formField);
            }
        }
        //清空缓存
        CacheUtil.removeFormDefinitionCache(context, old.getId());
        return formFieldList;
    }

    /**
     * 过滤掉之前老字段定义，使用新字段定义
     *
     * @param context
     * @param old
     * @return
     */
    @Override
    protected FormDefinition copyFormDefinition(CommandContext context, FormDefinition old) {
        FormDefinition formDefinition = super.copyFormDefinition(context, old);
        List<String> oldIds = new ArrayList<>();
        for (FieldModel fieldModel : getField()) {
            FormField oldField = formDefinition.getFieldByCode(fieldModel.getFieldCode());
            if (oldField != null) {
                oldIds.add(oldField.getId().toLowerCase());
            }
        }
        formDefinition.getFields().removeIf(f -> oldIds.contains(f.getId().toLowerCase()));
        return formDefinition;
    }

    @Override
    protected FormField newField(FieldModel fieldModel) {
        FormField newField = super.newField(fieldModel);
        newField.setId(UUID.randomUUID().toString());
        return newField;
    }

    private boolean isFieldChange(FormField old, FieldModel fieldModel) {
        if (old.getFieldType().equals(fieldModel.getFieldType())) {
            if (old.getFieldLength() == fieldModel.getFieldLength()) {
                return old.getFieldScale() != fieldModel.getFieldScale();
            }
        }
        return true;
    }

}
