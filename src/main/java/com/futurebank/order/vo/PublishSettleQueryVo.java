package com.futurebank.order.vo;

import lombok.Data;

@Data
public class PublishSettleQueryVo {
    private Long merchantId;
    private String settlementDate;
    private Integer status;
}
