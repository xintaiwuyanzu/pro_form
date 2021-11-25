package com.dr.framework.common.form.controller;

import com.dr.framework.common.entity.ResultEntity;
import com.dr.framework.common.entity.ResultListEntity;
import com.dr.framework.common.form.core.entity.FormDefinition;
import com.dr.framework.common.form.core.query.FormDefinitionQuery;
import com.dr.framework.common.form.core.service.FormDefinitionService;
import com.dr.framework.common.form.core.service.FormDefinitionTypeProvider;
import com.dr.framework.common.form.engine.TypeComponent;
import com.dr.framework.common.form.engine.model.core.FormModel;
import com.dr.framework.common.page.Page;
import com.dr.framework.core.organise.entity.Person;
import com.dr.framework.core.web.annotations.Current;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.OrderComparator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 表单定义controller
 *
 * @author dr
 */
@RestController
@RequestMapping("${common.api-path:/api}/formDefinition")
public class FormDefinitionController extends ApplicationObjectSupport implements InitializingBean {
    @Autowired
    FormDefinitionService formDefinitionService;

    List<FormDefinitionTypeProvider> typeProviderList;

    /**
     * 表单定义添加
     *
     * @param formDefinition 表单定义
     * @param createTable    是否创建表结构
     * @param person         当前登录人
     * @return
     */
    @PostMapping("/insert")
    public ResultEntity<FormModel> insert(
            FormDefinition formDefinition,
            @RequestParam(defaultValue = "true") boolean createTable,
            @Current Person person
    ) {
        return ResultEntity.success(formDefinitionService.addFormDefinition(formDefinition, new ArrayList<>(), createTable));
    }

    /**
     * 更新表单定义
     *
     * @param formDefinition 表单定义
     * @param person         当前登录人
     * @return
     */
    @PostMapping("/update")
    public ResultEntity<FormModel> update(FormDefinition formDefinition, @Current Person person) {
        return ResultEntity.success(formDefinitionService.updateFormDefinitionBaseInfo(formDefinition));
    }

    /**
     * 查询分页数据
     *
     * @param formDefinition
     * @param pageIndex
     * @param pageSize
     * @param page
     * @param versionAll
     * @return
     */
    @PostMapping("/page")
    public ResultEntity page(FormDefinition formDefinition,
                             @RequestParam(defaultValue = "0") int pageIndex,
                             @RequestParam(defaultValue = Page.DEFAULT_PAGE_SIZE_STR) int pageSize,
                             @RequestParam(defaultValue = "true") boolean page,
                             @RequestParam(defaultValue = "true") boolean versionAll) {
        FormDefinitionQuery query = new FormDefinitionQuery();
        query.nameLike(formDefinition.getFormName())
                .codeLike(formDefinition.getFormCode());
        if (formDefinition.getVersion() != null) {
            //查询特定版本
            query.versionEqual(formDefinition.getVersion());
        } else if (versionAll) {
            //查询所有版本
            query.versionAll();
        }
        if (page) {
            return ResultEntity.success(formDefinitionService.selectPageFormDefinition(query, pageIndex, pageSize));
        } else {
            return ResultEntity.success(formDefinitionService.selectFormDefinitionByQuery(query));
        }
    }

    /**
     * 获取支持的表单类型
     *
     * @return
     */
    @RequestMapping("/formType")
    public ResultListEntity<TypeComponent> formType() {
        return ResultListEntity.success(typeProviderList.stream().map(TypeComponent.TypeComponentVo::new).collect(Collectors.toList()));
    }

    @Override
    public void afterPropertiesSet() {
        typeProviderList = getApplicationContext().getBeansOfType(FormDefinitionTypeProvider.class)
                .values()
                .stream()
                .sorted(OrderComparator.INSTANCE)
                .collect(Collectors.toList());
    }
}
