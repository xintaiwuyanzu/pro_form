package com.dr.framework.sys.service.Impl;

import com.dr.framework.common.dao.CommonMapper;
import com.dr.framework.common.entity.TreeNode;
import com.dr.framework.common.page.Page;
import com.dr.framework.core.organise.entity.Person;
import com.dr.framework.core.organise.query.PersonQuery;
import com.dr.framework.core.organise.service.OrganisePersonService;
import com.dr.framework.core.security.entity.Permission;
import com.dr.framework.core.security.entity.Role;
import com.dr.framework.core.security.entity.SysMenu;
import com.dr.framework.core.security.query.RoleQuery;
import com.dr.framework.core.security.query.SysMenuQuery;
import com.dr.framework.core.security.service.SecurityManager;
import com.dr.framework.sys.entity.PermissionExistVo;
import com.dr.framework.sys.service.LoginXService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class LoginXServiceImpl implements LoginXService {

    @Autowired
    OrganisePersonService organisePersonService;

    @Autowired
    SecurityManager securityManager;

    @Autowired
    CommonMapper commonMapper;

    /**
     * 根据组织机构ID查询当前组织机构所有负责人
     *
     * @param organizeid
     * @return
     */
    @Override
    public List<Person> queryAllCurrentHeaderByOrgid(String organizeid) {
        PersonQuery personQuery = new PersonQuery.Builder()
                .organiseIdEqual(organizeid).dutyLike("fuzeren")
                .build();
        return organisePersonService.getPersonList(personQuery);
    }


    /**
     * 分页查询角色列表
     */
    @Override
    public Page<Role> getRolePage(int page, int pageSize) {
        return securityManager.selectRolePage(new RoleQuery.Builder().build(), (page - 1) * pageSize, page * pageSize - 1);
    }

    /**
     * 根据角色ID查询角色详情
     *
     * @param roleId
     * @return
     */
    @Override
    public Role getRoleById(String roleId) {
        return securityManager.selectRoleList(new RoleQuery.Builder().idEqual(roleId).build()).get(0);
    }

    @Override
    public void addRole(String personName, int order, String roleName, String roleCode) {
        Role role = new Role();
        role.setCode(roleCode);
        role.setName(roleName);
        role.setId(UUID.randomUUID().toString());
        role.setCreateDate(System.currentTimeMillis());
        role.setCreatePerson(personName);
        role.setOrder(order);
        role.setStatus(0);
        securityManager.addRole(role);
    }

    /**
     * 修改角色（角色名称等...）
     *
     * @param personName
     * @param role
     * @return
     */
    @Override
    public void updateRole(String personName, Role role) {
        role.setUpdatePerson(personName);
        role.setUpdateDate(System.currentTimeMillis());
        securityManager.updateRole(role);
    }

    /**
     * 删除角色
     *
     * @param roleCode
     * @return
     */
    @Override
    public void deleteRole(String roleCode) {
        securityManager.deleteRole(roleCode);
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
    @Override
    public void addPermissionToRole(String[] addPermissionIds,
                                    String[] delPermissionIds,
                                    String roleId,
                                    String[] addMenuIds,
                                    String[] delMenuIds) {

        if (delMenuIds != null) {
            securityManager.removeRoleMenu(roleId, delMenuIds);
        }
        if (delPermissionIds != null) {
            securityManager.removeRolePermission(roleId, delPermissionIds);
        }
        if (addPermissionIds != null) {
            securityManager.addPermissionToRole(roleId, addPermissionIds);
        }
        if (addMenuIds != null) {
            securityManager.addMenuToRole(roleId, addMenuIds);
        }
    }

    /**
     * 获取权限列表和菜单列表，根据角色ID标注哪些权限已授权给该角色
     *
     * @param sysMenus
     * @return
     */
    @Override
    public List<TreeNode> getPermissionMenuList(Person person, SysMenu sysMenus) {
        List<PermissionExistVo> list = new ArrayList<>();
        List<TreeNode> aDefault = null;
        if ("menu".equals(sysMenus.getName())) {
            aDefault = this.securityManager.menuTree("default", person.getId(), true);
            List<SysMenu> sysMenusNotInRole = securityManager.selectMenuList(new SysMenuQuery.Builder().build());
            List<SysMenu> sysMenusInRole = securityManager.selectMenuList(new SysMenuQuery.Builder().roleIdIn(sysMenus.getId()).build());
            for (SysMenu sysMenu : sysMenusInRole) {
                list.add(addSysMenu(sysMenu, true));
            }
            for (TreeNode treeNode : aDefault) {
                addTreeNode(sysMenusInRole, treeNode);
            }
            for (SysMenu sysMenu : sysMenusNotInRole) {
                boolean flag = true;
                for (SysMenu menu : sysMenusInRole) {
                    if (menu.getId().equals(sysMenu.getId())) {
                        flag = false;
                    }
                }
                if (flag) {
                    list.add(addSysMenu(sysMenu, false));
                }
            }

        }
        //TODO 权限列表的获取
        /*if ("permission".equals(type)){
            List<Permission> permissions = securityManager.selectPermissionList(new PermissionQuery.Builder().build());
            List<Permission> permissionsInRole = securityManager.selectPermissionList(new SysMenuQuery.Builder().roleIdIn(roleId).build());
            List<SysMenu> permissionsNotInRole = new ArrayList<>();
            List<SysMenu> sysMenusNotInRole = new ArrayList<>();
        }*/
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("id", "99999");
        valueMap.put("name", "所有权限");
        valueMap.put("children", aDefault);
        valueMap.put("disabled", true);

        return aDefault;
    }

    @Override
    public List<TreeNode> getPermissionMenuListone(Person person, String roleId, String type) {
        List<PermissionExistVo> list = new ArrayList<>();
        List<TreeNode> aDefault = null;
        if ("menu".equals(type)) {
            aDefault = this.securityManager.menuTree("defaultone", person.getId(), true);
            List<SysMenu> sysMenusNotInRole = securityManager.selectMenuList(new SysMenuQuery.Builder().build());
            List<SysMenu> sysMenusInRole = securityManager.selectMenuList(new SysMenuQuery.Builder().roleIdIn(roleId).build());
            for (SysMenu sysMenu : sysMenusInRole) {
                list.add(addSysMenu(sysMenu, true));
            }
            for (TreeNode treeNode : aDefault) {
                addTreeNode(sysMenusInRole, treeNode);
            }
            for (SysMenu sysMenu : sysMenusNotInRole) {
                boolean flag = true;
                for (SysMenu menu : sysMenusInRole) {
                    if (menu.getId().equals(sysMenu.getId())) {
                        flag = false;
                    }
                }
                if (flag) {
                    list.add(addSysMenu(sysMenu, false));
                }
            }
        }
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("id", "99999");
        valueMap.put("name", "所有权限");
        valueMap.put("children", aDefault);
        valueMap.put("disabled", true);
        return aDefault;
    }

    @Override
    public List getHasPermissionIds(SysMenu sysMenu) {
        List<SysMenu> sysMenus = securityManager.selectMenuList(new SysMenuQuery.Builder().roleIdIn(sysMenu.getId()).build());
        List<String> list = new ArrayList<>();
        for (SysMenu sysMenu1 : sysMenus) {
            list.add(sysMenu1.getId());
        }
        return list;
    }

    private void addTreeNode(List<SysMenu> sysMenusInRole, TreeNode treeNode) {
        List<TreeNode> nodeChildren = treeNode.getChildren();
        if (nodeChildren != null) {
            boolean flag = true;
            for (SysMenu sysMenu : sysMenusInRole) {
                if (sysMenu.getId().equals(treeNode.getId())) {
                    flag = false;
                }
            }
            if (flag) {
                SysMenu data = (SysMenu) treeNode.getData();
                treeNode.setData(addSysMenu(data, false));
            } else {
                SysMenu data = (SysMenu) treeNode.getData();
                treeNode.setData(addSysMenu(data, true));
            }
            for (TreeNode nodeChild : nodeChildren) {
                addTreeNode(sysMenusInRole, nodeChild);
            }
        } else {
            boolean flag = true;
            for (SysMenu sysMenu : sysMenusInRole) {
                if (sysMenu.getId().equals(treeNode.getId())) {
                    flag = false;
                }
            }
            if (flag) {
                SysMenu data = (SysMenu) treeNode.getData();
                treeNode.setData(addSysMenu(data, false));
            } else {
                SysMenu data = (SysMenu) treeNode.getData();
                treeNode.setData(addSysMenu(data, true));
            }
        }
    }

    private PermissionExistVo addSysMenu(SysMenu sysMenu, Boolean exist) {
        PermissionExistVo permissionExistVo = new PermissionExistVo();
        permissionExistVo.setExist(exist);
        permissionExistVo.setId(sysMenu.getId());
        permissionExistVo.setName(sysMenu.getName());
        permissionExistVo.setDescription(sysMenu.getDescription());
        permissionExistVo.setType("sysMenu");
        return permissionExistVo;
    }

    private PermissionExistVo addPermission(Permission permission, Boolean exist) {
        PermissionExistVo permissionExistVo = new PermissionExistVo();
        permissionExistVo.setId(permission.getId());
        permissionExistVo.setName(permission.getName());
        permissionExistVo.setDescription(permission.getDescription());
        permissionExistVo.setExist(exist);
        permissionExistVo.setType("permission");
        return permissionExistVo;
    }

    /**
     * 获取用户角色列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> getRoleListToUser(String userId) {
        return securityManager.userRoles(userId);
    }

    /**
     * 用户添加角色
     *
     * @param addRoleIds
     * @param delRoleIds
     * @param userId
     */
    @Override
    public void addRoleToUser(String[] addRoleIds, String[] delRoleIds, String userId) {
        if (delRoleIds != null) {
            securityManager.removeUserRole(userId, delRoleIds);
        }
        if (addRoleIds != null) {
            securityManager.addRoleToUser(userId, addRoleIds);
        }
    }

    /**
     * 获取角色列表，根据用户ID标注哪些角色已授权给该用户
     *
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> getRoleList(String userId) {
        List<Role> roles = securityManager.selectRoleList(new RoleQuery.Builder().build());
        List<Role> roleList = securityManager.userRoles(userId);
        List<PermissionExistVo> list = new ArrayList<>();
        for (Role role : roleList) {
            list.add(addRole(role, true));
        }
        for (Role role : roles) {
            boolean flag = true;
            for (Role r : roleList) {
                if (r.getId().equals(role.getId())) {
                    flag = false;
                }
            }
            if (flag) {
                list.add(addRole(role, false));
            }
        }
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("id", "99999");
        valueMap.put("name", "所有角色");
        valueMap.put("children", list);
        valueMap.put("disabled", true);
        return valueMap;
    }

    private PermissionExistVo addRole(Role role, Boolean exist) {
        PermissionExistVo permissionExistVo = new PermissionExistVo();
        permissionExistVo.setId(role.getId());
        permissionExistVo.setName(role.getName());
        permissionExistVo.setDescription(role.getDescription());
        permissionExistVo.setExist(exist);
        permissionExistVo.setType("role");
        return permissionExistVo;
    }

}
