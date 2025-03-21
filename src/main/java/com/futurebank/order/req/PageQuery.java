package com.futurebank.order.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageQuery {
    /** 总数量 **/
    @ApiModelProperty(value = "总数量")
    private long total ;

    /** 页码 **/
    @ApiModelProperty(value = "页码")
    private int pageNum = 1;

    /** 每页条数 **/
    @ApiModelProperty(value = "每页条数")
    private int pageSize = 10;
}
