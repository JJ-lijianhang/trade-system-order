package com.futurebank.order.vo.Report.excel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AcquiringChargebackExcelReportVo {
    /**
     * 引用号
     */
    private String reference;

    /**
     * 源引用号
     */
    private String sourceReference;

    /**
     * 创建日期
     */
    private String createdDate;

    /**
     * 完成日期
     */
    private String completedDate;

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
     * Chargebank 费用
     */
    private String chargebankFee;

    /**
     * Chargebank 费用货币
     */
    private String chargebankFeeCurrency;

    /**
     * 资金状态
     */
    private String fundStatus;

    /**
     * 结算状态
     */
    private String settlementStatus;

    /**
     * 原因
     */
    private String reason;

}
