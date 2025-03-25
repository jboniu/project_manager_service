package com.javanoteany.user.dao;

import com.javanoteany.common.base.dao.BaseRepository;
import com.javanoteany.user.entity.UserRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface UserRoleRepository extends BaseRepository<UserRole> {
    /**
     * 根据用户id 获取角色ids
     * @param id
     * @return
     */
    @Query("select ur.roleId from UserRole ur where ur.userId = :id")
    Set<String> findByUserId(@Param("id") String id);

    /**
     * 删除用户的角色
     * @param id
     */
    @Transactional
    @Modifying
    @Query("delete from UserRole where userId=:userId")
    void deleteByUserId(@Param("userId") String id);
}
