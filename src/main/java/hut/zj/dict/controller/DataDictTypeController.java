package hut.zj.dict.controller;

import hut.zj.common.aop.AutoErrorhandler;
import hut.zj.common.base.V;
import hut.zj.common.result.PageResult;
import hut.zj.common.result.Result;
import hut.zj.common.utils.BeanCopyUtils;
import hut.zj.dict.entity.DataDictType;
import hut.zj.dict.query.DictQuery;
import hut.zj.dict.service.IDataDictTypeService;
import hut.zj.dict.view.DataDictTypeView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("数据字典类型")
@RestController
@RequestMapping("/dict_type")
public class DataDictTypeController {

    @Autowired
    private IDataDictTypeService dataDictTypeService;

    /**
     * 添加字典类别
     * @param dataDictTypeView
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value="添加字典类别")
    @PostMapping("")
    @AutoErrorhandler
    public Result save(@RequestBody @Validated(V.Save.class)DataDictTypeView dataDictTypeView, BindingResult bindingResult) throws Exception {
        DataDictType dataDictType = dataDictTypeService.save(BeanCopyUtils.copy(dataDictTypeView, DataDictType.class));
        return Result.getSuccess(dataDictType);
    }

    /**
     * 修改字典类别
     * @param dataDictTypeView
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value="修改字典类别")
    @PutMapping("")
    @AutoErrorhandler
    public Result update(@RequestBody @Validated(V.Update.class)DataDictTypeView dataDictTypeView, BindingResult bindingResult) throws Exception {
        DataDictType updated = dataDictTypeService.update(BeanCopyUtils.copy(dataDictTypeView, DataDictType.class));
        return Result.getSuccess(updated);
    }

    /**
     * 字典类别列表
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value="字典类别列表")
    @GetMapping("/query")
    public Result query( DictQuery query) throws Exception {
        PageResult pageResult = dataDictTypeService.queryPage(query);
        List<DataDictType> rows = pageResult.getRows();
        pageResult.setRows(BeanCopyUtils.copyList(rows,DataDictTypeView.class));
        return Result.getSuccess(pageResult);
    }

    /**
     * 根据id获取字典类别
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id获取字典类别")
    @GetMapping(value = "",params = {"id"})
    public Result findById(String id) {
        DataDictType dataDictType = dataDictTypeService.findById(id);
        Assert.notNull(dataDictType,"字典类别不存在!");
        return Result.getSuccess(dataDictType);
    }

    /**
     * 数据字典类别删除
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value="数据字典类别删除")
    @DeleteMapping(value = "",params = {"id"})
    public Result deleteById(String id) throws Exception {
        dataDictTypeService.delete(id);
        return Result.getSuccess();
    }
}
