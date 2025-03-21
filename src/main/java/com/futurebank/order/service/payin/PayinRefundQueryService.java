package com.futurebank.order.service.payin;//package com.futurebank.payment.service.payin;



import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.entity.PaymentProviderEntity;
import org.springframework.transaction.annotation.Transactional;


public interface PayinRefundQueryService {

    /**
     * 退款查询
     * @param paymentOrderEntity
     * @param paymentProviderEntity
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    void refundQuery(PaymentOrderEntity paymentOrderEntity, PaymentProviderEntity paymentProviderEntity) throws Exception;
}
