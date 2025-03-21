package com.futurebank.order.mapper;

import com.futurebank.order.entity.KgpSettlementBill;
import com.futurebank.order.entity.PaymentBillingEntity;
import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KgpSettleBillMapper extends BaseMapper<KgpSettlementBill>{

    /**
     * 查询系统订单
     * @param list
     * @return
     */
    List<PaymentBillingEntity> querySettlementBillingsByUpstreamOrderNo (@Param("list") List<String> list);

    List<PaymentOrderEntity> querySettlementOrderByUpstreamOrderNo (@Param("list") List<String> list);

    /**
     * 批量插入差异的订单
     * @param list
     * @return
     */
    int insertPaymentBillingBatch (@Param("list") List<PaymentBillingEntity> list);


    /**
     * 更新订单 状态
     * @param billing
     * @return
     */
    int updateBill (PaymentBillingEntity billing);

    /**
     * 更新订单 状态
     * @param order
     * @return
     */
    int updateOrder (PaymentOrderEntity order);
}
