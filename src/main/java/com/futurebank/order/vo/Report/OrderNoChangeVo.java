package com.futurebank.order.vo.Report;

import lombok.Data;

import java.util.List;

@Data
public class OrderNoChangeVo {

    private String platOrderNo;

    private String sourcePlatOrderNo;

    private String downstreamOrderNo;

    private String sourceDownstreamOrderNo;

    /**
     * platOrderNo list
     */
    private List<String> orderList;
}
