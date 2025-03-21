package com.futurebank.order.mapper;

import com.futurebank.order.entity.MerchantPaymentProviderEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 商户-支付通道提供商关联表(MerchantPaymentProvider)表数据库访问层
 *
 * @author wangxin
 * @since 2024-12-11
 */
public interface MerchantPaymentProviderMapper extends BaseMapper<MerchantPaymentProviderEntity> {

    @Select("select * from tb_merchant_payment_provider where merchant_id = #{merchantId} and provider_name = #{providerName}")
    MerchantPaymentProviderEntity getMerchantPaymentProvider(@Param("merchantId") Long merchantId, @Param("providerName") String providerName);
}
