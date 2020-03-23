package com.dr.framework.common.form.core.plugin;

import com.dr.framework.common.form.engine.Plugin;
import com.dr.framework.core.orm.jdbc.Relation;

public interface CreateWorkFormPlugin extends Plugin {

    void beforeCreate(Relation relation);

}
