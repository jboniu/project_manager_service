package hut.zj.project.query;

import hut.zj.common.query.Filter;
import hut.zj.common.query.Query;
import hut.zj.common.utils.CurrentUserUtils;
import hut.zj.user.entity.Role;

import java.util.List;

public class ApplicationQuery extends Query{
    @Override
    public void generateCondition() {
        //如果不是科研工作者 就不能看见所有人的申请(暂时这样)
        List<Role> roles = CurrentUserUtils.getCurrentUser().getRoles();
        if(!roles.isEmpty()){
            for(Role role :roles){
                if(!role.getName().equals("科研管理员")){
                    addFilter(new Filter("applicant", Filter.Operator.eq, CurrentUserUtils.getCurrentUser().getSecurityUserDto().getUserName()));
                }
            }
        }


    }
}
