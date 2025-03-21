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
 * 订单查询
 * @author ben
 * @date 2024/2/27 0:01
 **/
@Component
@Slf4j
public class PayinOrderQueryProcessor {

    @Autowired
    PaymentOrderService paymentService;

    @Autowired
    PaymentProviderService paymentProviderService;


    private final Map<String, PayinOrderQueryService> orderQueryServiceMap;


    @Autowired
    public PayinOrderQueryProcessor(Map<String, PayinOrderQueryService> orderQueryServiceMap) {
        this.orderQueryServiceMap = orderQueryServiceMap;
    }

    /**
     * 获取上游对象
     *
     * @param serviceName
     * @return
     */
    PayinOrderQueryService getOrderQueryService(String serviceName) {
        return orderQueryServiceMap.get(serviceName);
    }

    public CommonResp ordedQueryProcess() throws Exception {

        List<PaymentOrderEntity> refundOrderList = paymentService.getFailPaymentOrder();
        log.info("refundOrderList size:{}", refundOrderList.size());

        if (refundOrderList == null || refundOrderList.size() == 0) {
            return null;
        }

        for (PaymentOrderEntity refundOrder : refundOrderList) {
            log.info("refundOrder:{}", refundOrder);
            refundOrder.setRequestStatus(1);
            paymentService.update(refundOrder);

            PaymentProviderEntity paymentProviderEntity = paymentProviderService.getPaymentProvider(refundOrder.getProviderId());
            String servicename = "payin-orderQuery-" + paymentProviderEntity.getProviderName();
            PayinOrderQueryService payinOrderQueryService = getOrderQueryService(servicename);
            payinOrderQueryService.orderQuery(refundOrder, paymentProviderEntity);
        }

        return null;

    }


}
