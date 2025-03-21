package com.futurebank.order.service;

import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.entity.PaymentProviderEntity;
import com.futurebank.order.mapper.PaymentProviderMapper;
import com.futurebank.order.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 支付通道提供商(PaymentProvider)表Service
 *
 * @author ben
 * @since 2024-04-01 00:06:08
 */
@Service
public class PaymentProviderService extends BaseService<PaymentProviderMapper, PaymentProviderEntity> {

    @Autowired
    private PaymentProviderMapper paymentProviderMapper;

    public PaymentProviderEntity getPaymentProvider(Long providerId) {
        return paymentProviderMapper.getPaymentProvider(providerId);
    }

    public PaymentProviderEntity getPaymentProviderByName(String name) {
        return paymentProviderMapper.getPaymentProviderByName(name);
    }

    public List<PaymentProviderEntity> getPaymentProviderByList(String klasha) {
        return paymentProviderMapper.getPaymentProviderByList(klasha);
    }
}
