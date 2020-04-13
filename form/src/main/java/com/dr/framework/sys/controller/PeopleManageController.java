package com.dr.framework.sys.controller;


import com.dr.framework.common.entity.ResultEntity;
import com.dr.framework.common.page.Page;
import com.dr.framework.core.organise.entity.Person;
import com.dr.framework.core.organise.service.OrganisePersonService;
import com.dr.framework.sys.entity.Condition;
import com.dr.framework.sys.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @Author: dr
 */
@RestController
@RequestMapping(value = "/api/peopleManage")
public class PeopleManageController {

    @Autowired
    OrganisePersonService organisePersonService;
    @Autowired
    PersonService personService;

    /**
     * 获取人员信息page
     *
     * @param condition
     * @param page
     * @param pageSize
     * @return
     */
    @PostMapping("/getDepartPerson")
    public ResultEntity<Page<Person>> getDepartPerson(Condition condition, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = Page.DEFAULT_PAGE_SIZE + "") int pageSize) {
        return ResultEntity.success(personService.getPersonListByOrganiseId(
                condition.getOrganiseId(), condition.getUserName(), condition.getUserCode(),
                condition.getPersonType(), condition.getStatus(), page, pageSize));
    }

    /**
     * 修改人员信息
     *
     * @param person
     * @param password
     * @return
     */
    @PostMapping("/updatePerson")
    public ResultEntity updatePerson(Person person, String password) {
        personService.updatePerson(person, password);
        return ResultEntity.success();
    }

    /**
     * 添加人员
     *
     * @param person
     * @param organiseId
     * @param role
     * @return
     */
    @PostMapping("/addPerson")
    public ResultEntity addPerson(Person person, String organiseId, String role) {
        person.setId(UUID.randomUUID().toString());
        organisePersonService.addPerson(person, organiseId, true, Base64Utils.encodeToString("123456".getBytes(Charset.forName("utf-8"))));
        return ResultEntity.success("save success");
    }

    /**
     * 删除人员
     *
     * @param personId
     * @param organiseId
     * @param userName
     * @return
     */
    @PostMapping("/delete")
    public ResultEntity delete(String personId, String organiseId, String userName) {
        personService.delete(personId, organiseId, userName);
        return ResultEntity.success("delete success");
    }
}
