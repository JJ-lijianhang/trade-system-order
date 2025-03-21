package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.PaymentBillEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 支付账单表(PaymentBill)表数据库访问层
 *
 * @author ben
 * @since 2024-05-22 16:01:01
 */
public interface PaymentBillMapper extends BaseMapper<PaymentBillEntity> {
    @Select("select * from tb_payment_bill where upstream_order_no = #{txid}")
    PaymentBillEntity selectByBillNo(@Param("txid") String txid);
}
