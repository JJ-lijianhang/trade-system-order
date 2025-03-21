package com.futurebank.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AccountBizTradeType", description = "账户系统-交易类型")
public class AccountBizTradeTypeEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5018159378601672451L;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id", example = "1")
    private Integer parentId;

    /**
     * 层级
     */
    @ApiModelProperty(value = "层级", example = "1")
    private String leave;

    /**
     * 交易编码
     */
    @ApiModelProperty(value = "交易编码", example = "TRADE_001")
    private String tradeCode;

    /**
     * 交易名称
     */
    @ApiModelProperty(value = "交易名称", example = "交易1")
    private String tradeName;

    /**
     * 类型描述
     */
    @ApiModelProperty(value = "类型描述", example = "描述信息")
    private String desc;

}