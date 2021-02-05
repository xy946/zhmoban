package com.zhzh.model.common;

import com.zhzh.util.pagination.PaginationParameter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : xiaoyu
 * @date : 2020/7/6 17:16:02
 **/
@ApiModel(value = "查询参数类")
@Data
public class QueryParam extends PaginationParameter {

    @ApiModelProperty(value = "id" ,example = "1")
    private Integer id;

    @ApiModelProperty(value = "ids,批量id操作" ,example = "1,2,3")
    private String ids;

    @ApiModelProperty(value = "老人名称" ,example = "1")
    private String oldName;

    @ApiModelProperty(value = "老人身份证" ,example = "420117xxxxxxxxxx38")
    private String idCard;

    @ApiModelProperty(value = "查询开始时间" ,example = "2018-08-08")
    private String startTime;

    @ApiModelProperty(value = "查询结束时间" ,example = "2018-08-08")
    private String endTime;

    @ApiModelProperty(value = "设备编码" ,example = "352454531321514")
    private String eqNumber;

    @ApiModelProperty(value = "是否绑定" ,example = "1/2")
    private String isBind;

    @ApiModelProperty(value = "报警类型" ,example = "1/2..")
    private String policeType;

    @ApiModelProperty(value = "指令类型" ,example = "主机复位分机")
    private String instructionType;

    @ApiModelProperty(value = "门牌号" ,example = "20")
    private String housenumName;

    @ApiModelProperty(value = "楼层" ,example = "03")
    private String floorName;

    @ApiModelProperty(value = "物品名称" ,example = "风扇")
    private String goodsName;

    @ApiModelProperty(value = "单个物品单id" ,example = "2")
    private Integer goodsOrderId;

    @ApiModelProperty(value = "老人ID" ,example = "18")
    private Integer oldId;


}
