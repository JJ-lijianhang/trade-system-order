package com.futurebank.order.service;

import com.futurebank.order.entity.MerchantSettlementAggregateEntity;
import com.futurebank.order.mapper.MerchantSettlementAggregateMapper;
import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.vo.Invoice.MerchantInfoVo;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * (MerchantSettlementAggregate)表Service
 *
 * @author ben
 * @since 2024-06-09 14:53:04
 */
@Service
public class MerchantSettlementAggregateService extends BaseService<MerchantSettlementAggregateMapper, MerchantSettlementAggregateEntity> {

    public Integer  deleteByMerchantIdAndPeriod(Long merchantId, String period) {
        return this.baseMapper.deleteByMerchantIdAndPeriod(merchantId, period);
    }

    public List<MerchantSettlementAggregateEntity> getByMerchantIdAndPeriod(Long merchantId, String period) {
        return this.baseMapper.getByMerchantIdAndPeriod(merchantId, period);
    }

    public MerchantInfoVo queryTotalSettlementMoney(Long merchantId, Long providerId, String period) {
        return this.baseMapper.queryTotalSettlementMoney(merchantId,providerId, period);
    }
}
