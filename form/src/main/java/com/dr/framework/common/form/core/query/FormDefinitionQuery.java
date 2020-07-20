package com.dr.framework.common.form.core.query;

import com.dr.framework.common.entity.StatusEntity;

/**
 * 表单查询类
 * <p>
 * code不为空的时候，版本为空，则查询默认版本
 * <p>
 * code不为空的时候，版本不为空，则查询指定版本
 * <p>
 * code不为空的时候，versionAll为true，则查询所有code的版本
 *
 * @author dr
 */
public class FormDefinitionQuery {

    private String codeLike;
    private String codeEqual;
    private String name;
    private String typeLike;
    private String typeEqual;
    private String status;

    private Integer version;
    private boolean versionAll;


    public FormDefinitionQuery codeLike(String code) {
        this.codeLike = code;
        return this;
    }

    public FormDefinitionQuery codeEqual(String code) {
        this.codeEqual = code;
        return this;
    }

    public FormDefinitionQuery nameLike(String name) {
        this.name = name;
        return this;
    }

    public FormDefinitionQuery typeLike(String type) {
        this.typeLike = type;
        return this;
    }

    public FormDefinitionQuery typeEqual(String type) {
        this.typeEqual = type;
        return this;
    }

    public FormDefinitionQuery statusEnable() {
        this.status = StatusEntity.STATUS_ENABLE_STR;
        return this;
    }

    public FormDefinitionQuery statusDisable() {
        this.status = StatusEntity.STATUS_DISABLE_STR;
        return this;
    }

    public FormDefinitionQuery statusAll() {
        this.status = null;
        return this;
    }

    public FormDefinitionQuery versionEqual(Integer version) {
        this.version = version;
        return this;
    }

    public FormDefinitionQuery versionAll() {
        versionAll = true;
        return this;
    }


    public String getCodeLike() {
        return codeLike;
    }

    public String getCodeEqual() {
        return codeEqual;
    }

    public String getName() {
        return name;
    }

    public String getTypeLike() {
        return typeLike;
    }

    public String getTypeEqual() {
        return typeEqual;
    }

    public String getStatus() {
        return status;
    }

    public Integer getVersion() {
        return version;
    }

    public boolean isVersionAll() {
        return versionAll;
    }
}
