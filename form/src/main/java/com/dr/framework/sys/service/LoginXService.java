package com.dr.framework.sys.service;

import com.dr.framework.common.entity.TreeNode;
import com.dr.framework.common.page.Page;
import com.dr.framework.core.organise.entity.Person;
import com.dr.framework.core.security.entity.Role;
import com.dr.framework.core.security.entity.SysMenu;

import java.util.List;
import java.util.Map;

public interface LoginXService {


    List<Person> queryAllCurrentHeaderByOrgid(String organizeid);

    /**
     * 分页查询角色列表
     */
    Page<Role> getRolePage(int page, int pageSize);

    /**
     * 根据角色ID查询角色详情
     *
     * @param roleId
     * @return
     */
    Role getRoleById(String roleId);

    /**
     * 添加新角色
     *
     * @param order
     * @param roleName
     * @param roleCode
     */
    void addRole(String personName, int order, String roleName, String roleCode);

    /**
     * 修改角色（角色名称等...）
     *
     * @param personName
     * @param role
     * @return
     */
    void updateRole(String personName, Role role);

    /**
     * 删除角色
     *
     * @param roleCode
     * @return
     */
    void deleteRole(String roleCode);

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
    void addPermissionToRole(String[] addPermissionIds,
                             String[] delPermissionIds,
                             String roleId,
                             String[] addMenuIds,
                             String[] delMenuIds);


    /**
     * 获取权限列表和菜单列表，根据角色ID标注哪些权限已授权给该角色
     *
     * @param sysMenu
     * @return
     */
    List<TreeNode> getPermissionMenuList(Person person, SysMenu sysMenu);

    List<TreeNode> getPermissionMenuListone(Person person, String roleId, String type);

    List getHasPermissionIds(SysMenu sysMenu);

    /**
     * 获取用户角色列表
     *
     * @param userId
     * @return
     */
    List<Role> getRoleListToUser(String userId);

    /**
     * 用户添加角色
     *
     * @param addRoleIds
     * @param delRoleIds
     * @param userId
     */
    void addRoleToUser(String[] addRoleIds, String[] delRoleIds, String userId);


    /**
     * 获取角色列表，根据用户ID标注哪些角色已授权给该用户
     *
     * @param userId
     * @return
     */
    Map<String, Object> getRoleList(String userId);

}
