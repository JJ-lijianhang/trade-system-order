package com.futurebank.order.service;

import com.futurebank.order.entity.MerchantSettmentDetailsEntity;
import com.futurebank.order.mapper.MerchantSettmentDetailsMapper;
import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.vo.SettlementAggergateVo;
import com.futurebank.order.vo.SettlementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * (MerchantSettmentDetails)表Service
 *
 * @author ben
 * @since 2024-05-30 11:51:29
 */
@Service
public class MerchantSettmentDetailsService extends BaseService<MerchantSettmentDetailsMapper, MerchantSettmentDetailsEntity> {
    @Autowired
     private MerchantSettmentDetailsMapper mapper;

    public List<SettlementVo> getPublishSettlement(Long merchantId, String settlementDate) {
        return this.baseMapper.getPublishSettlement(merchantId, settlementDate);
    }

    public List<SettlementAggergateVo> getPublishSettlementAggergate(Long merchantId, String period) {
        return this.baseMapper.getPublishSettlementAggergate(merchantId, period);
    }

    public List<MerchantSettmentDetailsEntity> getByRefundAndChargeback(Long merchantId, String period) {
        return this.baseMapper.getByRefundAndChargeback(merchantId, period);
    }

}
