package com.futurebank.order.vo.AutoSettmentVo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PPRORTransactionVo implements Serializable {

    private String merchantId;

    /**
     * 结算日期
     */
    private String reconciliationDate;

    /**
     * 结算日期
     */
    private String settlementDate;



}
