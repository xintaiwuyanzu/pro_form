package com.dr.framework.common.form.engine.impl.command;

import com.dr.framework.common.config.service.CommonMetaService;
import com.dr.framework.core.organise.entity.Person;
import com.dr.framework.core.security.SecurityHolder;

/**
 * 抽象父类，封装通用方法
 *
 * @author dr
 */
public abstract class AbstractCommand {
    protected long deleteMeta(CommonMetaService commonMetaService, String refId, String metaType) {
        return commonMetaService.deleteMeta(refId, metaType);
    }

    /**
     * 获取当前上下文登录用户Id
     *
     * @return
     */
    protected String getCurrentPersonId() {
        Person person = SecurityHolder.get().currentPerson();
        return person == null ? null : person.getId();
    }
}
