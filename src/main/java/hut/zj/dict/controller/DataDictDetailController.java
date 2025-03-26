package hut.zj.dict.controller;

import hut.zj.common.aop.AutoErrorhandler;
import hut.zj.common.base.V;
import hut.zj.common.result.PageResult;
import hut.zj.common.result.Result;
import hut.zj.common.utils.BeanCopyUtils;
import hut.zj.dict.entity.DataDictDetail;
import hut.zj.dict.query.DictQuery;
import hut.zj.dict.service.IDataDictDetailService;
import hut.zj.dict.view.DataDictView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("数据字典明细")
@RestController
@RequestMapping("/dict_detail")
public class DataDictDetailController {

    @Autowired
    private IDataDictDetailService dataDictionaryService;

    @ApiOperation(value="添加字典明细")
    @PostMapping("")
    @AutoErrorhandler
    public Result save(@RequestBody @Validated(V.Save.class)DataDictView dataDictView, BindingResult bindingResult) throws Exception {
        DataDictDetail saved = dataDictionaryService.save(BeanCopyUtils.copy(dataDictView, DataDictDetail.class));
        return Result.getSuccess(saved);
    }

    @ApiOperation(value="修改字典明细")
    @PutMapping("")
    @AutoErrorhandler
    public Result update(@RequestBody @Validated(V.Save.class)DataDictView dataDictView, BindingResult bindingResult) throws Exception {
        DataDictDetail updated = dataDictionaryService.update(BeanCopyUtils.copy(dataDictView, DataDictDetail.class));
        return Result.getSuccess(updated);
    }

    @ApiOperation(value="字典明细列表")
    @GetMapping("/query")
    public Result list(DictQuery query) throws Exception {
        PageResult pageResult = dataDictionaryService.queryPage(query);
        List<DataDictDetail> rows = pageResult.getRows();
        pageResult.setRows(BeanCopyUtils.copyList(rows,DataDictView.class));
        return Result.getSuccess(pageResult);
    }

    @ApiOperation(value = "根据id查询字典明细")
    @GetMapping(value = "",params = {"id"})
    public Result findById(String id) throws Exception {
        DataDictDetail dataDictDetail = dataDictionaryService.findById(id);
        Assert.notNull(dataDictDetail,"字典明细不存在");
        return Result.getSuccess(BeanCopyUtils.copy(dataDictDetail,DataDictView.class));
    }

    @ApiOperation(value = "删除字典明细")
    @DeleteMapping(value = "",params = {"id"})
    public Result delete(String id)  {
        dataDictionaryService.delete(id);
        return Result.getSuccess();
    }
}
