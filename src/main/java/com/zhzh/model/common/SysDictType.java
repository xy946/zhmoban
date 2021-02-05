package com.zhzh.model.common;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 字典类型表 sys_dict_type
 * 
 * @author ruoyi
 */
@AllArgsConstructor
@Data
@ApiModel(value = "字典类型表")
public class SysDictType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 字典主键 */
    @ApiModelProperty(value = "字典主键" ,example = "")
    private Long dictId;

    /** 字典名称 */
    @ApiModelProperty(value = "字典名称" ,example = "")
    private String dictName;

    /** 字典类型 */
    @ApiModelProperty(value = "字典类型" ,example = "")
    private String dictType;

    /** 状态（0正常 1停用） */
    @ApiModelProperty(value = "状态", example = "0=正常,1=停用")
    private String status;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dictId", getDictId())
            .append("dictName", getDictName())
            .append("dictType", getDictType())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
