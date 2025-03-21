package com.futurebank.order.vo.Report.excel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceExcelReportVo {
    /**
     * 引用号
     */
    private String reference;

    /**
     * 创建日期
     */
    private String createdDate;

    /**
     * 类型：收款/收款手续费/付款/付款手续费/转账/转账手续费/换汇/收单结算
     * Convert
     * Trensfer Fee
     * Trensfer
     * Payment Fee
     * Payment
     * Collection Fee
     * Collection
     * Acquiring Settlement
     */
    private String type;

    /**
     * 货币
     */
    private String currency;

    /**
     * 借方金额
     */
    private String debit;

    /**
     * 贷方金额
     */
    private String credit;

    /**
     * 余额
     */
    private String balance;

}
