package com.futurebank.order.entity;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;


/**
 * 已发送资金的退款记录表(PproRRefundsFundssent)实体类
 *
 * @author ben
 * @since 2024-05-22 15:52:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PproRRefundsFundssent", description = "已发送资金的退款记录表")
public class PproRRefundsFundssentEntity implements Serializable {
    private static final long serialVersionUID = -22247096897791260L;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    /**
     * 退款ID，唯一标识每笔退款
     */
    @ApiModelProperty(value = "退款ID，唯一标识每笔退款")
    private String refundid;

    /**
     * 退款状态，表示退款的当前状态
     */
    @ApiModelProperty(value = "退款状态，表示退款的当前状态")
    private String refundstate;

    /**
     * 失败类型，如果退款失败，表示失败的原因
     */
    @ApiModelProperty(value = "失败类型，如果退款失败，表示失败的原因")
    private String failtype;

    /**
     * 资金状态，表示退款资金的当前状态
     */
    @ApiModelProperty(value = "资金状态，表示退款资金的当前状态")
    private String fundsstate;

    /**
     * 金额，退款的金额总数
     */
    @ApiModelProperty(value = "金额，退款的金额总数")
    private String amount;

    /**
     * 货币类型，退款金额使用的货币代码
     */
    @ApiModelProperty(value = "货币类型，退款金额使用的货币代码")
    private String currency;

    /**
     * 原交易ID，与原交易关联的ID
     */
    @ApiModelProperty(value = "原交易ID，与原交易关联的ID")
    private String reftxid;

    /**
     * 商户退款ID，商户系统中的退款ID
     */
    @ApiModelProperty(value = "商户退款ID，商户系统中的退款ID")
    private String merchantrefundid;

    /**
     * 合同编号，与退款相关的合同或协议编号
     */
    @ApiModelProperty(value = "合同编号，与退款相关的合同或协议编号")
    private String mcontract;

    /**
     * 初始时间戳，退款流程开始的时间
     */
    @ApiModelProperty(value = "初始时间戳，退款流程开始的时间")
    private String initts;

    /**
     * 成功时间戳，退款成功的时间
     */
    @ApiModelProperty(value = "成功时间戳，退款成功的时间")
    private String succeededts;

    /**
     * 失败时间戳，如果退款失败，失败的时间
     */
    @ApiModelProperty(value = "失败时间戳，如果退款失败，失败的时间")
    private String failedts;

    /**
     * 资金发送时间戳，资金被发送出的时间
     */
    @ApiModelProperty(value = "资金发送时间戳，资金被发送出的时间")
    private String fundssentts;

    /**
     * 资金拒绝时间戳，如果资金被拒绝，拒绝的时间
     */
    @ApiModelProperty(value = "资金拒绝时间戳，如果资金被拒绝，拒绝的时间")
    private String fundsrejectedts;

    /**
     * 错误文本，如果退款过程中出现错误，错误描述
     */
    @ApiModelProperty(value = "错误文本，如果退款过程中出现错误，错误描述")
    private String errortext;

    @ApiModelProperty(value = "${column.comment}")
    private Date createdTime;

    @ApiModelProperty(value = "${column.comment}")
    private Date updateTime;

    @ApiModelProperty(value = "${column.comment}")
    private String fileName;

    /**
     * 是否已处理
     */
    @ApiModelProperty(value = "是否已处理")
    private Integer isProcessed;
}
