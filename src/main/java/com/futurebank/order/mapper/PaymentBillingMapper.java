package com.futurebank.order.mapper;

import com.futurebank.order.config.DS;
import com.futurebank.order.entity.PaymentBillingEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 支付账单表(new)(PaymentBilling)表数据库访问层
 *
 * @author ben
 * @since 2024-05-27 00:03:06
 */
public interface PaymentBillingMapper extends BaseMapper<PaymentBillingEntity> {
    @DS("slave")
    @Select("select * from tb_payment_billing where upstream_order_no = #{txid}")
    PaymentBillingEntity selectByBillNo(String txid);

//    @Select("select * from tb_payment_billing where merchanm_id = #{merchantId} and settlement_date <= #{settlementDate}")
//    List<PaymentBillingEntity> getPaymentBillingEntity(@Param("merchantId") String merchantId, @Param("settlementDate") String settlementDate);
    @DS("slave")
    @Select("select * from tb_payment_billing where merchant_id  = #{id} and (downstream_settlement_status=0 or downstream_settlement_status is null) and upstream_settlement_status = 1 and datediff(now(),order_create_time)>0")
    List<PaymentBillingEntity> getPaymentBillingEntity(Long id);

    /**
     * 获取商户对账单
     *
     * @param id
     * @return
     */
    @DS("slave")
    @Select("select \n" +
            "platform_order_no as TXID,\n" +
            "order_status as TXSTATE,\n" +
            "order_type as ORDERTYPE,\n" +
            "country as COUNTRYCODE,\n" +
            "merchant_order_amount as AMOUNT,\n" +
            "merchant_currency as CURRENCY,\n" +
            "payment_method as TAG,\n" +
            "downstream_order_no as MERCHANTTXID,\n" +
            "merchant_id as MERCHANID,\n" +
            "order_create_time as INITTS,\n" +
            "order_complete_time as COMPLETETS\n" +
            " from tb_payment_order where datediff(now(),order_create_time) >= 1 and merchant_id = #{id} ")
    List<PaymentBillingEntity> getMerchantReconciliation(Long id);

    @DS("slave")
    @Select("select * from tb_payment_billing where upstream_order_no = #{upstreamOrderNo} and bill_type = #{billType} ")
    PaymentBillingEntity selectByUpstreamOrderNo(@Param("upstreamOrderNo") String txid, @Param("billType") String billType);

    @DS("slave")
    @Select("select * from tb_payment_billing where downstream_order_no = #{downstreamOrderNo} ")
    PaymentBillingEntity getPaymentBillingByDownstreamOrderNo(String downstreamOrderNo);
    @DS("slave")
    @Update("update tb_payment_billing set downstream_reconciliation_status = 1 , downstream_reconciliation_date=CURDATE() where bill_id = #{billId} ")
    int updateByReconciliation(Long billId);

    @DS("slave")
    @Select("select * from tb_payment_billing where upstream_order_no = #{partnerpaymentid} limit 1")
    PaymentBillingEntity getPaymentBillingByUpstreamOrderNo(String partnerpaymentid);
}
