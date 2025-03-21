package com.futurebank.order.mapper;

import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.PaymentProviderEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 支付通道提供商(PaymentProvider)表数据库访问层
 *
 * @author ben
 * @since 2024-04-01 00:06:07
 */
public interface PaymentProviderMapper extends BaseMapper<PaymentProviderEntity> {
    @Select("SELECT * FROM tb_payment_provider WHERE provider_id = #{providerId}")
    PaymentProviderEntity getPaymentProvider(Long providerId);
    @Select("SELECT * FROM tb_payment_provider WHERE provider_name = #{name} limit 1")
    PaymentProviderEntity getPaymentProviderByName(String name);
    @Select("SELECT * FROM tb_payment_provider WHERE provider_name = #{name}")
    List<PaymentProviderEntity> getPaymentProviderByList(String klasha);
}
