package com.futurebank.order.service;

import com.futurebank.pojo.base.CommonResp;
import com.futurebank.order.entity.MerchantSettleBatch;
import com.futurebank.order.entity.PaymentSettlementConfEntity;

import java.util.List;

public interface MerchantSettleBatchService  {


    /**
     * 商户结算批次信息创建
     * @param merchantId
     * @param startDate
     * @param endDate
     * @return
     */
    CommonResp createSettleBatch(Long merchantId,String startDate, String endDate);


    List<PaymentSettlementConfEntity> queryMerchantSettleConfigInfo(Long merchantId);

    /**
     * 商户结算批次信息查询
     * @param merchantBatch
     * @return
     */
    List<MerchantSettleBatch> queryMerchantSettleBatchInfo(MerchantSettleBatch merchantBatch);

    /**
     * 商户结算批次信息删除
     * @param id
     * @return
     */
    int deleteById(Long id);


    /**
     *  商户结算批次信息保存
     * @param merchantBatch
     * @return
     */
    int save(MerchantSettleBatch merchantBatch);

    /**
     *  商户结算批次信息保存
     * @param merchantBatch
     * @return
     */
    int update(MerchantSettleBatch merchantBatch);




}
