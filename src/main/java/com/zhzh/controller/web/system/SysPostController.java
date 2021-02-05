package com.zhzh.controller.web.system;

import com.zhzh.constants.common.UserConstants;
import com.zhzh.model.common.AjaxResult;
import com.zhzh.model.system.SysPost;
import com.zhzh.service.ISysPostService;
import com.zhzh.util.common.controller.BaseController;
import com.zhzh.util.common.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位信息操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/post")
public class SysPostController extends BaseController
{
    @Autowired
    private ISysPostService postService;
    private SysPost post;

    /**
     * 获取岗位列表
     */
    @RequestMapping("/list")
    public TableDataInfo list(SysPost post)
    {
        this.post = post;
        startPage();
        List<SysPost> list = postService.selectPostList(post);
        return getDataTable(list);
    }

    @RequestMapping("/export")
    public AjaxResult export(SysPost post)
    {
        List<SysPost> list = postService.selectPostList(post);
        //导出逻辑
        return  null;
    }

    /**
     * 根据岗位编号获取详细信息
     */
    @RequestMapping(value = "/getInfo")
    public AjaxResult getInfo(Long postId)
    {
        return AjaxResult.success(postService.selectPostById(postId));
    }

    /**
     * 新增岗位
     */
    @RequestMapping(value = "/add")
    public AjaxResult add(@Validated  SysPost post)
    {
        if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post)))
        {
            return AjaxResult.error("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post)))
        {
            return AjaxResult.error("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        return toAjax(postService.insertPost(post));
    }

    /**
     * 修改岗位
     */
    @RequestMapping(value = "/edit")
    public AjaxResult edit(@Validated  SysPost post)
    {
        if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post)))
        {
            return AjaxResult.error("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post)))
        {
            return AjaxResult.error("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        return toAjax(postService.updatePost(post));
    }

    /**
     * 删除岗位
     */
    @RequestMapping(value = "/remove/{postIds}")
    public AjaxResult remove(@PathVariable Long[] postIds)
    {
        return toAjax(postService.deletePostByIds(postIds));
    }

    /**
     * 获取岗位选择框列表
     */
    @RequestMapping("/optionselect")
    public AjaxResult optionselect()
    {
        List<SysPost> posts = postService.selectPostAll();
        return AjaxResult.success(posts);
    }
}
