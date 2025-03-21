package com.futurebank.order.service;


import com.futurebank.order.entity.MerchantPaymentProviderEntity;
import com.futurebank.order.mapper.MerchantPaymentProviderMapper;
import com.futurebank.order.service.base.BaseService;
import org.springframework.stereotype.Service;


/**
 * 商户-支付通道提供商关联表(MerchantPaymentProvider)表Service
 *
 * @author ben
 * @since 2024-12-11 18:05:36
 */
@Service
public class MerchantPaymentProviderService extends BaseService<MerchantPaymentProviderMapper, MerchantPaymentProviderEntity> {

    public MerchantPaymentProviderEntity getMerchantPaymentProvider(Long merchantId, String providerName) {
        return baseMapper.getMerchantPaymentProvider(merchantId, providerName);
    }

}
