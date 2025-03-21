package com.futurebank.order.mapper;

import com.futurebank.order.config.DS;
import com.futurebank.order.entity.MerchantSettlementEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 商户结算表(MerchantSettlement)表数据库访问层
 *
 * @author ben
 * @since 2024-05-30 15:22:09
 */
public interface MerchantSettlementMapper extends BaseMapper<MerchantSettlementEntity> {
    @Delete("delete from tb_merchant_settlement where settle_id = #{settlementDate} and merchant_id = #{merchantId}")
    void deleteBySettlementDate(@Param("merchantId") Long merchantId, @Param("settlementDate")String settlementDate);

    //    @Select("select\n" +
//            "merchant_id as merchantId,\n" +
//            "merchant_currency as currency, \n" +
//            "sum(CASE WHEN order_status='SUCCEED' THEN merchant_order_amount ELSE 0 END) as realAmount, \n" +
//            "SUM(CASE WHEN fee_type IN ('transaction', 'chargeback','refund') and order_status='SUCCEED' THEN merchant_order_amount ELSE 0 END) as transactionAmount ,\n" +
//            "SUM(CASE WHEN fee_type IN ('transaction', 'chargeback','refund')  THEN 0 ELSE merchant_order_amount END) as rateFee\n" +
//            "from tb_merchant_settment_details \n" +
//            "group by merchant_id ,merchant_currency")
//    @Select("select\n" +
//            "merchant_id as merchantId,\n" +
//            "merchant_currency as currency, \n" +
//            "COALESCE(sum(CASE WHEN order_status='SUCCEED' THEN merchant_order_amount ELSE 0 END),0) as realAmount, \n" +
//            "COALESCE(sum(CASE WHEN fee_type IN ('transaction', 'chargeback','refund') and order_status='SUCCEED' THEN merchant_order_amount ELSE 0 END),0) as transactionAmount ,\n" +
//            "COALESCE(SUM(CASE WHEN fee_type IN ('transaction', 'chargeback','refund')  THEN 0 ELSE merchant_order_amount END),0) as rateFee,\n" +
//            "COALESCE(SUM(CASE WHEN fee_type = 'fixed'  THEN  merchant_order_amount END),0) as fixedFee,\n" +
//            "COALESCE(SUM(CASE WHEN fee_type not IN ('transaction', 'chargeback','refund','fixed') and order_status='SUCCEED' THEN  merchant_order_amount END),0) as percentageFee\n" +
//            "from tb_merchant_settment_details \n" +
//            "where merchant_id = #{merchantId} and settlement_id = #{settleDate} group by merchant_id ,merchant_currency;")
    @DS("slave")
    @Select("SELECT \n" +
            "\t\tmerchant_id AS merchantId,\n" +
            "    merchant_currency AS merchantCurrency,\n" +
            "    COALESCE(SUM(merchant_order_amount), 0) AS realAmount,\n" +
            "    COALESCE(SUM(CASE WHEN fee_type IN ('transaction', 'chargeback', 'refund') AND order_status = 'SUCCEED' THEN merchant_order_amount ELSE 0 END), 0) AS transactionAmount,\n" +
            "    COALESCE(SUM(CASE WHEN fee_type NOT IN ('transaction', 'chargeback', 'refund') THEN merchant_order_amount ELSE 0 END), 0) AS rateFee\n" +
            "\t\t\n" +
            "FROM \n" +
            "    tb_merchant_settment_details\n" +
            "WHERE \n" +
            "    settlement_id = #{settleDate} \n" +
            "    AND merchant_id = #{merchantId}\n" +
            "GROUP BY \n" +
            "    merchant_currency")
    List<Map<String, Object>> aggregateMerchantSettlement(@Param("merchantId") Long merchantId, @Param("settleDate") String settlementDate);

    @Select("select * from tb_merchant_settlement where settle_status = 1")
    List<MerchantSettlementEntity> getPublishSettlement();

    @Update("update tb_merchant_settlement set settle_status = 3 where id = #{id}")
    void updateMerchantPendingConfirmation(Long id);

    @DS("slave")
    @Select("select * from tb_merchant_settlement where settle_status = 3")
    List<MerchantSettlementEntity> getFundTransfer();

    @Update("update tb_merchant_settlement set settle_status = 4 where id = #{id}")
    void updateMerchantFundTransfer(Long id);
//    @Select("select sum(merchant_order_amount) from tb_merchant_settment_details where merchant_id = #{id} and settlement_id = #{settlementDate}")
//    BigDecimal getTotalTransactionAmount(Long id, String settlementDate);
}
