package hut.zj.user.service;

import hut.zj.common.base.service.IBaseService;
import hut.zj.user.dto.SecurityUserDto;
import hut.zj.user.entity.Role;
import hut.zj.user.entity.User;

import java.util.List;
import java.util.Set;

public interface IUserService extends IBaseService<User> {
    /**
     * 登陆
     * @param loginName 用户名
     * @param password 密码
     * @return
     */
    SecurityUserDto login(String loginName, String password);

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 根据用户名删除
     * @param name
     */
    void deleteByUserName(String name);

    /**
     * 修改用户状态
     * @param id
     * @param status
     */
    void updateStatusById(String id, String status);

    /**
     * 修改密码
     * @param user
     * @param password
     */
    void updatePassword(User user, String password);

    /**
     * 根据用户id查询用户已有的角色
     * @param id
     * @return
     */
    List<Role> findRoleByUserId(String id);

    /**
     * 根据用户id 查询用户所有权限
     * @param id
     * @return
     */
    Set<String> findPerByUserId(String id);

    /**
     * 分配角色
     * @param id
     * @param roleIds
     */
    void assignmentRole(String id, String[] roleIds);
}
