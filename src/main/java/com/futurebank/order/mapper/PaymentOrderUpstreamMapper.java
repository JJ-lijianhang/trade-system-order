package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.PaymentOrderUpstreamEntity;
import org.apache.ibatis.annotations.Select;

/**
 * 商户订单上游信息(PaymentOrderUpstream)表数据库访问层
 *
 * @author ben
 * @since 2024-04-02 10:42:04
 */
public interface PaymentOrderUpstreamMapper extends BaseMapper<PaymentOrderUpstreamEntity> {
    @Select("select * from tb_payment_order_upstream where upstream_order_no = #{txid}")
    PaymentOrderUpstreamEntity getPaymentOrderUpstreamByUpstreamOrderNo(String txid);

}
