package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.MerchantSettlementAggregateEntity;
import com.futurebank.order.vo.Invoice.MerchantInfoVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (MerchantSettlementAggregate)表数据库访问层
 *
 * @author ben
 * @since 2024-06-09 14:53:04
 */
public interface MerchantSettlementAggregateMapper extends BaseMapper<MerchantSettlementAggregateEntity> {
    @Select("delete from tb_merchant_settlement_aggregate where merchant_id = #{merchantId} and settlement_id = #{period}")
    Integer  deleteByMerchantIdAndPeriod(@Param("merchantId") Long merchantId, @Param("period") String period);
    @Select("select * from tb_merchant_settlement_aggregate where merchant_id = #{merchantId} and settlement_id = #{period} and payment_method is not null")
    List<MerchantSettlementAggregateEntity> getByMerchantIdAndPeriod(@Param("merchantId") Long merchantId, @Param("period") String period);

    MerchantInfoVo queryTotalSettlementMoney(@Param("merchantId") Long merchantId,@Param("providerId") Long providerId, @Param("period") String period);
}
