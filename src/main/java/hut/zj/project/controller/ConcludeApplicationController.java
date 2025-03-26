package hut.zj.project.controller;

import hut.zj.common.aop.AutoErrorhandler;
import hut.zj.common.base.V;
import hut.zj.common.result.PageResult;
import hut.zj.common.result.Result;
import hut.zj.common.utils.BeanCopyUtils;
import hut.zj.project.entity.ConcludeApplication;
import hut.zj.project.query.ApplicationQuery;
import hut.zj.project.service.IConcludeApplicationService;
import hut.zj.project.view.ConcludeApplicationView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("结题申请")
@RestController
@RequestMapping(value = "conclude_application")
public class ConcludeApplicationController {

    @Autowired
    private IConcludeApplicationService concludeApplicationService;

    @ApiOperation("结题申请")
    @PostMapping("")
    @AutoErrorhandler
    public Result save(@RequestBody @Validated(V.Save.class) ConcludeApplicationView concludeApplicationView, BindingResult bindingResult)throws Exception{
        ConcludeApplication concludeApplication = BeanCopyUtils.copy(concludeApplicationView, ConcludeApplication.class);
        return Result.getSuccess(concludeApplicationService.toApplication(concludeApplication));
    }


    @ApiOperation(value = "结题申请通过")
    @PutMapping(value = "/pass")
    @AutoErrorhandler
    public Result applicationPass(@RequestBody @Validated(ConcludeApplicationView.ApplicationPass.class) ConcludeApplicationView concludeApplicationView, BindingResult bindingResult){
        ConcludeApplication concludeApplication = concludeApplicationService.findById(concludeApplicationView.getId());
        Assert.notNull(concludeApplication,"结题申请不存在!");
        concludeApplication.setBackInformation(concludeApplicationView.getBackInformation());
        return Result.getSuccess(concludeApplicationService.applicationPass(concludeApplication));
    }

    @ApiOperation(value = "结题申请未通过")
    @PutMapping(value = "/not_pass")
    @AutoErrorhandler
    public Result applicationNotPass(@RequestBody @Validated(ConcludeApplicationView.ApplicationNotPass.class) ConcludeApplicationView concludeApplicationView, BindingResult bindingResult){
        ConcludeApplication concludeApplication = concludeApplicationService.findById(concludeApplicationView.getId());
        Assert.notNull(concludeApplication,"结题申请不存在!");
        concludeApplication.setBackInformation(concludeApplicationView.getBackInformation());
        return Result.getSuccess(concludeApplicationService.applicationNotPass(concludeApplication));
    }

    @ApiOperation(value = "查询结题申请")
    @GetMapping("/query")
    @AutoErrorhandler
    public Result query(ApplicationQuery query){
        PageResult pageResult = concludeApplicationService.queryPage(query);
        return Result.getSuccess(pageResult);
    }

    @ApiOperation(value = "根据id获取结题申请")
    @GetMapping(value = "",params = {"id"})
    @AutoErrorhandler
    public Result findById(String id){
        ConcludeApplication concludeApplication = concludeApplicationService.findById(id);
        Assert.notNull(concludeApplication,"结题申请不能为空!");
        return Result.getSuccess(concludeApplication);
    }
}
