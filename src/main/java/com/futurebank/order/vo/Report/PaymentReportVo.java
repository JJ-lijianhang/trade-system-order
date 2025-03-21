package com.futurebank.order.vo.Report;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaymentReportVo {
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
     * 状态
     */
    private String status;

    /**
     * 扣款金额
     */
    private BigDecimal deductionAmount;

    /**
     * 扣款货币
     */
    private String deductionCurrency;

    /**
     * 付款金额
     */
    private BigDecimal paymentAmount;

    /**
     * 付款货币
     */
    private String paymentCurrency;

    /**
     * 费用
     */
    private BigDecimal fee;

    /**
     * 费用货币
     */
    private String feeCurrency;

    /**
     * 汇率
     */
    private BigDecimal rate;

    /**
     * 付款方式
     */
    private String paymentMethod;

    /**
     * 费用责任方
     */
    private String feeResponsibility;

    /**
     * 付款人
     */
    private String payer;

    /**
     * 收款人类型
     */
    private String beneficiaryType;

    /**
     * 账户号/IBAN
     */
    private String accountNoOrIBAN;

    /**
     * 收款人名称
     */
    private String beneficiaryName;

    /**
     * 收款人地址
     */
    private String beneficiaryAddress;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行地址
     */
    private String bankAddress;

    /**
     * SWIFT 代码
     */
    private String swiftCode;

    /**
     * ABA 路由号码
     */
    private String abaRoutingNumber;

    /**
     * 付款原因
     */
    private String paymentReason;

    /**
     * 备注
     */
    private String noteToSelf;

    /**
     * 类型id
     */
    private String paymentTypeId;

    /**
     *
     */
    private Long payeeId;
}
