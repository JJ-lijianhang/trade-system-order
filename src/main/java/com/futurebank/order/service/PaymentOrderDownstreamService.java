package com.futurebank.order.service;

import com.futurebank.order.entity.PaymentOrderDownstreamEntity;
import com.futurebank.order.mapper.PaymentOrderDownstreamMapper;
import com.futurebank.order.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 商户订单下游信息(PaymentOrderDownstream)表Service
 *
 * @author ben
 * @since 2024-03-25 15:15:30
 */
@Service
public class PaymentOrderDownstreamService extends BaseService<PaymentOrderDownstreamMapper, PaymentOrderDownstreamEntity> {

    @Autowired
    private PaymentOrderDownstreamMapper paymentOrderDownstreamMapper;

    public PaymentOrderDownstreamEntity getPaymentOrderDownstreamByOrderNo(String tradeNo) {
        return paymentOrderDownstreamMapper.getPaymentOrderDownstreamByOrderNo(tradeNo);
    }
}
