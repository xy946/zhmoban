package com.zhzh.controller.web.system;

import com.zhzh.constants.common.UserConstants;
import com.zhzh.model.common.AjaxResult;
import com.zhzh.model.common.SysDictData;
import com.zhzh.model.common.SysDictType;
import com.zhzh.service.ISysDictTypeService;
import com.zhzh.util.common.controller.BaseController;
import com.zhzh.util.common.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典信息
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController extends BaseController
{
    @Autowired
    private ISysDictTypeService dictTypeService;

    @RequestMapping(value ="/list")
    public TableDataInfo list(SysDictType dictType)
    {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    @RequestMapping(value = "/export")
    public AjaxResult export(SysDictType dictType)
    {
        //List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        List<SysDictData> sysDictData = dictTypeService.selectDictDataByType("sys_show_hide");
        //导出逻辑
        return null;
    }

    /**
     * 查询字典类型详细
     */
    @RequestMapping(value = "/getInfo/{dictId}")
    public AjaxResult getInfo(@PathVariable Long dictId)
    {
        return AjaxResult.success(dictTypeService.selectDictTypeById(dictId));
    }

    /**
     * 新增字典类型
     */
    @RequestMapping(value = "add")
    public AjaxResult add(@Validated  SysDictType dict)
    {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict)))
        {
            return AjaxResult.error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        return toAjax(dictTypeService.insertDictType(dict));
    }

    /**
     * 修改字典类型
     */
    @RequestMapping(value = "edit")
    public AjaxResult edit(@Validated  SysDictType dict)
    {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict)))
        {
            return AjaxResult.error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
         return toAjax(dictTypeService.updateDictType(dict));
    }

    /**
     * 删除字典类型
     */
    @RequestMapping(value = "/remove")
    public AjaxResult remove( Long[] dictIds)
    {
        return toAjax(dictTypeService.deleteDictTypeByIds(dictIds));
    }

    /**
     * 清空缓存
     */
    @RequestMapping(value = "/clearCache")
    public AjaxResult clearCache()
    {
        dictTypeService.clearCache();
        return AjaxResult.success();
    }

    /**
     * 获取字典选择框列表
     */
    @RequestMapping(value = "/optionselect")
    public AjaxResult optionselect()
    {
        List<SysDictType> dictTypes = dictTypeService.selectDictTypeAll();
        return AjaxResult.success(dictTypes);
    }
}
