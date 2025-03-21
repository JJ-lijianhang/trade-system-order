package com.futurebank.order.vo.Invoice;


import lombok.Data;

import java.io.Serializable;

/**
 * 商户资金信息
 */
@Data
public class MerchantBizInfoVo implements Serializable {
    private static final long serialVersionUID = 3998612374319711049L;
    private String merchantId;
    private String registerCountryName;
    private String registerCountryCode;
    private String registerNameEn;
    private String ciCode;
}
