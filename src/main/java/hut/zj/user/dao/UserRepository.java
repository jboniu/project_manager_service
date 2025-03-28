package hut.zj.user.dao;

import hut.zj.common.base.dao.BaseRepository;
import hut.zj.user.entity.Role;
import hut.zj.user.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface UserRepository extends BaseRepository<User> {
    User findByUserName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("delete from User u where u.userName = :name")
    void deleteByUserName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("update User set status = :status where id=:id")
    void updateStatusById(@Param("id") String id, @Param("status") String status);

    @Query("select u from User u where u.id = :id")
    User findById(@Param("id") String id);

    @Query(value = "select r from Role r left join UserRole ur on r.id = ur.roleId where ur.userId = :id")
    List<Role> findRoleByUserId(@Param("id") String id);

    @Query(value = "select p.code from t_permission p left join t_role_permission rp on(p.id=rp.permission_id) left join t_user_role ur on(rp.role_id=ur.role_id) where ur.user_id=:userId",nativeQuery = true)
    Set<String> findPerByUserId(@Param("userId") String id);
}
