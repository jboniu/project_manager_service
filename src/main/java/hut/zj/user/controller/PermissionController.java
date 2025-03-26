package hut.zj.user.controller;

import hut.zj.common.aop.AutoErrorhandler;
import hut.zj.common.base.V;
import hut.zj.common.result.Result;
import hut.zj.common.utils.BeanCopyUtils;
import hut.zj.common.utils.CurrentUserUtils;
import hut.zj.common.utils.tree.JsonTreeData;
import hut.zj.user.service.IPermissionService;
import hut.zj.user.entity.Permission;
import hut.zj.user.view.PermissionView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "权限")
@RequestMapping("/permission")
@RestController
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    /**
     * 根据id获取权限
     * @param id
     * @return
     */
    @ApiOperation("根据id获取权限")
    @GetMapping(value = "",params = {"id"})
    @AutoErrorhandler
    @RequiresPermissions("account:user:list")
    public Result findById(String id){
        Permission permission = permissionService.findById(id);
        Assert.notNull(permission,"权限不存在!");
        return Result.getSuccess(permission);
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @ApiOperation("删除权限")
    @DeleteMapping(value = "",params = {"id"})
    @AutoErrorhandler
    public Result delete(String id){
        Permission permission = permissionService.findById(id);
        Assert.notNull(permission,"权限不存在!");
        permissionService.delete(id);
        return Result.getSuccess();
    }

    @RequiresPermissions("account:permission:update")
    @ApiOperation(value="修改权限")
    @AutoErrorhandler
    @PutMapping("")
    public Result update(@RequestBody @Validated(V.Update.class) PermissionView permissionView, BindingResult bindingResult) throws Exception {
        //这里应该要判断权限是否重复的
        int count = permissionService.updateValidate(BeanCopyUtils.copy(permissionView,Permission.class));
        Assert.isTrue(count == 0,"该节点下已经有同名 或者 同code 的权限");
        permissionService.update(BeanCopyUtils.copy(permissionView,Permission.class));
        return Result.getSuccess();
    }


    /**
     * 添加权限
     * @return
     */
    @ApiOperation("添加权限")
    @PostMapping("")
    @AutoErrorhandler
    public Result save(@RequestBody @Validated(V.Save.class)PermissionView permissionView, BindingResult bindingResult) throws Exception {
        // 先判断再同一个父节点下面是不是有同名或者同code的权限 如果有就不允许添加
        Permission permission = permissionService.saveValidate(permissionView.getName(), permissionView.getCode(), permissionView.getParentId());
        Assert.isNull(permission,"该节点下已经有同名 或者 同code 的权限");
        return Result.getSuccess(permissionService.save(BeanCopyUtils.copy(permissionView,Permission.class)));
    }

    /**
     * 查询权限树
     * @return
     */
    @RequiresPermissions("account:permission:list")
    @ApiOperation(value = "查询权限树")
    @GetMapping("/tree")
    @AutoErrorhandler
    public Result findPermissionTree(){
        List<JsonTreeData> permissionTree = permissionService.findPermissionTree();
        return Result.getSuccess(permissionTree);
    }

    /**
     * 获取当前用户的权限code
     * @return
     */
    @ApiOperation(value = "获取当前用户的权限code")
    @GetMapping("/per_code")
    @AutoErrorhandler
    public Result getCurrentPermission(){
        return Result.getSuccess(CurrentUserUtils.getCurrentUser().getPerCodes());
    }
}
