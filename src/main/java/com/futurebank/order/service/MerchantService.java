package com.futurebank.order.service;

import com.futurebank.order.entity.MerchantEntity;
import com.futurebank.order.mapper.MerchantMapper;
import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.vo.Invoice.MerchantBizInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 商户表(Merchant)表Service
 *
 * @author ben
 * @since 2024-04-20 19:21:41
 */
@Service
public class MerchantService extends BaseService<MerchantMapper, MerchantEntity> {
    @Autowired
    private MerchantMapper merchantMapper;
    public MerchantEntity getMerchant(String merchantId) {
        return merchantMapper.getMerchant(merchantId);
    }


    public List<MerchantEntity> getMerchantEntityList() {
        return this.baseMapper.getMerchantEntityList();
    }


    public List<MerchantEntity> getMerchantReciliationList() {
        return this.baseMapper.getMerchantReciliationList();
    }

    public void updateMerchantReconciliation(Long id) {
        this.baseMapper.updateMerchantReconciliation(id);
    }

    /**
     * 查询商户业务信息
     * @param merchantId
     * @return
     */
    public MerchantBizInfoVo queryMerchantBizInfo(Long merchantId) {
        return this.baseMapper.queryMerchantBizInfo(merchantId);
    }
}
