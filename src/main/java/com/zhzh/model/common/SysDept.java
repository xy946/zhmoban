package com.zhzh.model.common;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 部门表 sys_dept
 * 
 * @author ruoyi
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "部门表")
public class SysDept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    @ApiModelProperty(value = "部门ID" ,example = "")
    private Long deptId;

    /** 父部门ID */
    @ApiModelProperty(value = "父部门ID" ,example = "")
    private Long parentId;

    /** 祖级列表 */
    @ApiModelProperty(value = "祖级列表" ,example = "")
    private String ancestors;

    /** 部门名称 */
    @ApiModelProperty(value = "部门名称" ,example = "")
    private String deptName;

    /** 显示顺序 */
    @ApiModelProperty(value = "显示顺序" ,example = "")
    private String orderNum;

    /** 负责人 */
    @ApiModelProperty(value = "负责人" ,example = "")
    private String leader;

    /** 联系电话 */
    @ApiModelProperty(value = "联系电话" ,example = "")
    private String phone;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱" ,example = "")
    private String email;

    /** 部门状态:0正常,1停用 */
    @ApiModelProperty(value = "部门状态" ,example = "0正常,1停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty(value = "删除标志" ,example = "0代表存在 2代表删除")
    private String delFlag;

    /** 父部门名称 */
    @ApiModelProperty(value = "父部门名称" ,example = "")
    private String parentName;
    
    /** 子部门 */
    @ApiModelProperty(value = "子部门" ,example = "")
    private List<SysDept> children = new ArrayList<SysDept>();

    public List<SysDept> getChildren()
    {
        return children;
    }

    public void setChildren(List<SysDept> children)
    {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("deptName", getDeptName())
            .append("orderNum", getOrderNum())
            .append("leader", getLeader())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
