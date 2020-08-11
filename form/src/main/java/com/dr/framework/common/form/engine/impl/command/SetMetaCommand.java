package com.dr.framework.common.form.engine.impl.command;

import com.dr.framework.common.config.model.MetaMap;
import com.dr.framework.common.config.service.CommonMetaService;
import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;

import java.util.Map;

/**
 * 设置元数据
 *
 * @author dr
 */
public interface SetMetaCommand extends Command<MetaMap> {
    @Override
    default MetaMap execute(CommandContext context) {
        return context.getApplicationContext()
                .getBean(CommonMetaService.class)
                .setMetaData(getRefId(context), getRefType(context), getMetaMap());
    }

    /**
     * 获取元数据Map
     *
     * @return
     */
    Map<String, String> getMetaMap();

    /**
     * 获取元数据外键Id
     *
     * @param context
     * @return
     */
    String getRefId(CommandContext context);

    /**
     * 获取元数据外键类型
     *
     * @param context
     * @return
     */
    String getRefType(CommandContext context);

}
