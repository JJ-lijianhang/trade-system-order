package com.futurebank.order.service;

import com.alibaba.fastjson.JSON;
import com.futurebank.pojo.vo.payment.PaymentRequest;
import com.futurebank.order.entity.PaymentOrderUpstreamEntity;
import com.futurebank.order.mapper.PaymentOrderUpstreamMapper;
import com.futurebank.order.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


/**
 * 商户订单上游信息(PaymentOrderUpstream)表Service
 *
 * @author ben
 * @since 2024-04-02 10:42:04
 */
@Slf4j
@Service
public class PaymentOrderUpstreamService extends BaseService<PaymentOrderUpstreamMapper, PaymentOrderUpstreamEntity> {

    @Autowired
    private PaymentOrderUpstreamMapper paymentOrderUpstreamMapper;

    /**
     * @param orderRequest
     * @param orderId
     * @param postMap
     * @param postUrl
     * @param postHeader
     * @param txId
     * @param status                  仅为成功，该值赋"SUCCEEDED" 其余状态为""
     * @param upstreamResponseMessage
     */
    public void addPaymentOrderUpstream(PaymentRequest orderRequest, String orderId, Map<String, Object> postMap, String postUrl, Map<String, String> postHeader, String txId, String status, String upstreamResponseMessage, String PlatformNotifyUrl, String PlatformRedirectUrl, String succStatus) {
        log.info("orderRequest:{}    orderId:{}  postMap:{}  postUrl:{}  postHeader:{} txid:{}", orderRequest, orderId, postMap, postUrl, postHeader, txId);
        PaymentOrderUpstreamEntity paymentOrderUpstreamEntity = new PaymentOrderUpstreamEntity();
        paymentOrderUpstreamEntity.setUpstreamRequestUrl(postUrl);
        paymentOrderUpstreamEntity.setUpstrameHeader(postHeader == null ? null : JSON.toJSONString(postHeader));
        paymentOrderUpstreamEntity.setPlatformNotifyUrl(PlatformNotifyUrl == null ? "" : PlatformNotifyUrl);
        paymentOrderUpstreamEntity.setPlatformRedirectUrl(PlatformRedirectUrl == null ? "" : PlatformRedirectUrl);
        paymentOrderUpstreamEntity.setUpstreamRequestTime(new Date());

//        if (status.equals(succStatus))
//            paymentOrderUpstreamEntity.setUpstreamSuccessTime(new Date());
        paymentOrderUpstreamEntity.setUpstreamSuccessTime(new Date());
        paymentOrderUpstreamEntity.setUpstreamNotifyStatus(status);
        paymentOrderUpstreamEntity.setUpstreamNotifyTime(new Date());

        paymentOrderUpstreamEntity.setUpstreamRequestMessage(postMap == null ? null : JSON.toJSONString(postMap));
        paymentOrderUpstreamEntity.setUpstreamResponseMessage(upstreamResponseMessage);
        paymentOrderUpstreamEntity.setCreatedAt(new Date());
        paymentOrderUpstreamEntity.setUpdatedAt(new Date());
        paymentOrderUpstreamEntity.setDownstreamOrderNo(orderRequest.getReference());
        paymentOrderUpstreamEntity.setPlatformOrderNo(orderId);
        paymentOrderUpstreamEntity.setUpstreamOrderNo(txId);
        int i = paymentOrderUpstreamMapper.insert(paymentOrderUpstreamEntity);
        if (i == 0) {
            log.error("插入失败");
        }
    }


    public PaymentOrderUpstreamEntity getPaymentOrderUpstreamByUpstreamOrderNo(String upstreamOrderNo) {
        return paymentOrderUpstreamMapper.getPaymentOrderUpstreamByUpstreamOrderNo(upstreamOrderNo);
    }
}
