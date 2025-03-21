package com.futurebank.order.vo.Report;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ConvertReportVo {
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
     * 状态 订单状态0-待生效，10-处理中，2000-成功，3000-失败，4000-取消）
     * 订单状态 0：待生成 10：处理中，20 失败待确认, 30 失败待复核，40 成功待复核，2000：成功，2500：失败，3000：取消
     */
    private String status;

    /**
     * 卖出金额
     */
    private BigDecimal sold;

    /**
     * 卖出货币
     */
    private String soldCurrency;

    /**
     * 买入金额
     */
    private BigDecimal bought;

    /**
     * 买入货币
     */
    private String boughtCurrency;

    /**
     * 汇率
     */
    private BigDecimal rate;

    /**
     * 类型 交割方式 1 实时 2委托
     */
    private String type;
}
