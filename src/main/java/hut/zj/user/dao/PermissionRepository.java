package hut.zj.user.dao;

import hut.zj.common.base.dao.BaseRepository;
import hut.zj.user.entity.Permission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PermissionRepository extends BaseRepository<Permission> {

    @Query("select p from Permission p where (p.code = :code or p.name = :name) and p.parentId = :parentId")
    Permission saveValidate(@Param("name") String name,@Param("code") String code,@Param("parentId") String parentId);

    @Query("select count(p.id)  from Permission p where (p.code = :code or p.name = :name) and p.parentId = :parentId and p.id <> :id")
    int updateValidate(@Param("id") String id, @Param("name") String name,@Param("code") String code,@Param("parentId") String parentId);

}
