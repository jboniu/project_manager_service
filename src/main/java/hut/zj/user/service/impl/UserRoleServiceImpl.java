package hut.zj.user.service.impl;

import hut.zj.common.base.dao.BaseRepository;
import hut.zj.common.base.service.Impl.BaseServiceImpl;
import hut.zj.user.service.IUserRoleService;
import hut.zj.user.dao.UserRoleRepository;
import hut.zj.user.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements IUserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(BaseRepository<UserRole> repository) {
        super(repository);
    }

    @Override
    public Set<String> findRoleIdsByUserId(String id) {
        return userRoleRepository.findByUserId(id);
    }
}
