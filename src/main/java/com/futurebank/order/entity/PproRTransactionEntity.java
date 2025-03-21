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
 * 交易记录表(PproRTransaction)实体类
 *
 * @author ben
 * @since 2024-05-26 14:07:42
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PproRTransaction", description = "交易记录表")
public class PproRTransactionEntity implements Serializable {
    private static final long serialVersionUID = -34176570789969181L;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    /**
     * 交易ID，交易的唯一标识符
     */
    @ApiModelProperty(value = "交易ID，交易的唯一标识符")
    private String txid;

    /**
     * 交易状态，表示交易已成功
     */
    @ApiModelProperty(value = "交易状态，表示交易已成功")
    private String txstate;

    /**
     * 失败类型，对于成功的交易，此字段通常为空
     */
    @ApiModelProperty(value = "失败类型，对于成功的交易，此字段通常为空")
    private String failtype;

    /**
     * 资金状态，表示资金的当前状态
     */
    @ApiModelProperty(value = "资金状态，表示资金的当前状态")
    private String fundsstate;

    /**
     * 交易金额，交易的金额总数
     */
    @ApiModelProperty(value = "交易金额，交易的金额总数")
    private String amount;

    /**
     * 货币类型，交易使用的货币代码
     */
    @ApiModelProperty(value = "货币类型，交易使用的货币代码")
    private String currency;

    /**
     * 账户持有人，拥有该账户的个人或实体
     */
    @ApiModelProperty(value = "账户持有人，拥有该账户的个人或实体")
    private String accountholder;

    /**
     * 渠道，交易发生的渠道或方式
     */
    @ApiModelProperty(value = "渠道，交易发生的渠道或方式")
    private String channel;

    /**
     * 标签，用于分类或识别交易
     */
    @ApiModelProperty(value = "标签，用于分类或识别交易")
    private String tag;

    /**
     * 商户交易ID，商户系统中的交易ID
     */
    @ApiModelProperty(value = "商户交易ID，商户系统中的交易ID")
    private String merchanttxid;

    /**
     * 合同编号，与交易相关的合同或协议的编号
     */
    @ApiModelProperty(value = "合同编号，与交易相关的合同或协议的编号")
    private String mcontract;

    /**
     * 国家代码，交易发生地的国家代码
     */
    @ApiModelProperty(value = "国家代码，交易发生地的国家代码")
    private String countrycode;

    /**
     * 销售点，交易发生的地点
     */
    @ApiModelProperty(value = "销售点，交易发生的地点")
    private String sellingpoint;

    /**
     * 所售服务，交易中提供的商品或服务
     */
    @ApiModelProperty(value = "所售服务，交易中提供的商品或服务")
    private String soldservice;

    @ApiModelProperty(value = "${column.comment}")
    private String initts;

    @ApiModelProperty(value = "${column.comment}")
    private String succeededts;

    @ApiModelProperty(value = "${column.comment}")
    private String failedts;

    @ApiModelProperty(value = "${column.comment}")
    private String indoubtts;

    @ApiModelProperty(value = "${column.comment}")
    private String fundsreceivedts;

    @ApiModelProperty(value = "${column.comment}")
    private String fundsmissingts;

    /**
     * 错误文本，对于成功的交易，此字段通常为空
     */
    @ApiModelProperty(value = "错误文本，对于成功的交易，此字段通常为空")
    private String errortext;

    /**
     * 扩展字段文本
     */
    @ApiModelProperty(value = "扩展字段文本")
    private String addJson;

    /**
     * 响应文本
     */
    @ApiModelProperty(value = "响应文本")
    private String outJson;

    @ApiModelProperty(value = "${column.comment}")
    private Date reconciliationDate;

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
