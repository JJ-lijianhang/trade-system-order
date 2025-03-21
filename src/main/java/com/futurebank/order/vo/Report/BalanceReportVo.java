package com.futurebank.order.vo.Report;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BalanceReportVo {
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
    private BigDecimal debit;

    /**
     * 贷方金额
     */
    private BigDecimal credit;

    /**
     * 余额
     */
    private BigDecimal balance;


    /**
     * 服务类型
     */
    private String serviceType;
    private Integer walletType;
    private String chargeEventType;
    private String chargeEventName;





}
