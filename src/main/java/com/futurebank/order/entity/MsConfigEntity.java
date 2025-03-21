package com.futurebank.order.entity;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Getter;
import lombok.Setter;


/**
 * 微服务鉴权(MsConfig)实体类
 *
 * @author ben
 * @since 2024-09-23 15:18:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MsConfig", description = "微服务鉴权")
public class MsConfigEntity implements Serializable {
    private static final long serialVersionUID = 176718431993072266L;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    /**
     * 微服务名称
     */
    @ApiModelProperty(value = "微服务名称")
    private String msName;

    /**
     * 服务器ips(加密)
     */
    @ApiModelProperty(value = "服务器ips(加密)")
    private String msIps;

    /**
     * 服务器描述
     */
    @ApiModelProperty(value = "服务器描述")
    private String msDesc;

    @ApiModelProperty(value = "${column.comment}")
    private Date createdTime;

    @ApiModelProperty(value = "${column.comment}")
    private Date updateTime;
}
