package com.futurebank.order.entity;

import java.math.BigDecimal;
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
 * 支付订单表，用于存储订单相关信息(PaymentOrder)实体类
 *
 * @author ben
 * @since 2024-12-05 14:42:56
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentOrder", description = "支付订单表，用于存储订单相关信息")
public class PaymentOrderEntity implements Serializable {
    private static final long serialVersionUID = -31225595149214952L;

    /**
     * 唯一编号
     */
    @ApiModelProperty(value = "唯一编号")
    private Long id;

    /**
     * 会话编号
     */
    @ApiModelProperty(value = "会话编号")
    private String sessionId;

    /**
     * 客户端请求次数
     */
    @ApiModelProperty(value = "客户端请求次数")
    private Integer requestCount;

    /**
     * 商户编号
     */
    @ApiModelProperty(value = "商户编号")
    private Long merchantId;

    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 渠道编号
     */
    @ApiModelProperty(value = "渠道编号")
    private Long channelId;

    /**
     * 渠道费率编号
     */
    @ApiModelProperty(value = "渠道费率编号")
    private Integer channelRatesId;

    /**
     * 产品编号
     */
    @ApiModelProperty(value = "产品编号")
    private Long productId;

    /**
     * 产品渠道编号
     */
    @ApiModelProperty(value = "产品渠道编号")
    private Long productChannelId;

    /**
     * 支付通道提供商编号
     */
    @ApiModelProperty(value = "支付通道提供商编号")
    private Long providerId;

    /**
     * 订单类型：transaction=交易 refund=退款 chanrgbank=拒付 transfers=转账 withdrawals=同名提现 recharges=充值 exchange=换汇 dispute=争议
     */
    @ApiModelProperty(value = "订单类型：transaction=交易 refund=退款 chanrgbank=拒付 transfers=转账 withdrawals=同名提现 recharges=充值 exchange=换汇 dispute=争议")
    private String orderType;

    /**
     * 收单方式 checkout=收银台 direct=直接集成
     */
    @ApiModelProperty(value = "收单方式 checkout=收银台 direct=直接集成")
    private String acquiringMode;

    /**
     * 交易类型 wallet=电子钱包  bankTransfer=银行转帐 localCard=本地卡  cash=现金 installments=分期 buyNowPayLater=先买后付 cryptocurrency=加密货币 prepaidCard=预付卡 recurring=定期支付
     */
    @ApiModelProperty(value = "交易类型 wallet=电子钱包  bankTransfer=银行转帐 localCard=本地卡  cash=现金 installments=分期 buyNowPayLater=先买后付 cryptocurrency=加密货币 prepaidCard=预付卡 recurring=定期支付")
    private String transactionType;

    /**
     * 下游订单号
     */
    @ApiModelProperty(value = "下游订单号")
    private String downstreamOrderNo;

    /**
     * 平台订单号
     */
    @ApiModelProperty(value = "平台订单号")
    private String platformOrderNo;

    /**
     * 上游订单号
     */
    @ApiModelProperty(value = "上游订单号")
    private String upstreamOrderNo;

    /**
     * 关联订单号
     */
    @ApiModelProperty(value = "关联订单号")
    private String referenceOrderNo;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private String paymentMethod;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;

    /**
     * 上游交易固定手续费
     */
    @ApiModelProperty(value = "上游交易固定手续费")
    private String upstreamFixedFee;

    /**
     * 上游交易手续费费率
     */
    @ApiModelProperty(value = "上游交易手续费费率")
    private String upstreamTxRate;

    /**
     * 上游总费用
     */
    @ApiModelProperty(value = "上游总费用")
    private BigDecimal upstreamTotalFee;

    /**
     * 商户交易固定手续费
     */
    @ApiModelProperty(value = "商户交易固定手续费")
    private String downstreamFixedFee;

    /**
     * 商户交易手续费费率
     */
    @ApiModelProperty(value = "商户交易手续费费率")
    private String downstreamTxRate;

    /**
     * 下游总费用
     */
    @ApiModelProperty(value = "下游总费用")
    private BigDecimal downstreamTotalFee;

    /**
     * 商户方的用户id
     */
    @ApiModelProperty(value = "商户方的用户id")
    private String buyerId;

    /**
     * 语言代码
     */
    @ApiModelProperty(value = "语言代码")
    private String languageCode;

    /**
     * 国家
     */
    @ApiModelProperty(value = "国家")
    private String country;

    /**
     * 处理货币代码
     */
    @ApiModelProperty(value = "处理货币代码")
    private String currency;

    /**
     * 商户货币代码
     */
    @ApiModelProperty(value = "商户货币代码")
    private String merchantCurrency;

    /**
     * 商户的支付金额
     */
    @ApiModelProperty(value = "商户的支付金额")
    private BigDecimal merchantOrderAmount;

    /**
     * 支付换汇汇率
     */
    @ApiModelProperty(value = "支付换汇汇率")
    private BigDecimal paymentExchange;

    /**
     * 支付换汇手续费
     */
    @ApiModelProperty(value = "支付换汇手续费")
    private BigDecimal paymentExchangeRate;

    /**
     * 结算货币代码
     */
    @ApiModelProperty(value = "结算货币代码")
    private String settlementCurrency;

    /**
     * 产品名称 | 支付原因
     */
    @ApiModelProperty(value = "产品名称 | 支付原因")
    private String productName;

    /**
     * 产品描述 | 支付详情
     */
    @ApiModelProperty(value = "产品描述 | 支付详情")
    private String productDetail;

    /**
     * 商品清单-数组
     */
    @ApiModelProperty(value = "商品清单-数组")
    private String inventories;

    /**
     * 订单状态 INITIALIZED=订单创建 PENDING=交易处理中 FAILED=交易失败 SUCCEED=交易成功 CANCEL=交易已取消 EXPIRED=交易已过期 REFUSED=交易被拒绝 PENDING=退款处理中 REFUNDED=已退款 FAILED=退款失败 REFUND_REVOKE=退款撤销 REFUND_REFUSED=退款拒绝 CHARGEBACK=拒付 CHARGEBACK_REVERSED=拒付撤销 DISPUTE=争议
     */
    @ApiModelProperty(value = "订单状态 INITIALIZED=订单创建 PENDING=交易处理中 FAILED=交易失败 SUCCEED=交易成功 CANCEL=交易已取消 EXPIRED=交易已过期 REFUSED=交易被拒绝 PENDING=退款处理中 REFUNDED=已退款 FAILED=退款失败 REFUND_REVOKE=退款撤销 REFUND_REFUSED=退款拒绝 CHARGEBACK=拒付 CHARGEBACK_REVERSED=拒付撤销 DISPUTE=争议")
    private String orderStatus;

    /**
     * 订单审核状态 normal=未审核 , review=审核中 , reject=审核拒绝 , approved=审核通过
     */
    @ApiModelProperty(value = "订单审核状态 normal=未审核 , review=审核中 , reject=审核拒绝 , approved=审核通过")
    private String reviewStatus;

    /**
     * 退款请求状态：0=待请求 1=已请求
     */
    @ApiModelProperty(value = "退款请求状态：0=待请求 1=已请求")
    private Integer requestStatus;

    /**
     * 订单查询状态：0=待请求 1=已请求
     */
    @ApiModelProperty(value = "订单查询状态：0=待请求 1=已请求")
    private Integer orderQueryStatus;

    /**
     * 退款订单查询状态：0=待请求 1=已请求
     */
    @ApiModelProperty(value = "退款订单查询状态：0=待请求 1=已请求")
    private Integer refundQueryStatus;

    /**
     * 拒绝原因
     */
    @ApiModelProperty(value = "拒绝原因")
    private String rejectionReason;

    /**
     * 退款原因
     */
    @ApiModelProperty(value = "退款原因")
    private String refundReason;

    /**
     * 上游返回状态
     */
    @ApiModelProperty(value = "上游返回状态")
    private String upstreamStatus;

    /**
     * 错误原因
     */
    @ApiModelProperty(value = "错误原因")
    private String errorMsg;

    /**
     * 失败的原因状态
     */
    @ApiModelProperty(value = "失败的原因状态")
    private String failReason;

    /**
     * ppro重定向密钥
     */
    @ApiModelProperty(value = "ppro重定向密钥")
    private String redirectsecret;

    /**
     * 商户同步地址
     */
    @ApiModelProperty(value = "商户同步地址")
    private String downstreamNotifyUrl;

    /**
     * 商户跳转地址
     */
    @ApiModelProperty(value = "商户跳转地址")
    private String downstreamRedirectUrl;

    /**
     * 订单创建时间
     */
    @ApiModelProperty(value = "订单创建时间")
    private Date orderCreateTime;

    /**
     * 订单完成时间
     */
    @ApiModelProperty(value = "订单完成时间")
    private Date orderCompleteTime;

    /**
     * 订单通知时间
     */
    @ApiModelProperty(value = "订单通知时间")
    private Date orderNotifyTime;

    /**
     * 结算结算周期 T=工作日 D=自然日
     */
    @ApiModelProperty(value = "结算结算周期 T=工作日 D=自然日")
    private String settlementCycle;

    /**
     * 用户号
     */
    @ApiModelProperty(value = "用户号")
    private Long userId;

    /**
     * 代理商
     */
    @ApiModelProperty(value = "代理商")
    private Long agentId;

    /**
     * 支付平台编号
     */
    @ApiModelProperty(value = "支付平台编号")
    private Long platformId;

    /**
     * 支付换汇加点利润
     */
    @ApiModelProperty(value = "支付换汇加点利润")
    private BigDecimal paymentTxMarkup;

    /**
     * 预估支付金额（USD）
     */
    @ApiModelProperty(value = "预估支付金额（USD）")
    private BigDecimal estimatedAmount;

    /**
     * 预估USD，换汇的汇率
     */
    @ApiModelProperty(value = "预估USD，换汇的汇率")
    private String estimatedExchangeRate;

    /**
     * 处理货币通道手续费：格式：费用类型:手续费货币
     */
    @ApiModelProperty(value = "处理货币通道手续费：格式：费用类型:手续费货币")
    private String downstreamFee;

    /**
     * 消费货币通道手续费：
     */
    @ApiModelProperty(value = "消费货币通道手续费：")
    private String downstreamFeeC;

    /**
     * 预估结算金额（USD）
     */
    @ApiModelProperty(value = "预估结算金额（USD）")
    private BigDecimal downstreamEstimatedAmount;

    /**
     * 预估手续费金额(USD)
     */
    @ApiModelProperty(value = "预估手续费金额(USD)")
    private BigDecimal downstreamEstimatedFee;

    /**
     * 通道手续费：格式：费用类型:手续费货币单位
     */
    @ApiModelProperty(value = "通道手续费：格式：费用类型:手续费货币单位")
    private String upstreamFee;

    /**
     * 预估金额
     */
    @ApiModelProperty(value = "预估金额")
    private BigDecimal upstreamEstimatedAmount;

    /**
     * 预估手续费金额
     */
    @ApiModelProperty(value = "预估手续费金额")
    private BigDecimal upstreamEstimatedFee;

    /**
     * 风控类型 0 初始状态
     */
    @ApiModelProperty(value = "风控类型 0 初始状态")
    private Integer riskType;

    /**
     * 利润(USD)
     */
    @ApiModelProperty(value = "利润(USD)")
    private BigDecimal profit;

    /**
     * 冻结类型：0 初始状态 1 冻结 2 原路返还 3 已划拨
     */
    @ApiModelProperty(value = "冻结类型：0 初始状态 1 冻结 2 原路返还 3 已划拨")
    private Integer freezeType;

    /**
     * 商户地址
     */
    @ApiModelProperty(value = "商户地址")
    private String origin;

    /**
     * 3ds当前状态 ：initial_authentication 初始化验证 information_gathering 信息收集 risk_assessment 风险评估(保留) challenge 挑战请求 authentication 挑战响应 authorization 授权请求(保留)
     */
    @ApiModelProperty(value = "3ds当前状态 ：initial_authentication 初始化验证 information_gathering 信息收集 risk_assessment 风险评估(保留) challenge 挑战请求 authentication 挑战响应 authorization 授权请求(保留)")
    private String threedsStatus;

    /**
     * 3ds授权验证模式：redirect pin avs
     */
    @ApiModelProperty(value = "3ds授权验证模式：redirect pin avs")
    private String mode;

    /**
     * 3ds初始化验证响应
     */
    @ApiModelProperty(value = "3ds初始化验证响应")
    private String initialAuthentication;

    /**
     * 3ds初始化验证响应时间
     */
    @ApiModelProperty(value = "3ds初始化验证响应时间")
    private Date initialAuthenticationTime;

    /**
     * 3ds初始化状态
     */
    @ApiModelProperty(value = "3ds初始化状态")
    private String initialAuthenticationStatus;

    /**
     * 3ds授权验证第一步请求
     */
    @ApiModelProperty(value = "3ds授权验证第一步请求")
    private String authStep1Request;

    /**
     * 3ds授权验证第一步响应
     */
    @ApiModelProperty(value = "3ds授权验证第一步响应")
    private String authStep1Response;

    /**
     * 采集信息状态
     */
    @ApiModelProperty(value = "采集信息状态")
    private String authStep1Status;

    /**
     * 3ds授权验证第一步请求时间
     */
    @ApiModelProperty(value = "3ds授权验证第一步请求时间")
    private Date authStep1Time;

    /**
     * 3ds授权验证第二步请求
     */
    @ApiModelProperty(value = "3ds授权验证第二步请求")
    private String authStep2Request;

    /**
     * 3ds授权验证第二步响应
     */
    @ApiModelProperty(value = "3ds授权验证第二步响应")
    private String authStep2Response;

    /**
     * 挑战请求状态
     */
    @ApiModelProperty(value = "挑战请求状态")
    private String authStep2Status;

    /**
     * 3ds授权验证第二步请求时间
     */
    @ApiModelProperty(value = "3ds授权验证第二步请求时间")
    private Date authStep2Time;

    /**
     * 操作人Id
     */
    @ApiModelProperty(value = "操作人Id")
    private Long operatorId;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    private Date operatorTime;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    private String tags;

    /**
     * 下游初始订单号
     */
    @ApiModelProperty(value = "下游初始订单号")
    private String downstreamOrderNoOrigin;

    /**
     * 加点手续费(USD)
     */
    @ApiModelProperty(value = "加点手续费(USD)")
    private BigDecimal paymentTxMarkupUsd;

    /**
     * 下游固定费用(USD)
     */
    @ApiModelProperty(value = "下游固定费用(USD)")
    private BigDecimal downstreamFixedFeeUsd;

    /**
     * 上游税率
     */
    @ApiModelProperty(value = "上游税率")
    private BigDecimal upstreamVat;

    /**
     * 下游游税率
     */
    @ApiModelProperty(value = "下游游税率")
    private BigDecimal downstreamVat;

    /**
     * 上游税费 = 上游游通道手续费 * VAT
     */
    @ApiModelProperty(value = "上游税费 = 上游游通道手续费 * VAT")
    private BigDecimal estimatedUpstreamVatFee;

    /**
     * 下游税费 = 下游通道手续费 * VAT
     */
    @ApiModelProperty(value = "下游税费 = 下游通道手续费 * VAT")
    private BigDecimal estimatedDownstreamVatFee;

    /**
     * 支付凭证
     */
    @ApiModelProperty(value = "支付凭证")
    private String documentId;

    /**
     * 上游交易订单号，二维码获取
     */
    @ApiModelProperty(value = "上游交易订单号，二维码获取")
    private String transRefId;

    /**
     * 上传的凭证文件路径
     */
    @ApiModelProperty(value = "上传的凭证文件路径")
    private String uploadVoucherFile;

    /**
     * 退款银行账号（针对kgp）
     */
    @ApiModelProperty(value = "退款银行账号（针对kgp）")
    private String refundAccountNo;

    /**
     * 退款姓名（针对kgp）
     */
    @ApiModelProperty(value = "退款姓名（针对kgp）")
    private String returnAccountName;

    /**
     * 商户请求时间
     */
    @ApiModelProperty(value = "商户请求时间")
    private Long merchantRequestTime;

    /**
     * 渠道请求时间
     */
    @ApiModelProperty(value = "渠道请求时间")
    private Long channelRequestTime;

    @ApiModelProperty(value = "${column.comment}")
    private String downstreamGatewayFee;

    /**
     * 网关手续费
     */
    @ApiModelProperty(value = "网关手续费")
    private String upstreamGatewayFee;
}
