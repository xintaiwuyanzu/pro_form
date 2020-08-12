package com.dr.framework.common.form.engine.impl.command;

import com.dr.framework.common.config.service.CommonMetaService;

/**
 * 抽象父类，封装通用方法
 *
 * @author dr
 */
public abstract class AbstractCommand {
    protected long deleteMeta(CommonMetaService commonMetaService, String refId, String metaType) {
        return commonMetaService.deleteMeta(refId, metaType);
    }
}
