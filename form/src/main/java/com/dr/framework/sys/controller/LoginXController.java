package com.dr.framework.sys.controller;

import com.dr.framework.common.entity.ResultEntity;
import com.dr.framework.common.page.Page;
import com.dr.framework.core.organise.entity.Organise;
import com.dr.framework.core.organise.entity.Person;
import com.dr.framework.core.organise.query.OrganiseQuery;
import com.dr.framework.core.organise.service.LoginService;
import com.dr.framework.core.organise.service.OrganisePersonService;
import com.dr.framework.core.security.SecurityHolder;
import com.dr.framework.core.security.bo.ClientInfo;
import com.dr.framework.core.security.entity.Role;
import com.dr.framework.core.security.entity.SysMenu;
import com.dr.framework.core.web.annotations.Current;
import com.dr.framework.sys.service.LoginXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dr.framework.common.entity.ResultEntity.error;
import static com.dr.framework.common.entity.ResultEntity.success;

@RestController
@RequestMapping({"${common.api-path:/api}/login"})
public class LoginXController {

    @Autowired
    OrganisePersonService organisePersonService;
    @Autowired
    LoginService loginService;
    @Autowired
    LoginXService loginXService;

    /**
     * 默认登录超时时间为30分钟
     */
    @Value("${server.session.timeout:30m}")
    Duration timeout;

    /**
     * 登录校验
     *
     * @param username   用户名
     * @param password   密码
     * @param loginType  登录类型
     * @param clientInfo 客户端信息
     * @param response
     * @return
     */
    @PostMapping("/validate")
    public ResultEntity<String> validate(Boolean onOff, @RequestParam String username, @RequestParam String password, @RequestParam(defaultValue = LoginService.LOGIN_TYPE_DEFAULT) String loginType, @Current ClientInfo clientInfo, HttpServletRequest request, HttpServletResponse response) {
        try {
            Person person = loginService.login(username, password, loginType, clientInfo.getRemoteIp());
            String token = loginService.auth(person);
            Cookie cookie = new Cookie(SecurityHolder.TOKEN_HEADER_KEY, token);
            response.addHeader(SecurityHolder.TOKEN_HEADER_KEY, token);
            //设置超时时间为2小时
            String path = request.getContextPath();
            if (StringUtils.isEmpty(path)) {
                path = "/";
            }
            cookie.setMaxAge((int) timeout.getSeconds());
            cookie.setPath(path);
            cookie.setHttpOnly(true);
            cookie.setDomain(clientInfo.getRemoteIp());
            response.addCookie(cookie);
            return ResultEntity.success(token);
        } catch (Exception e) {
            return ResultEntity.error("用户名或密码错误");
        }
    }

    /**
     * 查询当前登录用户的默认部门以及部门下的责任人
     *
     * @param person
     * @return
     */
    @PostMapping(value = "/getDefaultOrganize")
    public ResultEntity getDefaultOrganize(@Current Person person) {
        //根据人员ID查询人员默认组织机构
        Organise organise = organisePersonService.getPersonDefaultOrganise(person.getId());
        if (organise != null) {
            //根据人员默认组织机构查询下的所有人
            List<Person> personList = loginXService.queryAllCurrentHeaderByOrgid(organise.getId());
            List<Map<String, Object>> valueList = new ArrayList<>();
            Map<String, Object> valueMap = new HashMap<>();
            valueMap.put("id", organise.getId());
            valueMap.put("name", organise.getOrganiseName());
            valueMap.put("disabled", true);
            List<Map<String, Object>> neiList = new ArrayList<>();
            Map<String, Object> neiMap;
            Person person1;
            for (int i = 0; i < personList.size(); i++) {
                person1 = personList.get(i);
                neiMap = new HashMap<>();
                neiMap.put("id", person1.getId());
                neiMap.put("name", person1.getUserName());
                neiMap.put("disabled", false);
                neiList.add(neiMap);
            }
            valueMap.put("children", neiList);
            valueList.add(valueMap);
            return success(valueList);
        } else {
            return error("未查询到当前用户所属组织机构！");
        }
    }

    /**
     * 查询所有部门
     *
     * @return
     */
    @PostMapping(value = "/getAllDepartment")
    public ResultEntity getAllDepartment() {
        OrganiseQuery organiseQuery = new OrganiseQuery.Builder().build();
        List<Organise> list = organisePersonService.getOrganiseList(organiseQuery);

        return success(list);
    }

    /**
     * 分页查询角色列表
     */
    @RequestMapping(value = "/getRolePage")
    public ResultEntity<Page<Role>> getRolePage(@RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = Page.DEFAULT_PAGE_SIZE + "15") int pageSize) {
        return success(loginXService.getRolePage(page, pageSize));
    }

    /**
     * 根据角色ID查询角色详情
     *
     * @param roleId
     * @return
     */
    @RequestMapping("/getRoleById")
    public ResultEntity<Role> getRoleById(String roleId) {
        return success(loginXService.getRoleById(roleId));
    }

    @RequestMapping("/getHasPermissionIds")
    public ResultEntity<List> getHasPermissionIds(SysMenu sysMenu) {
        return success(loginXService.getHasPermissionIds(sysMenu));
    }

    /**
     * 添加新角色
     *
     * @param person
     * @param order
     * @param roleName
     * @param roleCode
     * @return
     */
    @RequestMapping("/addRole")
    public ResultEntity addRole(@Current Person person, int order, String roleName, String roleCode) {
        loginXService.addRole(person.getUserName(), order, roleName, roleCode);
        return success();
    }

    /**
     * 修改角色（角色名称等...）
     *
     * @param person
     * @param role
     * @return
     */
    @PostMapping(value = "/updateRole")
    public ResultEntity updateRole(@Current Person person, Role role) {
        loginXService.updateRole(person.getUserName(), role);
        return success();
    }

    /**
     * 删除角色
     *
     * @param roleCode
     * @return
     */
    @RequestMapping("/deleteRole")
    public ResultEntity deleteRole(String roleCode) {
        loginXService.deleteRole(roleCode);
        return success();
    }

    /**
     * 给角色加权限（多个权限/菜单）
     *
     * @param addPermissionIds
     * @param delPermissionIds
     * @param roleId
     * @param addMenuIds
     * @param delMenuIds
     * @return
     */
    @RequestMapping("/addPermissionToRole")
    public ResultEntity addPermissionToRole(String addPermissionIds,//添加的权限集合
                                            String delPermissionIds,//删除的权限集合
                                            String roleId,
                                            String addMenuIds,//添加的菜单集合
                                            String delMenuIds//删除的菜单集合
    ) {
        Assert.isTrue(!StringUtils.isEmpty(roleId), "角色不存在或为空！");
        String[] addPermissions = getArray(addPermissionIds);
        String[] delPermissions = getArray(delPermissionIds);
        String[] addMenus = getArray(addMenuIds);
        String[] delMenus = getArray(delMenuIds);
        loginXService.addPermissionToRole(addPermissions, delPermissions, roleId, addMenus, delMenus);
        return success();
    }

    private String[] getArray(String s) {
        if (!StringUtils.isEmpty(s)) {
            return s.split(",");
        }
        return null;
    }

    /**
     * 获取权限列表和菜单列表，根据角色ID标注哪些权限已授权给该角色
     *
     * @param sysMenu
     * @return
     */
    @RequestMapping("/getPermissionMenuList")
    public ResultEntity<List> getPermissionMenuList(@Current Person person, SysMenu sysMenu) {
        return success(loginXService.getPermissionMenuList(person, sysMenu));
    }

    @RequestMapping("/getPermissionMenuListone")
    public ResultEntity<List> getPermissionMenuListone(@Current Person person, String roleId, String type) {
        return success(loginXService.getPermissionMenuListone(person, roleId, type));
    }

    /**
     * 获取用户角色列表
     *
     * @param userId
     * @return
     */
    @RequestMapping("/getRoleListToUser")
    public ResultEntity<List<Role>> getRoleListToUser(String userId) {
        return success(loginXService.getRoleListToUser(userId));
    }

    /**
     * 用户添加角色
     *
     * @param addRoleIds
     * @param delRoleIds
     * @param userId
     * @return
     */
    @RequestMapping("/addRoleToUser")
    public ResultEntity addRoleToUser(String addRoleIds, String delRoleIds, String userId) {
        Assert.isTrue(!StringUtils.isEmpty(userId), "用户为空或不存在");
        String[] addRoleIdss = getArray(addRoleIds);
        String[] delRoleIdss = getArray(delRoleIds);
        loginXService.addRoleToUser(addRoleIdss, delRoleIdss, userId);
        return success();
    }

    /**
     * 获取角色列表，根据用户ID标注哪些角色已授权给该用户
     *
     * @param userId
     * @return
     */
    @RequestMapping("/getRoleList")
    public ResultEntity<Map> getRoleList(String userId) {
        return success(loginXService.getRoleList(userId));
    }

}
