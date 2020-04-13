package com.dr.framework.sys.service;


import com.dr.framework.common.page.Page;
import com.dr.framework.core.organise.entity.Organise;
import com.dr.framework.core.organise.entity.Person;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PersonService {
    /**
     * 获取人员信息page
     *
     * @param organiseId
     * @param userName
     * @param userCode
     * @param personType
     * @param status
     * @param page
     * @param pageSize
     * @return
     */
    Page<Person> getPersonListByOrganiseId(String organiseId, String userName, String userCode, String personType, String status, int page, int pageSize);

    /**
     * 修改人员信息
     *
     * @param person
     * @param password
     */
    void updatePerson(Person person, String password);

    /**
     * 删除人员信息
     *
     * @param personId
     * @param organiseId
     * @param userName
     */
    void delete(String personId, String organiseId, String userName);

}
