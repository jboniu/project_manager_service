package com.javanoteany.user.service;

import com.javanoteany.common.base.service.IBaseService;
import com.javanoteany.user.entity.UserRole;

import java.util.Set;

public interface IUserRoleService extends IBaseService<UserRole> {
    /**
     * 根据用户id获取角色ids
     * @param id
     * @return
     */
    Set<String> findRoleIdsByUserId(String id);

}
