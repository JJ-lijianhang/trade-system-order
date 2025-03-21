package com.futurebank.order.mapper;

import com.futurebank.order.entity.MerchantSettmentDetailsEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.vo.SettlementAggergateVo;
import com.futurebank.order.vo.SettlementVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (MerchantSettmentDetails)表数据库访问层
 *
 * @author ben
 * @since 2024-05-30 11:51:29
 */
public interface MerchantSettmentDetailsMapper extends BaseMapper<MerchantSettmentDetailsEntity> {
    @Delete("delete from tb_merchant_settment_details where settlement_id = #{settlementDate} and merchant_id = #{merchantId}")
    void   deleteBySettlementDate(@Param("merchantId") Long merchantId, @Param("settlementDate")String settlementDate);

//    @Select("select * from tb_merchant_settment_details where settlement_id = #{settlementDate} and merchant_id = #{merchantId}")
    @Select("select \n" +
            "downstream_order_no as MERCHANTTXID,\n" +
            "platform_order_no as TXID,\n" +
            "reference_order_no as REFERENCETXID,\n" +
            "CASE WHEN order_status = 'SUCCEED' THEN 'SUCCEED' ELSE 'INITIALIZED' END  as TXSTATE,\n" +
            "payment_method as TAG,\n" +
            "order_type as ORDERTYPE,\n" +
            "country as COUNTRYCODE,\n" +
            "merchant_currency as CURRENCY,\n" +
            "fee_type as FEETYPE,\n" +
            "merchant_order_amount as AMOUNT,\n" +
            "merchant_id as MERCHANID,\n" +
            "order_create_time as EVENTTIMESTAMP\n" +
            " from tb_merchant_settment_details where  merchant_id = #{merchantId} and settlement_id =#{settlementDate} ")
    List<SettlementVo> getPublishSettlement(@Param("merchantId") Long merchantId,@Param("settlementDate") String settlementDate);

    @Select("select \n" +
            " settlement_id as 'SETTLEMENTID',\n" +
            " merchant_id as 'MERCHANT_ID',\n" +
            " payment_method as 'PAYMENT_METHOD',\n" +
            " fee_type as 'EVENT_TYPE',\n" +
            " count(1) as 'EVENT_COUNT',\n" +
            " sum(merchant_order_amount) as 'AGGREGATE_AMOUNT',\n" +
            " merchant_currency as 'CURRENCY'\n" +
            " from tb_merchant_settment_details \n" +
            " where settlement_id =#{settlementDate} and merchant_id = #{merchantId} \n" +
            " group by payment_method,fee_type,merchant_currency")
    List<SettlementAggergateVo> getPublishSettlementAggergate(@Param("merchantId") Long merchantId,@Param("settlementDate") String settlementDate);
    @Select("select * from tb_merchant_settment_details where order_status = 'SUCCEED' and fee_type in ('refund','chargeback') and merchant_id=#{merchantId} and settlement_id =#{settlementDate}")
    List<MerchantSettmentDetailsEntity> getByRefundAndChargeback(@Param("merchantId") Long merchantId, @Param("settlementDate") String settlementDate);
}
