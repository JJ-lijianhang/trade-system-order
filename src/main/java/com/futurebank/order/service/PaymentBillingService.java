package com.futurebank.order.service;

import com.alibaba.fastjson.JSON;
import com.futurebank.pojo.enums.BillTypeEnum;
import com.futurebank.pojo.enums.PaymentStatusEnum;
import com.futurebank.order.config.DS;
import com.futurebank.order.entity.PaymentBillingEntity;
import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.entity.PaymentOrderUpstreamEntity;
import com.futurebank.order.mapper.PaymentBillingMapper;
import com.futurebank.order.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 支付账单表(new)(PaymentBilling)表Service
 *
 * @author ben
 * @since 2024-05-27 00:03:06
 */
@Service
@Slf4j
public class PaymentBillingService extends BaseService<PaymentBillingMapper, PaymentBillingEntity> {

    public PaymentBillingEntity selectByBillNo(String txid) {
        return this.baseMapper.selectByBillNo(txid);
    }

    public PaymentBillingEntity selectByUpstreamOrderNo(String txid, String billType) {
        return this.baseMapper.selectByUpstreamOrderNo(txid, billType);
    }


    public List<PaymentBillingEntity> getPaymentBillingEntity(Long id) {
        return this.baseMapper.getPaymentBillingEntity(id);
    }


    public List<PaymentBillingEntity> getMerchantReconciliation(Long id) {
        return this.baseMapper.getMerchantReconciliation(id);
    }

    @Autowired
    private PaymentOrderService paymentOrderService;
    @Autowired
    private PaymentOrderUpstreamService paymentOrderUpstreamService;
    @Autowired
    private PaymentBillingService paymentBillingService;

    /**
     * 更新订单状态
     *
     * @param txid           上游订单状态
     * @param platformStatus 平台订单状态
     * @param upstreamStatus 上游订单状态
     * @param upstreamReason 上游订单失败原因
     * @param successStatus  上游订单成功的状态标识
     * @param data           上游订单通知数据
     */
    public void updateOrderStatus(String txid,
                                  String platformStatus,
                                  String upstreamStatus,
                                  String upstreamReason,
                                  String successStatus,
                                  String data) {
        PaymentOrderEntity paymentOrderEntity = paymentOrderService.getPaymentOrderByUpstreamOrderNo(txid);

        if (paymentOrderEntity == null)
            return;

        if (paymentOrderEntity.getOrderStatus().equals(PaymentStatusEnum.交易成功.getStatus())) return;

        Map<String, Object> map = JSON.parseObject(data);

        upstreamStatus = upstreamStatus == null ? "" : upstreamStatus;
        upstreamReason = upstreamReason == null ? "" : upstreamReason;

        if (StringUtils.isBlank(upstreamStatus)) return;

        if (StringUtils.isBlank(platformStatus)) return;

        //修改订单表
        paymentOrderEntity.setOrderStatus(platformStatus);

        if (platformStatus.equals(paymentOrderEntity.getOrderStatus()))
            paymentOrderEntity.setOrderCompleteTime(new Date());

        paymentOrderEntity.setOrderNotifyTime(new Date());
        paymentOrderEntity.setUpstreamStatus(upstreamStatus);
        paymentOrderEntity.setFailReason(upstreamReason);
        paymentOrderService.update(paymentOrderEntity);

        //修改上游信息表
        PaymentOrderUpstreamEntity paymentOrderUpstreamEntity = paymentOrderUpstreamService.getPaymentOrderUpstreamByUpstreamOrderNo(txid);
        if (paymentOrderUpstreamEntity != null) {
            paymentOrderUpstreamEntity.setUpstreamNotifyStatus(upstreamStatus);
            paymentOrderUpstreamEntity.setUpstreamNotifyTime(new Date());
            if (upstreamStatus.equals(successStatus))
                paymentOrderUpstreamEntity.setUpstreamSuccessTime(new Date());
            paymentOrderUpstreamEntity.setUpstreamNotifyContent(data);
            paymentOrderUpstreamService.update(paymentOrderUpstreamEntity);
        }
//        if (!platformStatus.equals(PaymentStatusEnum.交易成功.getStatus()))
//            return;

        //修改账单
        PaymentBillingEntity paymentBillingEntity = paymentBillingService.selectByUpstreamOrderNo(txid, BillTypeEnum.交易.getType());
        if (paymentBillingEntity == null) {
            paymentBillingEntity = new PaymentBillingEntity();
            BeanUtils.copyProperties(paymentOrderEntity, paymentBillingEntity);

            paymentBillingEntity.setOrderStatus(platformStatus);

            paymentBillingService.add(paymentBillingEntity);

        } else {
            if (paymentBillingEntity.getOrderStatus().equals(PaymentStatusEnum.交易成功.getStatus()))
                return;

            paymentBillingEntity.setOrderStatus(platformStatus);
            paymentBillingEntity.setOrderCompleteTime(new Date());
            paymentBillingService.update(paymentBillingEntity);

        }
        log.info("ppro succRecord : {} ", JSON.toJSONString(paymentBillingEntity));

    }

    public PaymentBillingEntity getPaymentBillingByDownstreamOrderNo(String downstreamOrderNo) {
        return this.baseMapper.getPaymentBillingByDownstreamOrderNo(downstreamOrderNo);
    }


    public int updateByReconciliation(Long billId) {
        return this.baseMapper.updateByReconciliation(billId);
    }

    public PaymentBillingEntity getPaymentBillingByUpstreamOrderNo(String partnerpaymentid) {
        return this.baseMapper.getPaymentBillingByUpstreamOrderNo(partnerpaymentid);
    }
}
