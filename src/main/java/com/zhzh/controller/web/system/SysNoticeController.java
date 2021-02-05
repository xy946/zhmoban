package com.zhzh.controller.web.system;

import com.zhzh.model.common.AjaxResult;
import com.zhzh.model.system.SysNotice;
import com.zhzh.service.ISysNoticeService;
import com.zhzh.util.common.controller.BaseController;
import com.zhzh.util.common.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告 信息操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController
{
    @Autowired
    private ISysNoticeService noticeService;

    /**
     * 获取通知公告列表
     */
    @RequestMapping(value = "/list")
    public TableDataInfo list(SysNotice notice)
    {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @RequestMapping(value = "/getInfo")
    public AjaxResult getInfo( Long noticeId)
    {
        return AjaxResult.success(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 新增通知公告
     */
    @RequestMapping(value = "/add")
    public AjaxResult add(@Validated  SysNotice notice)
    {
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告
     */
    @RequestMapping(value = "/edit")
    public AjaxResult edit(@Validated  SysNotice notice)
    {
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告
     */
    @RequestMapping(value="/remove/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds)
    {
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }
}
