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
 * 资金已接收的交易记录表(PproRTransactionFundsreceived)实体类
 *
 * @author ben
 * @since 2024-05-22 15:52:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PproRTransactionFundsreceived", description = "资金已接收的交易记录表")
public class PproRTransactionFundsreceivedEntity implements Serializable {
    private static final long serialVersionUID = -26686078839382439L;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    /**
     * 交易ID，唯一标识每笔交易
     */
    @ApiModelProperty(value = "交易ID，唯一标识每笔交易")
    private String txid;

    /**
     * 交易状态，表示交易的当前状态
     */
    @ApiModelProperty(value = "交易状态，表示交易的当前状态")
    private String txstate;

    /**
     * 失败类型，若交易失败，指明失败的原因分类
     */
    @ApiModelProperty(value = "失败类型，若交易失败，指明失败的原因分类")
    private String failtype;

    /**
     * 资金状态，表示交易资金的当前状态
     */
    @ApiModelProperty(value = "资金状态，表示交易资金的当前状态")
    private String fundsstate;

    /**
     * 交易金额，交易发生时的金额
     */
    @ApiModelProperty(value = "交易金额，交易发生时的金额")
    private String amount;

    /**
     * 货币类型，交易使用的货币代码
     */
    @ApiModelProperty(value = "货币类型，交易使用的货币代码")
    private String currency;

    /**
     * 账户持有人，进行交易的账户持有者名称
     */
    @ApiModelProperty(value = "账户持有人，进行交易的账户持有者名称")
    private String accountholder;

    /**
     * 渠道，交易发生的渠道或方式
     */
    @ApiModelProperty(value = "渠道，交易发生的渠道或方式")
    private String channel;

    /**
     * 标签，用于标记交易的特征或分类
     */
    @ApiModelProperty(value = "标签，用于标记交易的特征或分类")
    private String tag;

    /**
     * 商户交易ID，商户系统中的交易ID
     */
    @ApiModelProperty(value = "商户交易ID，商户系统中的交易ID")
    private String merchanttxid;

    /**
     * 合同编号，与交易关联的合同或协议编号
     */
    @ApiModelProperty(value = "合同编号，与交易关联的合同或协议编号")
    private String mcontract;

    /**
     * 国家代码，交易发生地的国家代码
     */
    @ApiModelProperty(value = "国家代码，交易发生地的国家代码")
    private String countrycode;

    /**
     * 销售点，交易发生的销售点或位置
     */
    @ApiModelProperty(value = "销售点，交易发生的销售点或位置")
    private String sellingpoint;

    /**
     * 所售服务，交易中提供的服务或商品类型
     */
    @ApiModelProperty(value = "所售服务，交易中提供的服务或商品类型")
    private String soldservice;

    /**
     * 初始交易时间戳，交易开始的时间点
     */
    @ApiModelProperty(value = "初始交易时间戳，交易开始的时间点")
    private String initts;

    /**
     * 成功时间戳，交易成功完成的时间点
     */
    @ApiModelProperty(value = "成功时间戳，交易成功完成的时间点")
    private String succeededts;

    /**
     * 失败时间戳，若交易失败，交易失败的时间点
     */
    @ApiModelProperty(value = "失败时间戳，若交易失败，交易失败的时间点")
    private String failedts;

    /**
     * 存疑时间戳，若交易状态存疑，交易存疑的时间点
     */
    @ApiModelProperty(value = "存疑时间戳，若交易状态存疑，交易存疑的时间点")
    private String indoubtts;

    /**
     * 资金接收时间戳，资金到账的时间点
     */
    @ApiModelProperty(value = "资金接收时间戳，资金到账的时间点")
    private String fundsreceivedts;

    /**
     * 资金缺失时间戳，若记录资金缺失，发现资金缺失的时间点
     */
    @ApiModelProperty(value = "资金缺失时间戳，若记录资金缺失，发现资金缺失的时间点")
    private String fundsmissingts;

    /**
     * 错误文本，若交易失败或异常，错误描述
     */
    @ApiModelProperty(value = "错误文本，若交易失败或异常，错误描述")
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
