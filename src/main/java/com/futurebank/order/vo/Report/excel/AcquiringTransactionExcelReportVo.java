package com.futurebank.order.vo.Report.excel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AcquiringTransactionExcelReportVo {
    /**
     * 引用号
     */
    private String reference;

    /**
     * 创建日期
     */
    private String createdDate;

    /**
     * 完成日期
     */
    private String completedDate;

    /**
     * 通知日期
     */
    private String notifyDate;

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
    private String transaction;

    /**
     * 交易货币
     */
    private String transactionCurrency;

    /**
     * 网关费用
     */
    private String gatewayFee;


    /**
     * 网关货币
     */
    private String gatewayCurrency;

    /**
     * 固定费用
     */
    private String fixedFee;


    /**
     * 固定费用货币
     */
    private String fixedFeeCurrency;

    /**
     * 折扣费用
     */
    private String discountFee;


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

}
