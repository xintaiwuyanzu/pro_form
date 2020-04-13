package com.dr.framework.sys.service.Impl;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.page.Page;
import com.dr.framework.core.organise.entity.Person;
import com.dr.framework.core.organise.query.PersonQuery;
import com.dr.framework.core.organise.service.OrganisePersonService;
import com.dr.framework.sys.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    CommonMapper commonMapper;
    @Autowired
    OrganisePersonService organisePersonService;

    @Override
    public Page<Person> getPersonListByOrganiseId(String organiseId, String userName, String userCode, String personType,
                                                  String status, int page, int pageSize) {
        return organisePersonService.getPersonPage(new PersonQuery.Builder()
                        .nameLike(userName)
                        .userCodeLike(userCode)
                        .typeLike(personType)
                        .statusEqual(status)
                        .organiseIdEqual(organiseId)
                        .build()
                , (page - 1) * pageSize
                , page * pageSize
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePerson(Person person, String password) {
        //业务数据非空判断
        Assert.isTrue(person != null, "该人员数据不存在");
        commonMapper.updateById(person);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String personId, String organiseId, String userName) {
        // 这里是物理删除数据，不需要传这么多参数
        organisePersonService.deletePerson(personId);
    }

}
