package hut.zj.user.service;

import hut.zj.common.base.service.IBaseService;
import hut.zj.user.entity.Role;

import java.util.Set;

public interface IRoleService extends IBaseService<Role> {
    /**
     * 根据用户id获取已经有的角色ids
     * @param id
     * @return
     */
    Set<String> findRoleIdsByUserId(String id);

    /**
     * 根据角色名获取角色
     * @param name
     * @return
     */
    Role findByName(String name);

    /**
     * 根据角色id查询已有权限ids
     * @param id
     * @return
     */
    Set<String> findPermissionIdsByid(String id);

    /**
     * 给角色分配权限
     * @param roleId
     * @param permissionIds
     */
    void assignmentPermission(String roleId, String[] permissionIds);

    /**
     * 删除角色
     * @param id
     */
    void delete(String id);
}
