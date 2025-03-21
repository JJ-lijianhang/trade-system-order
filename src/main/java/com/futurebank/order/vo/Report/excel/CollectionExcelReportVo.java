package com.futurebank.order.vo.Report.excel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CollectionExcelReportVo {
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
     * 状态
     */
    private String status;

    /**
     * 类型
     */
    private String type;


    /**
     * 收到的金额
     */
    private String receivedAmount;

    /**
     * 收到的货币
     */
    private String receivedCurrency;

    /**
     * 汇率
     */
    private String rate;

    /**
     * 净金额
     */
    private String netAmount;

    /**
     * 净金额货币
     */
    private String netAmountCurrency;

    /**
     * 费用
     */
    private String fee;

    /**
     * 费用货币
     */
    private String feeCurrency;

    /**
     * 付款人
     */
    private String payer;

    /**
     * 付款人地址
     */
    private String payerAddress;

    /**
     * 账户号/IBAN
     */
    private String accountNoOrIBAN;

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
     * 备注
     */
    private String noteToSelf;
}
