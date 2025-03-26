package hut.zj.user.service;

import hut.zj.common.base.service.IBaseService;
import hut.zj.user.entity.UserRole;

import java.util.Set;

public interface IUserRoleService extends IBaseService<UserRole> {
    /**
     * 根据用户id获取角色ids
     * @param id
     * @return
     */
    Set<String> findRoleIdsByUserId(String id);

}
