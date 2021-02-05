package com.zhzh.controller.web.system;

import com.alibaba.fastjson.JSONArray;
import com.zhzh.model.common.AjaxResult;
import com.zhzh.model.common.SysDictData;
import com.zhzh.service.ISysDictDataService;
import com.zhzh.service.ISysDictTypeService;
import com.zhzh.service.impl.RedisServiceImpl;
import com.zhzh.util.common.controller.BaseController;
import com.zhzh.util.common.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典信息
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {
    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private ISysDictTypeService dictTypeService;

    @Autowired
    private RedisServiceImpl redisServiceImpl;

    @RequestMapping("/list")
    public TableDataInfo list(SysDictData dictData)
    {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    @RequestMapping("/export")
    public AjaxResult export(SysDictData dictData)
    {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        //导出
        return null;
    }

    /**
     * 查询字典数据详细
     */
    @RequestMapping(value = "/particular")
    public AjaxResult getInfo( Long dictCode)
    {
        return AjaxResult.success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @RequestMapping(value = "/type")
    public AjaxResult dictType(String dictType) {
        return AjaxResult.success(dictTypeService.selectDictDataByType(dictType));
    }

    /**
     * 新增字典类型
     */
    @RequestMapping(value = "/add")
    public AjaxResult add(@Validated  SysDictData dict)
    {
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @RequestMapping(value = "/edit")
    public AjaxResult edit(@Validated  SysDictData dict)
    {
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @RequestMapping("/remove/{dictCodes}")
    public AjaxResult remove( @PathVariable Long[] dictCodes) {
        return toAjax(dictDataService.deleteDictDataByIds(dictCodes));
    }
}
