package com.zhzh.controller.web.system;

import com.alibaba.fastjson.JSONObject;
import com.zhzh.constants.common.Constants;
import com.zhzh.model.common.AjaxResult;
import com.zhzh.model.common.SysMenu;
import com.zhzh.model.common.SysUser;
import com.zhzh.model.common.model.LoginBody;
import com.zhzh.service.ISysMenuService;
import com.zhzh.service.ISysRoleService;
import com.zhzh.service.ISysUserService;
import com.zhzh.service.impl.RedisServiceImpl;
import com.zhzh.util.exception.GlobalException;
import com.zhzh.util.imexdw.WordUtil;
import com.zhzh.util.model.ResultVO;
import com.zhzh.util.model.StatusCode;
import com.zhzh.util.otherUtil.UserCommonsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@RestController
public class SysLoginController{

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private ISysRoleService iSysRoleService;

    @Autowired
    private ISysMenuService iSysMenuService;

    @Autowired
    private RedisServiceImpl redisService;

    /**
     * 登录方法
     * 
     * @param
     * @return 结果
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AjaxResult login(LoginBody loginBody, HttpServletRequest request)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        SysUser sysUser = iSysUserService.selectUserByUserName(loginBody.getUsername());
        if(sysUser==null){
            throw  new GlobalException("账号不存在");
        }
        if(!UserCommonsUtil.Md5Util.MD5Encode(loginBody.getPassword()).equals(sysUser.getPassword())){
            throw  new GlobalException("密码不正确");
        }
       //删除当前账号已存在的token 再添加
        String s = sysUser.getUserId().toString();
        redisService.removeById("userId=" + s);
        String token = UserCommonsUtil.TokenUtil.createToken(Integer.parseInt(sysUser.getUserId().toString()));
        //添加缓存
        JSONObject jo= (JSONObject) JSONObject.toJSON(sysUser);
        boolean status = redisService.setNx(token, jo, (long) Constants.REDIS_TIME_OUT);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @RequestMapping("/getInfo")
    public void getInfo(HttpServletRequest request,HttpServletResponse response)
    {
        String token = request.getHeader(Constants.SESSION_KEY);
        if(token==null || token.equals("")){
            throw  new GlobalException("token不能为空");
        }
        JSONObject obj = (JSONObject)redisService.get(token);
        if(obj==null){
            throw  new GlobalException("token失效");
        }
        SysUser sysUser =  JSONObject.toJavaObject(obj, SysUser.class);
        // 角色集合
        Set<String> roles = iSysRoleService.getRolesByUser(sysUser);
        // 权限集合
        Set<String> permissions = iSysMenuService.getMenusByUser(sysUser);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", sysUser);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        AjaxResult ajaxw = new AjaxResult();
        ajaxw.put("userName",sysUser.getUserName());
        ajaxw.put("phonenumber",sysUser.getPhonenumber());
        WordUtil.exportWord(response,ajaxw);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @RequestMapping("/getRouters")
    public AjaxResult getRouters(HttpServletRequest request)
    {
        String token = request.getHeader(Constants.SESSION_KEY);
        if(token==null || token.equals("")){
            throw  new GlobalException("token不能为空");
        }
        JSONObject obj = (JSONObject)redisService.get(token);
        if(obj==null){
            throw  new GlobalException("token失效");
        }
        SysUser sysUser =  JSONObject.toJavaObject(obj, SysUser.class);
        List<SysMenu> menus = iSysMenuService.selectMenuTreeByUserId(sysUser.getUserId());
        return AjaxResult.success(iSysMenuService.buildMenus(menus));
    }

    @RequestMapping("/loginout")
    public void loginout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader(Constants.SESSION_KEY);
        redisService.remove(token);
        response.sendRedirect("192.168.1.5:80");
    }

    @RequestMapping("/tokenError")
    public ResultVO tokenError()  {
//        response.sendRedirect(request.getContextPath() + "/error");
        return new ResultVO(StatusCode.TOKEN_ERROR);
    }

    /**
     * 修改密码
     *
     * @param
     * @return 结果
     */
    @RequestMapping("/updatePassword")
    public AjaxResult updatePassword(LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        SysUser sysUser = iSysUserService.selectUserByUserName(loginBody.getUsername());

        if(!UserCommonsUtil.Md5Util.MD5Encode(loginBody.getPassword()).equals(sysUser.getBeforePassword())){
            throw  new GlobalException("原密码不正确");
        }
        //修改密码
        iSysUserService.resetUserPwd(loginBody.getUsername(),loginBody.getPassword());

        return ajax;
    }

}
