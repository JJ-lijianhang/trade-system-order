package com.futurebank.order.mapper;

import com.futurebank.order.config.DS;
import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.vo.MerchantReconciliationDetailsVo;
import com.futurebank.order.vo.MerchantRefundDetailsVo;
import com.futurebank.order.vo.MerchantSttemQuery;
import com.futurebank.order.vo.ReconciliationVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 支付订单表，用于存储订单相关信息(PaymentOrder)表数据库访问层
 *
 * @author ben
 * @since 2024-03-21 14:39:59
 */
public interface PaymentOrderMapper extends BaseMapper<PaymentOrderEntity> {



    @DS("slave")
    @Select("select * from tb_payment_order where (status = 'failed' or status = 'succeeded') order by order_notify_time asc")
    List<PaymentOrderEntity> getPaymentOrderByStatus();

    @DS("slave")
    @Select("select * from tb_payment_order where platform_order_no = #{tradeNo}")
    PaymentOrderEntity getPaymentOrderByOrderNo(@Param("tradeNo") String tradeNo);

    @DS("slave")
    @Select("SELECT * FROM tb_payment_order WHERE order_type='refund' AND review_status='approved' AND request_status=0 AND id IN (#{ids})")
    List<PaymentOrderEntity> getPaymentOrderEntity(@Param("ids") List<String> ids);

    @DS("slave")
    @Select("select * from tb_payment_order where order_type='transaction'  and order_query_status=0 and status='PENDING' and order by order_create_time asc limit 1000")
    List<PaymentOrderEntity> getFailPaymentOrder();

    @DS("slave")
    @Select("select * from tb_payment_order where upstream_order_no = #{upstreamOrderNo} limit 1")
    PaymentOrderEntity getPaymentOrderByUpstreamOrderNo(@Param("upstreamOrderNo") String upstreamOrderNo);

    @DS("slave")
    @Select("select * from tb_payment_order where order_type='refund' and review_status='rejected' and freeze_type= 1 order by order_create_time asc limit 1000")
    List<PaymentOrderEntity> getPaymentOrderByReject();

    @DS("slave")
    @Select("select * from tb_payment_order where platform_order_no = #{merchanttxid}")
    PaymentOrderEntity getPaymentOrderByPlatformOrderNo(String merchanttxid);

    @DS("slave")
    @Select("select * from tb_payment_order where reference_order_no = #{cbid}")
    PaymentOrderEntity getPaymentOrderByReferenceOrderNo(String cbid);

    //    @Select("select \n" +
//            "platform_order_no as TXID,\n" +
//            "order_status as TXSTATE,\n" +
//            "order_type as ORDERTYPE,\n" +
//            "country as COUNTRYCODE,\n" +
//            "merchant_order_amount as AMOUNT,\n" +
//            "merchant_currency as CURRENCY,\n" +
//            "payment_method as TAG,\n" +
//            "downstream_order_no as MERCHANTTXID,\n" +
//            "reference_order_no as REFERENCE_TX_ID,\n" +
//            "merchant_id as MERCHANID,\n" +
//            "reference_order_no as REFERENCE_TX_ID,\n" +
//            "order_create_time as INITTS,\n" +
//            "order_complete_time as COMPLETETS\n" +
//            " from tb_payment_order where datediff(now(),order_create_time) >= 1 and merchant_id = #{id} ")
    @DS("slave")
    @Select("select bill_id,platform_order_no AS TXID,\n" +
            "    order_status AS TXSTATE,\n" +
            "    order_type AS ORDERTYPE,\n" +
            "    country AS COUNTRYCODE,\n" +
            "    merchant_order_amount AS AMOUNT,\n" +
            "    merchant_currency AS CURRENCY,\n" +
            "    payment_method AS TAG,\n" +
            "    downstream_order_no AS MERCHANTTXID,\n" +
            "    reference_order_no AS REFERENCE_TX_ID,\n" +
            "    merchant_id AS MERCHANID,\n" +
            "    reference_order_no AS REFERENCE_TX_ID, \n" +
            "    order_create_time AS INITTS,\n" +
            "    order_complete_time AS COMPLETETS from tb_payment_billing where merchant_id  = #{id} and (downstream_reconciliation_status=0 or downstream_reconciliation_status is null) and upstream_reconciliation_status = 1 and datediff(now(),order_create_time)>0")
    List<ReconciliationVo> getMerchantReconciliation(Long id);

    //    @Select("<script>" +
//            "SELECT * " +
//            "FROM tb_payment_order " +
//            "WHERE (order_status = 'INITIALIZED' || order_status = 'PENDING') and order_create_time > NOW() - INTERVAL 10 SECOND and provider_id IN " +
//            "<foreach item='item' collection='providerIds' open='(' separator=',' close=')'>" +
//            "#{item}" +
//            "</foreach>" +
//            "order by id  limit 1000" +
//            "</script>  ")
//    @Select("SELECT * FROM tb_payment_order WHERE order_status IN ('INITIALIZED', 'PENDING') AND order_create_time > NOW() - INTERVAL 30 DAY ORDER BY id desc ")
    @DS("slave")
    @Select("SELECT * FROM tb_payment_order WHERE order_status IN ('INITIALIZED', 'PENDING') AND order_type ='transaction' AND payment_method is not null and order_create_time > NOW() - INTERVAL 5 DAY  ORDER BY id desc")
    List<PaymentOrderEntity> getPaymentOrderByList();

    @DS("slave")
    @Select("Select * from tb_payment_order where id = #{id}")
    PaymentOrderEntity getPaymentOrderById(String id);


//    @Select("<script>" +
//            "SELECT * " +
//            "FROM tb_payment_order " +
//            "WHERE (order_status = 'INITIALIZED' || order_status = 'PENDING') and currency_code IN " +
//            "<foreach item='item' collection='providerIds' open='(' separator=',' close=')'>" +
//            "#{item}" +
//            "</foreach>" +
//            "order by id " +
//            "</script>  ")
//    List<Map<String, Object>> getCurrencyInfoOrder(@Param("providerIds") List<String> providerIds);


//    @Select("<script>" +
//            "SELECT * " +
//            "FROM tb_payment_order " +
//            "WHERE (order_status = 'INITIALIZED' || order_status = 'PENDING') and currency_code IN " +
//            "<foreach item='item' collection='providerIds' open='(' separator=',' close=')'>" +
//            "#{item}" +
//            "</foreach>" +
//            "order by id " +
//            "</script>  ")
//    List<Map<String, Object>> getCurrencyInfoOrder(@Param("providerIds") List<String> providerIds);


    /**
     * 结算对账-退款明细查询
     * @param sttemQuery
     * @return
     */
    List<MerchantRefundDetailsVo> queryMerchantRefundDetails(MerchantSttemQuery sttemQuery);

    /**
     * 结算对账-交易明细查询
     * @return
     */
    List<MerchantReconciliationDetailsVo> queryMerchantReconciliationDetails(MerchantSttemQuery sttemQuery);







}
