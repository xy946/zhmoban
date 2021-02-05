package com.zhzh.controller.web.system;

import com.zhzh.constants.common.UserConstants;
import com.zhzh.model.common.AjaxResult;
import com.zhzh.model.common.SysRole;
import com.zhzh.model.common.SysUser;
import com.zhzh.service.ISysPostService;
import com.zhzh.service.ISysRoleService;
import com.zhzh.service.ISysUserService;
import com.zhzh.util.common.StringUtils;
import com.zhzh.util.common.controller.BaseController;
import com.zhzh.util.common.page.TableDataInfo;
import com.zhzh.util.otherUtil.UserCommonsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息
 *
 * @author yanfuxing
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    /**
     * 获取用户列表
     */
    @RequestMapping("/list")
    public TableDataInfo list(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @RequestMapping("/export")
    public AjaxResult export(SysUser user)
    {
        List<SysUser> list = userService.selectUserList(user);
        //导出的逻辑
        return  null;
    }

    @RequestMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        //导入的逻辑
        return  null;
    }

    @RequestMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        //导入模板
        return  null;
    }

    /**
     * 根据用户编号获取详细信息
     */
    @RequestMapping(value = { "/getInfo" })
    public AjaxResult getInfo( Long userId)
    {
        AjaxResult ajax = AjaxResult.success();
        List<SysRole> roles = roleService.selectRoleAll();
        ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId))
        {
            ajax.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }

    /**
     * 新增用户
     */
    @RequestMapping(value = { "/add" })
    public AjaxResult add(@Validated  SysUser user)
    {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName())))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        user.setPassword(UserCommonsUtil.Md5Util.MD5Encode("123456"));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @RequestMapping(value = { "/edit" })
    public AjaxResult edit(@Validated SysUser user)
    {
        userService.checkUserAllowed(user);
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/remove/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @RequestMapping(value ="/resetPwd")
    public AjaxResult resetPwd( SysUser user)
    {
        userService.checkUserAllowed(user);
        user.setPassword(UserCommonsUtil.Md5Util.MD5Encode(user.getPassword()));
        return toAjax(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @RequestMapping(value ="/changeStatus")
    public AjaxResult changeStatus( SysUser user)
    {
        userService.checkUserAllowed(user);
        return toAjax(userService.updateUserStatus(user));
    }
}
