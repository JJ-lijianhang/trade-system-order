package com.futurebank.order.vo.Report.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferExcelReportVo {
    /**
     * 引用号
     */
    @ExcelProperty("Reference")
    private String reference;

    /**
     * 创建日期
     */
    @ExcelProperty("Created Date")
    private String createdDate;

    /**
     * 完成日期
     */
    @ExcelProperty("Completed Date")
    private String completedDate;

    /**
     * 状态
     */
    @ExcelProperty("Status")
    private String status;

    /**
     * 类型
     */
    @ExcelProperty("Type")
    private String type;

    /**
     * 目标账户
     */
    @ExcelProperty("Target Account")
    private String targetAccount;

    /**
     * 转账金额
     */
    @ExcelProperty("Transfer Amount")
    private String transferAmount;

    /**
     * 转账货币
     */
    @ExcelProperty("Transfer Currency")
    private String transferCurrency;

    /**
     * 费用金额
     */
    @ExcelProperty("Fee Amount")
    private String feeAmount;

    /**
     * 费用货币
     */
    @ExcelProperty("Fee Currency")
    private String feeCurrency;

    /**
     * 汇率
     */
    @ExcelProperty("Rate")
    private String rate;
}
