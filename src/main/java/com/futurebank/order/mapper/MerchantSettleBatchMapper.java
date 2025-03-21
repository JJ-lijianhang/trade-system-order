package com.futurebank.order.mapper;

import com.futurebank.order.entity.MerchantSettleBatch;
import com.futurebank.order.entity.PaymentSettlementConfEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantSettleBatchMapper extends BaseMapper<MerchantSettleBatch> {

    /**
     * 查询商户的结算配置信息
     * @param merchantId
     * @return
     */
    List<PaymentSettlementConfEntity> queryMerchantSettleConfigInfo(@Param("merchantId") Long merchantId);

    /**
     * 查询商户的结算批次信息
     * @param merchantSettleBatch
     * @return
     */
    List<MerchantSettleBatch> queryMerchantSettleBatchInfo(MerchantSettleBatch merchantSettleBatch);
}
