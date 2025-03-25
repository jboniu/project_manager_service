package com.javanoteany.user.dao;

import com.javanoteany.common.base.dao.BaseRepository;
import com.javanoteany.user.entity.Role;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends BaseRepository<Role> {
    /**
     * 根据角色名获取角色
     * @param name
     * @return
     */
    Role findByName(@Param("name") String name);
}
