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
 * 基于提供文件内容生成的退款失败表(PproRRefundFailed)实体类
 *
 * @author ben
 * @since 2024-05-22 15:52:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PproRRefundFailed", description = "基于提供文件内容生成的退款失败表")
public class PproRRefundFailedEntity implements Serializable {
    private static final long serialVersionUID = -63774559783821469L;

    /**
     * 唯一ID
     */
    @ApiModelProperty(value = "唯一ID")
    private Long id;

    /**
     * 退款ID
     */
    @ApiModelProperty(value = "退款ID")
    private String refundid;

    /**
     * 退款状态
     */
    @ApiModelProperty(value = "退款状态")
    private String refundstate;

    /**
     * 失败类型
     */
    @ApiModelProperty(value = "失败类型")
    private String failtype;

    /**
     * 资金状态
     */
    @ApiModelProperty(value = "资金状态")
    private String fundsstate;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private String amount;

    /**
     * 货币
     */
    @ApiModelProperty(value = "货币")
    private String currency;

    /**
     * 参考交易ID
     */
    @ApiModelProperty(value = "参考交易ID")
    private String reftxid;

    /**
     * 商户退款ID
     */
    @ApiModelProperty(value = "商户退款ID")
    private String merchantrefundid;

    /**
     * 合同ID
     */
    @ApiModelProperty(value = "合同ID")
    private String mcontract;

    /**
     * 初始化时间
     */
    @ApiModelProperty(value = "初始化时间")
    private String initts;

    /**
     * 成功时间
     */
    @ApiModelProperty(value = "成功时间")
    private String succeededts;

    /**
     * 失败时间
     */
    @ApiModelProperty(value = "失败时间")
    private String failedts;

    /**
     * 资金发送时间
     */
    @ApiModelProperty(value = "资金发送时间")
    private String fundssentts;

    /**
     * 资金拒绝时间
     */
    @ApiModelProperty(value = "资金拒绝时间")
    private String fundsrejectedts;

    /**
     * 错误文本
     */
    @ApiModelProperty(value = "错误文本")
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
