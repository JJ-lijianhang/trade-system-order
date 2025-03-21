package com.futurebank.order.service.payin;


import jakarta.transaction.Transactional;

/**
 * @author ben
 * @date 2024/3/21 17:28
 **/

public interface PayinRefundNotifyService {
    /**
     * 商户订单同步
     */
    @Transactional(rollbackOn = Exception.class)
    boolean payinRefundNotify(String refundNotifyData) ;

}
