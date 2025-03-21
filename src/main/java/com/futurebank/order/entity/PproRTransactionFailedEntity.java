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
 * 记录失败的交易信息表(PproRTransactionFailed)实体类
 *
 * @author ben
 * @since 2024-05-22 15:52:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PproRTransactionFailed", description = "记录失败的交易信息表")
public class PproRTransactionFailedEntity implements Serializable {
    private static final long serialVersionUID = 852425944791499901L;

    /**
     * 唯一标识
     */
    @ApiModelProperty(value = "唯一标识")
    private Long id;

    /**
     * 交易ID
     */
    @ApiModelProperty(value = "交易ID")
    private String txid;

    /**
     * 交易状态
     */
    @ApiModelProperty(value = "交易状态")
    private String txstate;

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
     * 交易金额
     */
    @ApiModelProperty(value = "交易金额")
    private String amount;

    /**
     * 货币类型
     */
    @ApiModelProperty(value = "货币类型")
    private String currency;

    /**
     * 账户持有人
     */
    @ApiModelProperty(value = "账户持有人")
    private String accountholder;

    /**
     * 渠道
     */
    @ApiModelProperty(value = "渠道")
    private String channel;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    private String tag;

    /**
     * 商户交易ID
     */
    @ApiModelProperty(value = "商户交易ID")
    private String merchanttxid;

    /**
     * 合同编号
     */
    @ApiModelProperty(value = "合同编号")
    private String mcontract;

    /**
     * 国家代码
     */
    @ApiModelProperty(value = "国家代码")
    private String countrycode;

    /**
     * 销售点
     */
    @ApiModelProperty(value = "销售点")
    private String sellingpoint;

    /**
     * 所售服务
     */
    @ApiModelProperty(value = "所售服务")
    private String soldservice;

    /**
     * 初始交易时间戳
     */
    @ApiModelProperty(value = "初始交易时间戳")
    private String initts;

    /**
     * 成功时间戳
     */
    @ApiModelProperty(value = "成功时间戳")
    private String succeededts;

    /**
     * 失败时间戳
     */
    @ApiModelProperty(value = "失败时间戳")
    private String failedts;

    /**
     * 存疑时间戳
     */
    @ApiModelProperty(value = "存疑时间戳")
    private String indoubtts;

    /**
     * 资金接收时间戳
     */
    @ApiModelProperty(value = "资金接收时间戳")
    private String fundsreceivedts;

    /**
     * 资金缺失时间戳
     */
    @ApiModelProperty(value = "资金缺失时间戳")
    private String fundsmissingts;

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
