package hut.zj.user.service.impl;

import hut.zj.common.base.dao.BaseRepository;
import hut.zj.common.base.service.Impl.BaseServiceImpl;
import hut.zj.common.constant.UserStatus;
import hut.zj.common.constant.UserType;
import hut.zj.common.exception.CopyFailedException;
import hut.zj.common.utils.BeanCopyUtils;
import hut.zj.user.service.IUserService;
import hut.zj.user.dao.UserPermissionRepository;
import hut.zj.user.dao.UserRepository;
import hut.zj.user.dao.UserRoleRepository;
import hut.zj.user.dto.SecurityUserDto;
import hut.zj.user.entity.Role;
import hut.zj.user.entity.User;
import hut.zj.user.entity.UserRole;
import hut.zj.user.utils.UserUtil;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BaseRepository<User> baseRepository;

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserServiceImpl(BaseRepository<User> repository) {
        super(repository);
    }

    private RandomNumberGenerator randomNumberGenerator =
            new SecureRandomNumberGenerator();

    @Override
    public SecurityUserDto login(String loginName, String password){
        SecurityUserDto securityUserDto = new SecurityUserDto();
        User user = userRepository.findByUserName(loginName);
        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("账号不存在!");
        }
        password = new Md5Hash(password, user.getSalt()).toString();
        //密码错误
        if(!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("密码错误!");
        }
        //账号锁定
        if(user.getStatus().equals(UserStatus.LOCK.getCode())){
            throw new LockedAccountException("账号已经被锁定,请联系管理员!");
        }
        try {
            securityUserDto = BeanCopyUtils.copy(user,SecurityUserDto.class);
        }catch (Exception e){
            throw new CopyFailedException(e.getMessage());
        }
        return securityUserDto;
    }

    @Override
    public User save(User user){
        User oUser = this.findByUserName(user.getUserName());
        Assert.isNull(oUser,"用户名已经存在!");
        //设置用户状态 开启
        user.setStatus(UserStatus.UNLOCK.getCode());
        user.setCreateTime(new Date());
        if(StringUtils.isEmpty(user.getType())) {
            //设置用户类型 默认为普通用户
            user.setType(UserType.USER.getCode());
        }
        //对密码进行加盐
        String password = user.getPassword();
        String salt = randomNumberGenerator.nextBytes().toHex();
        user.setSalt(salt);
        password = new Md5Hash(password, salt).toString();
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public void deleteByUserName(String name) {
        User user = userRepository.findByUserName(name);
        // 删除用户
        userRepository.deleteByUserName(name);
        // 删除用户角色中间关系表
        userRoleRepository.deleteByUserId(user.getId());
    }

    @Override
    public void updateStatusById(String id, String status) {
        User user = userRepository.findById(id);
        Assert.isTrue(!UserType.isAdmin(user.getType()),"超级管理员无法禁用");
        userRepository.updateStatusById(id,status);
    }

    @Override
    public void updatePassword(User user, String password) {
        String salt = user.getSalt();
        password = new Md5Hash(password, salt).toString();
        user.setPassword(password);
        baseRepository.save(user);
    }

    @Override
    public List<Role> findRoleByUserId(String id) {
        return userRepository.findRoleByUserId(id);
    }

    @Override
    public Set<String> findPerByUserId(String id) {
        return userRepository.findPerByUserId(id);
    }

    @Override
    @Transactional
    public void assignmentRole(String id, String[] roleIds) {
        User user = userRepository.findById(id);
        Assert.notNull(user,"用户不存在!");
        // 删除之前的用户角色关系
        userRoleRepository.deleteByUserId(id);
        // 分配新的角色
        if(roleIds.length>0) {
            List<UserRole> userRoleList = UserRole.getUserRoleList(id, roleIds);
            userRoleRepository.save(userRoleList);
        }
    }

    @Override
    public User update(User user){
        User oldUser = userRepository.findById(user.getId());
        Assert.notNull(oldUser,"用户不存在!");
        oldUser = UserUtil.updateUser(user,oldUser);
        return baseRepository.save(oldUser);
    }

}
