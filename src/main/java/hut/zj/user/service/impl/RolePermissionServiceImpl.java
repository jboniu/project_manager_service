package hut.zj.user.service.impl;

import hut.zj.common.base.dao.BaseRepository;
import hut.zj.common.base.service.Impl.BaseServiceImpl;
import hut.zj.user.entity.RolePermission;
import hut.zj.user.service.IRolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission> implements IRolePermissionService {

    public RolePermissionServiceImpl(BaseRepository<RolePermission> repository) {
        super(repository);
    }
}
