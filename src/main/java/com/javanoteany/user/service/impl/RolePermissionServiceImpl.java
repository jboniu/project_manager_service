package com.javanoteany.user.service.impl;

import com.javanoteany.common.base.dao.BaseRepository;
import com.javanoteany.common.base.service.Impl.BaseServiceImpl;
import com.javanoteany.user.entity.RolePermission;
import com.javanoteany.user.service.IRolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission> implements IRolePermissionService {

    public RolePermissionServiceImpl(BaseRepository<RolePermission> repository) {
        super(repository);
    }
}
