package com.futurebank.order.vo.Report;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode
public class TransferReportVo {
    /**
     * 引用号
     */
    @ExcelProperty("Reference")
    private String reference;

    /**
     * 创建日期
     */
    @ExcelProperty("Created Date")
    private Date createdDate;

    /**
     * 完成日期
     */
    @ExcelProperty("Completed Date")
    private Date completedDate;

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
    private BigDecimal transferAmount;

    /**
     * 转账货币
     */
    @ExcelProperty("Transfer Currency")
    private String transferCurrency;

    /**
     * 费用金额
     */
    @ExcelProperty("Fee Amount")
    private BigDecimal feeAmount;

    /**
     * 费用货币
     */
    @ExcelProperty("Fee Currency")
    private String feeCurrency;

    /**
     * 汇率
     */
    @ExcelProperty("Rate")
    private BigDecimal rate;

    /* 百分比手续费 */
    @ExcelIgnore
    private BigDecimal fixedFee;
    @ExcelIgnore
    private String fixedFeeCcy;
    @ExcelIgnore
    private BigDecimal percentFee;
    @ExcelIgnore
    private String percentFeeCcy;
}
