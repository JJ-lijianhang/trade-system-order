package com.futurebank.order.service;

import com.futurebank.order.entity.MerchantChargeEntity;
import com.futurebank.order.mapper.MerchantChargeMapper;
import com.futurebank.order.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 商户资金流水表(MerchantCharge)表Service
 *
 * @author ben
 * @since 2024-06-01 20:44:11
 */
@Service
public class MerchantChargeService extends BaseService<MerchantChargeMapper, MerchantChargeEntity> {

    public List<MerchantChargeEntity> getMerchantChargeByOrderNo(String platformOrderNo, Integer chargeType, Integer waiterType, Integer merchantChargeEvent) {
        return this.baseMapper.getMerchantChargeByOrderNo1(platformOrderNo, chargeType, waiterType, merchantChargeEvent);
    }
    public List<MerchantChargeEntity> getMerchantChargeByOrderNo(String platformOrderNo, Integer chargeType, Integer waiterType) {
        return this.baseMapper.getMerchantChargeByOrderNo(platformOrderNo, chargeType, waiterType);
    }
}
