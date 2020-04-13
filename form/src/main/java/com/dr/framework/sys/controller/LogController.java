package com.dr.framework.sys.controller;

import com.dr.framework.common.controller.BaseController;
import com.dr.framework.core.orm.sql.support.SqlQuery;
import com.dr.framework.sys.controller.entity.Log;
import com.dr.framework.sys.controller.entity.LogInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统操作日志
 *
 * @author dr
 */
@RestController
@RequestMapping("/api/log")
public class LogController extends BaseController<Log> {

    @Override
    protected void onBeforePageQuery(HttpServletRequest request, SqlQuery<Log> sqlQuery, Log entity) {
        super.onBeforePageQuery(request, sqlQuery, entity);
        sqlQuery.startingWith(LogInfo.PERSON, entity.getPerson());
        sqlQuery.equal(LogInfo.LOGRESULT, entity.getLogresult());
        sqlQuery.startingWith(LogInfo.LOGTYPE, entity.getLogtype());
        sqlQuery.startingWith(LogInfo.PERATED, entity.getPerated());
        sqlQuery.orderByDesc(LogInfo.CREATEDATE);
    }

}
