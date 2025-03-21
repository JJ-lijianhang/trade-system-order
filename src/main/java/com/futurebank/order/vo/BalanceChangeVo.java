package com.futurebank.order.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BalanceChangeVo implements Serializable {
    private static final long serialVersionUID = -4020999698189369981L;
    private String tradeNo;
    /**
     * 外围系统唯一标识
     */
    private String referenceNo;
    /**
     * 统一业务唯一单号
     */
    private String bizCode;
    private String bizUniqueNo;
    /**
     * 服务来源
     */
    private String serviceType;
    /**
     * 商户信息
     */
    private Long merchantId;

    private String consumerId;

    private Long agentId;

    private Long platformId;

    private Long providerId;

    private Integer ChargeType;

    private Integer walletType;

    private String walletCode;

    private Integer chargeEventType;

    private String currency;
    /**
     * 金额(total)
     */
    private BigDecimal total;
    /**
     * 金额(目标钱包)
     */
    private BigDecimal money;
    /**
     * 金额(二级钱包)
     */
    private BigDecimal moneySub;
    private String remark;
    private Long version;
    private String payment_method;
    private String period;

    public boolean isValid() {
        if (this.tradeNo != null && this.tradeNo.length() != 0) {
            if (this.referenceNo != null && this.referenceNo.length() != 0) {
                if (this.serviceType != null && this.serviceType.length() != 0) {
                    if (this.merchantId == null) {
                        return false;
                    } else if (this.ChargeType == null) {
                        return false;
                    } else if (this.walletType == null) {
                        return false;
                    } else if (this.chargeEventType == null) {
                        return false;
                    } else if (this.currency != null && this.currency.length() != 0) {
                        return this.money != null;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
