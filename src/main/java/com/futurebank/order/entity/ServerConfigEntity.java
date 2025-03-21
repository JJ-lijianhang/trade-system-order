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
 * 分布式部署配置表(ServerConfig)实体类
 *
 * @author ben
 * @since 2024-04-01 10:59:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ServerConfig", description = "分布式部署配置表")
public class ServerConfigEntity implements Serializable {
    private static final long serialVersionUID = 554770445977044460L;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    /**
     * 服务器ip
     */
    @ApiModelProperty(value = "服务器ip")
    private String serverIp;

    /**
     * 服务器id
     */
    @ApiModelProperty(value = "服务器id")
    private String serverId;

    @ApiModelProperty(value = "${column.comment}")
    private Date createdTime;

    @ApiModelProperty(value = "${column.comment}")
    private Date updateTime;
}
