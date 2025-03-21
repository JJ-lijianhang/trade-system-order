package com.futurebank.order.mapper;

import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.entity.MerchantUpstreamSettlementEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * 商户结算表(MerchantUpstreamSettlement)表数据库访问层
 *
 * @author ben
 * @since 2024-05-31 15:40:49
 */
public interface MerchantUpstreamSettlementMapper extends BaseMapper<MerchantUpstreamSettlementEntity> {
    @Delete("delete from tb_merchant_upstream_settlement where settle_id = #{settlementDate} and merchant_id = #{merchantId}")
    void deleteBySettlementDate(@Param("merchantId") Long merchantId, @Param("settlementDate")String settlementDate);
}
