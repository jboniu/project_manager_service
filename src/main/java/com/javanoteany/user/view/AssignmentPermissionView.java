package com.javanoteany.user.view;

import com.javanoteany.common.utils.tree.JsonTreeData;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AssignmentPermissionView {
    @ApiModelProperty("所有权限")
    private List<JsonTreeData> permissionTree  = new ArrayList<>();

    @ApiModelProperty("角色已经有了的权限")
    private Set<String> rolePermissionIds;

    public AssignmentPermissionView(List<JsonTreeData> permissionTree, Set<String> rolePermissionIds) {
        this.permissionTree = permissionTree;
        this.rolePermissionIds = rolePermissionIds;
    }

    public List<JsonTreeData> getPermissionTree() {
        return permissionTree;
    }

    public void setPermissionTree(List<JsonTreeData> permissionTree) {
        this.permissionTree = permissionTree;
    }

    public Set<String> getRolePermissionIds() {
        return rolePermissionIds;
    }

    public void setRolePermissionIds(Set<String> rolePermissionIds) {
        this.rolePermissionIds = rolePermissionIds;
    }
}
