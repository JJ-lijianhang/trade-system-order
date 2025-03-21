package com.futurebank.order.vo.AutoSettmentVo;

import lombok.Data;

@Data
public class MerchantSettInfoVo {
    private String merchantId;
    private String settlementDate;
    private String reconciliationDate;
}
