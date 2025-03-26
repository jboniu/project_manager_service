package hut.zj.project.controller;

import hut.zj.common.aop.AutoErrorhandler;
import hut.zj.common.base.V;
import hut.zj.common.result.PageResult;
import hut.zj.common.result.Result;
import hut.zj.common.utils.BeanCopyUtils;
import hut.zj.project.entity.UpdateApplication;
import hut.zj.project.query.ApplicationQuery;
import hut.zj.project.service.IUpdateApplicationService;
import hut.zj.project.view.UpdateApplicationView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("变更申请")
@RestController
@RequestMapping("/update_application")
public class UpdateApplicationController {

    @Autowired
    private IUpdateApplicationService updateApplicationService;

    @ApiOperation(value = "保存变更申请")
    @PostMapping("")
    @AutoErrorhandler
    public Result save(@RequestBody @Validated(V.Save.class) UpdateApplicationView updateApplicationView, BindingResult bindingResult) throws Exception {
        UpdateApplication updateApplication = BeanCopyUtils.copy(updateApplicationView, UpdateApplication.class);
        return Result.getSuccess(updateApplicationService.toApplication(updateApplication));
    }

    @ApiOperation(value = "变更申请通过")
    @PutMapping(value = "/pass")
    @AutoErrorhandler
    public Result applicationPass(@RequestBody @Validated(UpdateApplicationView.ApplicationPass.class) UpdateApplicationView updateApplicationView, BindingResult bindingResult){
        UpdateApplication updateApplication = updateApplicationService.findById(updateApplicationView.getId());
        Assert.notNull(updateApplication,"变更申请不存在!");
        updateApplication.setBackInformation(updateApplicationView.getBackInformation());
        return Result.getSuccess(updateApplicationService.applicationPass(updateApplication));
    }

    @ApiOperation(value = "变更未申请通过")
    @PutMapping(value = "/not_pass")
    @AutoErrorhandler
    public Result applicationNotPass(@RequestBody @Validated(UpdateApplicationView.ApplicationNotPass.class) UpdateApplicationView updateApplicationView, BindingResult bindingResult){
        UpdateApplication updateApplication = updateApplicationService.findById(updateApplicationView.getId());
        Assert.notNull(updateApplication,"变更申请不存在!");
        updateApplication.setBackInformation(updateApplicationView.getBackInformation());
        return Result.getSuccess(updateApplicationService.applicationNotPass(updateApplication));
    }

    @ApiOperation(value = "查询申请变更")
    @GetMapping("/query")
    @AutoErrorhandler
    public Result query(ApplicationQuery query){
        PageResult pageResult = updateApplicationService.queryPage(query);
        return Result.getSuccess(pageResult);
    }

    @ApiOperation("根据id查询变更申请")
    @GetMapping(value = "",params = {"id"})
    @AutoErrorhandler
    public Result findById(String id){
        UpdateApplication updateApplication = updateApplicationService.findById(id);
        Assert.notNull(updateApplication,"变更申请不存在!");
        return Result.getSuccess(updateApplication);
    }
}