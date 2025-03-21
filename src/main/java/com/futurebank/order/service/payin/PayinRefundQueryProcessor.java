package com.futurebank.order.service.payin;//package com.futurebank.payment.service.payin;


import com.futurebank.pojo.base.CommonResp;
import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.entity.PaymentProviderEntity;
import com.futurebank.order.service.PaymentOrderService;
import com.futurebank.order.service.PaymentProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * 退款订单查询
 * @author ben
 * @date 2024/2/27 0:01
 **/
@Component
@Slf4j
public class PayinRefundQueryProcessor {

    @Autowired
    PaymentOrderService paymentService;

    @Autowired
    PaymentProviderService paymentProviderService;


    private final Map<String, PayinRefundQueryService> refundQueryServiceMap;


    @Autowired
    public PayinRefundQueryProcessor(Map<String, PayinRefundQueryService> refundQueryServiceMap) {
        this.refundQueryServiceMap = refundQueryServiceMap;
    }

    /**
     * 获取上游对象
     *
     * @param serviceName
     * @return
     */
    PayinRefundQueryService getPaymentService(String serviceName) {
        return refundQueryServiceMap.get(serviceName);
    }

    public CommonResp process() throws Exception {
        return null;
    }


}
