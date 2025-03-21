package com.futurebank.order.service;

import com.futurebank.order.entity.MerchantSettlementEntity;
import com.futurebank.order.mapper.MerchantSettlementMapper;
import com.futurebank.order.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 商户结算表(MerchantSettlement)表Service
 *
 * @author ben
 * @since 2024-05-30 15:22:09
 */
@Service
public class MerchantSettlementService extends BaseService<MerchantSettlementMapper, MerchantSettlementEntity> {
    public List<MerchantSettlementEntity> getPublishSettlement() {
        return this.baseMapper.getPublishSettlement();
    }

    /* 直接修改成 3 ，进入资金发布的过程*/
    public void updateMerchantPendingConfirmation(Long id) {
        this.baseMapper.updateMerchantPendingConfirmation(id);
    }

    public List<MerchantSettlementEntity> getFundTransfer() {
        return this.baseMapper.getFundTransfer();
    }

    public void updateMerchantFundTransfer(Long id) {
        this.baseMapper.updateMerchantFundTransfer(id);
    }

}
