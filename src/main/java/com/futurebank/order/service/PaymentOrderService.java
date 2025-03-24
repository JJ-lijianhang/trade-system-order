package com.futurebank.order.service;

import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.mapper.PaymentOrderMapper;
import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.vo.MerchantReconciliationDetailsVo;
import com.futurebank.order.vo.MerchantRefundDetailsVo;
import com.futurebank.order.vo.MerchantSttemQuery;
import com.futurebank.order.vo.ReconciliationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 支付订单表，用于存储订单相关信息(PaymentOrder)表Service
 *
 * @author ben
 * @since 2024-03-21 14:39:59
 */
@Service
public class PaymentOrderService extends BaseService<PaymentOrderMapper, PaymentOrderEntity> {
    @Autowired
    PaymentOrderMapper paymentOrderMapper;


    public List<PaymentOrderEntity> getPaymentOrderByStatus() {
        return paymentOrderMapper.getPaymentOrderByStatus();
    }

    public PaymentOrderEntity getPaymentOrderByOrderNo(String tradeNo) {
        return paymentOrderMapper.getPaymentOrderByOrderNo(tradeNo);
    }

    public PaymentOrderEntity getPaymentOrderByUpstreamOrderNo(String upstreamOrderNo) {
        return paymentOrderMapper.getPaymentOrderByUpstreamOrderNo(upstreamOrderNo);
    }

    public List<PaymentOrderEntity> getPaymentOrderEntity(List ids) {
        return paymentOrderMapper.getPaymentOrderEntity(ids);
    }


    public List<PaymentOrderEntity> getFailPaymentOrder() {
        return paymentOrderMapper.getFailPaymentOrder();
    }

    public List<PaymentOrderEntity> getPaymentOrderByReject() {
        return paymentOrderMapper.getPaymentOrderByReject();
    }

    public List<ReconciliationVo> getMerchantReconciliation(Long id) {
        return paymentOrderMapper.getMerchantReconciliation(id);
    }


    public List<PaymentOrderEntity> getPaymentOrderByList() {
        return paymentOrderMapper.getPaymentOrderByList();
    }

    public PaymentOrderEntity getPaymentOrderById(String id) {
        return paymentOrderMapper.getPaymentOrderById(id);
    }

    /**
     * 商户退款明细
     * @param sttemQuery
     * @return
     */
    public List<MerchantRefundDetailsVo> queryMerchantRefundDetails(MerchantSttemQuery sttemQuery) {
        return paymentOrderMapper.queryMerchantRefundDetails(sttemQuery);
    }

    /**
     * 商户对账明细
     * @param sttemQuery
     * @return
     */
    public List<MerchantReconciliationDetailsVo> queryMerchantReconciliationDetails(MerchantSttemQuery sttemQuery) {
        return paymentOrderMapper.queryMerchantReconciliationDetails(sttemQuery);
    }
}
