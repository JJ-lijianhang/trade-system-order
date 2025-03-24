package com.futurebank.order.service.payin;//package com.futurebank.payment.service.payin;


import com.futurebank.pojo.base.CommonResp;
import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.entity.PaymentProviderEntity;
import com.futurebank.order.service.PaymentOrderService;
import com.futurebank.order.service.PaymentProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.futurebank.order.service.FundsHandleService;

import java.util.List;
import java.util.Map;


/**
 * @author ben
 * @date 2024/2/27 0:01
 **/
@Component
@Slf4j
public class PayinRefundProcessor {

    @Autowired
    PaymentOrderService paymentService;

    @Autowired
    PaymentProviderService paymentProviderService;


    private final Map<String, PayinRefundService> refundServiceMap;


    @Autowired
    public PayinRefundProcessor(Map<String, PayinRefundService> refundServiceMap) {
        this.refundServiceMap = refundServiceMap;
        log.info("refundServiceMap:{}", refundServiceMap);
    }

    /**
     * 获取上游对象
     *
     * @param serviceName
     * @return
     */
    PayinRefundService getPaymentService(String serviceName) {
        if (!this.refundServiceMap.containsKey(serviceName)) {
            throw new RuntimeException("can not find service:" + serviceName);
        }
        return this.refundServiceMap.get(serviceName);
    }

    public CommonResp processRefund(List ids) throws Exception {

        List<PaymentOrderEntity> refundOrderList = paymentService.getPaymentOrderEntity(ids);
        log.info("refundOrderList size:{}", refundOrderList.size());

        if (refundOrderList == null || refundOrderList.size() == 0) {
            return null;
        }

        for (PaymentOrderEntity refundOrder : refundOrderList) {
            log.info("refundOrder:{}", refundOrder);
            refundOrder.setRequestStatus(1);
            paymentService.update(refundOrder);

            PaymentProviderEntity paymentProviderEntity = paymentProviderService.getPaymentProvider(refundOrder.getProviderId());
            String servicename = "payin-refund-" + paymentProviderEntity.getProviderName();
            PayinRefundService payinRefundService = getPaymentService(servicename);
            log.info("payinRefundService:{}  servicename:{}", payinRefundService, servicename);
            payinRefundService.refund(refundOrder, paymentProviderEntity);
        }

        return null;

    }

    @Autowired
    FundsHandleService fundsHandleService;

    public CommonResp refundBack() {

        List<PaymentOrderEntity> refundOrderList = paymentService.getPaymentOrderByReject();
        log.info("refundOrderList size:{}", refundOrderList.size());

        if (refundOrderList == null || refundOrderList.size() == 0) {
            return null;
        }

        for (PaymentOrderEntity refundOrder : refundOrderList) {
            log.info("退款金额原来返还:{}", refundOrder);
            fundsHandleService.refundBack(refundOrder);
//            fundsHandleService.refundFeeBack(refundOrder);
            refundOrder.setFreezeType(2);
            paymentService.update(refundOrder);
        }

        return null;

    }

}
