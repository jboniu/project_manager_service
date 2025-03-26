package hut.zj.user.controller;

import hut.zj.common.aop.AutoErrorhandler;
import hut.zj.common.base.V;
import hut.zj.common.exception.CopyFailedException;
import hut.zj.common.query.Query;
import hut.zj.common.query.Sort;
import hut.zj.common.result.PageResult;
import hut.zj.common.result.Result;
import hut.zj.common.utils.BeanCopyUtils;
import hut.zj.common.utils.CurrentUserUtils;
import hut.zj.user.service.IRoleService;
import hut.zj.user.service.IUserService;
import hut.zj.user.dto.SecurityUserDto;
import hut.zj.user.entity.User;
import hut.zj.user.view.AssignmentRoleView;
import hut.zj.user.view.PasswordView;
import hut.zj.user.view.RoleView;
import hut.zj.user.view.UserView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("用户")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @ApiOperation(value="添加用户")
    @PostMapping("")
    @AutoErrorhandler
    public Result save(@RequestBody @Validated(V.Save.class)UserView user, BindingResult bindingResult){
        User nUser ;
        try {
             nUser = BeanCopyUtils.copy(user, User.class);
        } catch (Exception e) {
            throw new CopyFailedException(e.getMessage());
        }
        userService.save(nUser);
        return Result.getSuccess();
    }

    @ApiOperation(value="完善用户信息")
    @PutMapping("")
    @AutoErrorhandler
    public Result completeInfo(@RequestBody @Validated(UserView.complete.class)UserView userView,BindingResult bindingResult) throws Exception {
        User user = BeanCopyUtils.copy(userView, User.class);
        userService.update(user);
        return Result.getSuccess();
    }

    @ApiOperation(value="展示用户")
    @GetMapping("")
    @AutoErrorhandler
    public Result Query(Query query) throws Exception {
        query.addSort(Sort.Dir.desc,"createTime");
        PageResult<User> pageResult = userService.queryPage(query);
        List list = BeanCopyUtils.copyList(pageResult.getRows(), UserView.class);
        PageResult<UserView> userViewPageResult = new PageResult<>();
        userViewPageResult.setRows(list);
        return Result.getSuccess(list);
    }

    @ApiOperation(value="根据用户名删除用户")
    @DeleteMapping(value = "",params = "userName")
    public Result deleteByName(String userName){
        Assert.hasText(userName,"用户名不能为空");
        User user = userService.findByUserName(userName);
        Assert.notNull(user,"用户不存在!");
        userService.deleteByUserName(userName);
        return Result.getSuccess();
    }

    @ApiOperation(value="用户状态修改")
    @AutoErrorhandler
    @PutMapping("/status")
    public Result updateStatus(@RequestBody @Validated(UserView.UpdateStatus.class)UserView userView,BindingResult bindingResult) throws Exception {
        Assert.hasText(userView.getId(),"用户id不能为空");
        userService.updateStatusById(userView.getId(),userView.getStatus());
        return Result.getSuccess();
    }

    @ApiOperation(value="根据Id获取用户")
    @GetMapping(value = "",params = "id")
    public Result findById(String id) throws Exception {
        Assert.hasText(id,"用户id不能为空");
        User user = userService.findById(id);
        Assert.notNull(user,"用户不存在!");
        user.setPassword(null);
        return Result.getSuccess(BeanCopyUtils.copy(user,UserView.class));
    }

    @ApiOperation(value="修改密码")
    @PutMapping("/password")
    @AutoErrorhandler
    public Result updatePassword(@RequestBody @Validated(PasswordView.UpdatePassword.class) PasswordView passwordView, BindingResult bindingResult){
        User user = userService.findByUserName(passwordView.getUserName());
        Assert.notNull(user,"不存在!");
        userService.updatePassword(user,passwordView.getPassword());
        return Result.getSuccess();
    }

    /**
     * 获取当前登录用户
     * @return
     */
    @RequiresPermissions("account:user:list")
    @ApiOperation(value="获取当前登录用户")
    @GetMapping("/current_user")
    @AutoErrorhandler
    public Result getCurrentUser() throws Exception {
        SecurityUserDto currentUser = CurrentUserUtils.getCurrentUser().getSecurityUserDto();
        UserView userView = BeanCopyUtils.copy(currentUser, UserView.class);
        return Result.getSuccess(userView);
    }

    @ApiOperation(value = "去分配角色页面")
    @GetMapping(value = "/to_assignment_role",params = "id")
    public Result toAssignmentRole(String id) throws Exception {
        AssignmentRoleView assignmentRoleView = new AssignmentRoleView(BeanCopyUtils.copyList(roleService.findAll(), RoleView.class),
                roleService.findRoleIdsByUserId(id));
        return Result.getSuccess(assignmentRoleView);
    }


    @ApiOperation(value="分配角色")
    @PutMapping(value = "/assignment_role",params = {"id"})
    @AutoErrorhandler
    public Result assignmentRole(String id, @RequestBody String[] roleIds)throws Exception{
        userService.assignmentRole(id,roleIds);
        return Result.getSuccess();
    }
}
