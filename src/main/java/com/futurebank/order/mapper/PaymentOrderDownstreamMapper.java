package com.futurebank.order.mapper;

import com.futurebank.order.entity.PaymentOrderDownstreamEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 商户订单下游信息(PaymentOrderDownstream)表数据库访问层
 *
 * @author ben
 * @since 2024-03-25 15:15:29
 */
public interface PaymentOrderDownstreamMapper extends BaseMapper<PaymentOrderDownstreamEntity> {

    @Select("select * from tb_payment_order_downstream where platform_order_no = #{tradeNo} limit  1 ")
    PaymentOrderDownstreamEntity getPaymentOrderDownstreamByOrderNo(@Param("tradeNo") String tradeNo);
}
