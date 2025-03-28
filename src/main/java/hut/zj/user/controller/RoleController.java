package hut.zj.user.controller;

import hut.zj.common.aop.AutoErrorhandler;
import hut.zj.common.base.V;
import hut.zj.common.query.Query;
import hut.zj.common.result.PageResult;
import hut.zj.common.result.Result;
import hut.zj.common.utils.BeanCopyUtils;
import hut.zj.common.utils.tree.JsonTreeData;
import hut.zj.user.service.IPermissionService;
import hut.zj.user.service.IRoleService;
import hut.zj.user.entity.Role;
import hut.zj.user.view.AssignmentPermissionView;
import hut.zj.user.view.RoleView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Api(value = "角色")
@RequestMapping("/role")
@RestController
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @PostMapping("")
    @ApiOperation(value = "保存角色")
    @AutoErrorhandler
    public Result save(@RequestBody @Validated(V.Save.class) RoleView roleView , BindingResult bindingResult) throws Exception {
        Assert.isNull(roleService.findByName(roleView.getName()),"角色"+roleView.getName()+"已经存在!");
        return Result.getSuccess(roleService.save(BeanCopyUtils.copy(roleView, Role.class)));
    }

    @PutMapping("")
    @ApiOperation(value = "修改角色")
    @AutoErrorhandler
    public Result update(@RequestBody @Validated(V.Update.class) RoleView roleView , BindingResult bindingResult) throws Exception {
        Assert.notNull(roleService.findById(roleView.getId()),"角色"+roleView.getName()+"不存在!");
        Assert.isNull( roleService.findByName(roleView.getName()),"角色已经存在!");
        return Result.getSuccess(roleService.update(BeanCopyUtils.copy(roleView, Role.class)));
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询角色")
    @AutoErrorhandler
    public Result query(Query query){
        PageResult pageResult = roleService.queryPage(query);
        return Result.getSuccess(pageResult);
    }

    @GetMapping(value = "",params = {"id"})
    @AutoErrorhandler
    @ApiOperation("根据id查询角色")
    public Result findById(String id){
        Role role = roleService.findById(id);
        Assert.notNull(role,"角色不存在");
        return Result.getSuccess(role);
    }

    @ApiOperation(value = "角色删除")
    @DeleteMapping(value = "",params = {"id"})
    @AutoErrorhandler
    public Result delete(String id){
        Role role = roleService.findById(id);
        Assert.notNull(role,"角色不存在!");
        roleService.delete(id);
        return Result.getSuccess();
    }

    @ApiOperation(value = "去分配权限的页面")
    @GetMapping(value = "/to_assignment",params = {"id"})
    @AutoErrorhandler
    public Result toAssignmentPermission(String id){
        // 查询所有的权限树　
        List<JsonTreeData> permissionTree = permissionService.findPermissionTree();
        // 查询角色已经有的权限ids
        Set<String> rolePermissionIds = roleService.findPermissionIdsByid(id);
        return Result.getSuccess(new AssignmentPermissionView(permissionTree,rolePermissionIds));
    }

    @ApiOperation(value="分配权限")
    @PutMapping(value = "/assignment",params = {"id"})
    @AutoErrorhandler
    public Result assignmentPermission(String id, @RequestBody String[] permissionIds)throws Exception{
        roleService.assignmentPermission(id,permissionIds);
        return Result.getSuccess();
    }
}
