package com.futurebank.order.mapper;

import com.futurebank.order.entity.MerchantReconciliationCalendarEntity;
import com.futurebank.order.entity.PaymentBillingEntity;
import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.mapper.base.BaseMapper;
import com.futurebank.order.vo.MerchantSttemQuery;
import com.futurebank.order.vo.SummaryReconVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantReconMapper extends BaseMapper<MerchantReconciliationCalendarEntity> {

    /**
     *  查询对账单数据
     * @param query
     * @return
     */
    List<PaymentOrderEntity> getMerchantReconciliation(MerchantSttemQuery query);


    /**
     * 查询对账汇总数据
     * @param reconDate
     * @param merchantId
     * @param providerId
     * @return
     */
    List<SummaryReconVo> querySummaryByReconDate(@Param("reconDate") String reconDate, @Param("merchantId") Long merchantId, @Param("providerId") Long providerId);


    List<MerchantReconciliationCalendarEntity> queryList(@Param("merchantId") Long merchantId, @Param("reconDate") String reconDate);
    int deleteByMerchantIdAndReconDate(@Param("merchantId") Long merchantId, @Param("reconDate") String reconDate);


}
