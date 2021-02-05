package com.zhzh.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;
/**
 * 用户对象 sys_user
 * 
 * @author ruoyi
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "用户列表")
public class SysUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long userId;

    /** 员工id */
    private Integer staffId;

    /** 部门ID */
    @ApiModelProperty(value = "部门ID" ,example = "1")
    private Long deptId;

    /** 用户账号 */
    @ApiModelProperty(value = "用户账号 " ,example = "1")
    private String userName;

    /** 用户昵称 */
    @ApiModelProperty(value = "用户昵称 " ,example = "1")
    private String nickName;

    /** 用户邮箱 */
    @ApiModelProperty(value = "用户邮箱 " ,example = "1")
    private String email;

    /** 手机号码 */
    @ApiModelProperty(value = "手机号码 " ,example = "1")
    private String phonenumber;

    /** 用户性别 */
    @ApiModelProperty(value = "用户性别", example = "0=男,1=女,2=未知")
    private String sex;

    /** 用户头像 */
    @ApiModelProperty(value = "用户头像", example = "")
    private String avatar;

    /** 密码 */
    @ApiModelProperty(value = "密码", example = "")
    private String password;

    /** 盐加密 */
    @ApiModelProperty(value = "盐加密", example = "")
    private String salt;

    /** 帐号状态（0正常 1停用） */
    @ApiModelProperty(value = "帐号状态", example = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty(value = "删除标志", example = "0代表存在 2代表删除")
    private String delFlag;

    /** 最后登陆IP */
    @ApiModelProperty(value = "最后登陆IP", example = "")
    private String loginIp;

    /** 最后登陆时间 */
    @ApiModelProperty(value = "最后登陆时间", example="")
    private Date loginDate;


    private SysDept dept;

    /**原密码*/
    private String beforePassword;

    /** 角色对象 */
    private List<SysRole> roles;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;

    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }


    public SysUser(Long userId)
    {
        this.userId = userId;
    }
    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    public SysDept getDept() {
        return dept;
    }



    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds() {
        return postIds;
    }

    public void setPostIds(Long[] postIds) {
        this.postIds = postIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("userName", getUserName())
            .append("nickName", getNickName())
            .append("email", getEmail())
            .append("phonenumber", getPhonenumber())
            .append("sex", getSex())
            .append("avatar", getAvatar())
            .append("password", getPassword())
            .append("salt", getSalt())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("loginIp", getLoginIp())
            .append("loginDate", getLoginDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("dept", getDept())
            .toString();
    }
}
