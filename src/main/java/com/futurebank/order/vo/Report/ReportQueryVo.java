package com.futurebank.order.vo.Report;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

/**
 * 报表查询接口参数
 */
@Data
public class ReportQueryVo {

    /**
     * 商户号
     */
    private Long merchantId;

    private String currency;


    /**
     * 订单开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startTime;

    /**
     * 订单结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endTime;

    /**
     * 币种列表-支持多种币种查询
     */
    private List<String> currencyList;


    private int offset;

    private int limit;


}
