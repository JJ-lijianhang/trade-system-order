package com.futurebank.order.vo.Report;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AcquiringTransactionReportVo {
    /**
     * 引用号
     */
    private String reference;

    /**
     * 创建日期
     */
    private Date createdDate;

    /**
     * 完成日期
     */
    private Date completedDate;

    /**
     * 通知日期
     */
    private Date notifyDate;

    /**
     * 状态
     */
    private String status;

    /**
     * 付款方式
     */
    private String paymentMethod;

    /**
     * 付款类型
     */
    private String paymentType;

    /**
     * 国家/地区
     */
    private String countryOrRegion;

    /**
     * 交易
     */
    private BigDecimal transaction;

    /**
     * 交易货币
     */
    private String transactionCurrency;

    /**
     * 网关费用
     */
    private BigDecimal gatewayFee;


    /**
     * 网关货币
     */
    private String gatewayCurrency;

    /**
     * 固定费用
     */
    private BigDecimal fixedFee;


    /**
     * 固定费用货币
     */
    private String fixedFeeCurrency;

    /**
     * 折扣费用
     */
    private BigDecimal discountFee;


    /**
     * 折扣费用货币
     */
    private String discountFeeCurrency;

    /**
     * 资金状态
     */
    private String fundStatus;

    /**
     * 预计结算日期
     */
    private String estimatedSettlementDate;

    /**
     * 错误描述
     */
    private String errorDescription;

    /**
     * 订单类型
     */
    private Long providerId;
    private String orderType;
    private String downstreamFeeC;
    private String gatewayFeeStr;
    private String discountFeeStr;
    private String fixedFeeStr;
}
