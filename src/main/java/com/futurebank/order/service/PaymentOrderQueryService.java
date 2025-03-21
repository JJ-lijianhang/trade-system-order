package com.futurebank.order.service;

import com.futurebank.pojo.base.BaseReq;
import com.futurebank.pojo.base.CommonResp;
import com.futurebank.pojo.vo.payment.PaymentRequest;
import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.entity.PaymentProviderEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * 支付
 *
 * @author ben
 * @date 2024/2/21 9:49
 * 1=创建代收订单 2=代收通知回调 3=创建代付订单 4=代付通知回调 5=订阅收款  6=退款 7=查询代收订单（商户订单号） 8=查询代付订单  9=退款查询 10=跳转地址   11=汇率查询 12=对账文件导出
 **/

public interface PaymentOrderQueryService {


    /**
     * 上游支付订单查询
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    CommonResp upstreamTransacationQuery(PaymentOrderEntity paymentOrderEntity, PaymentProviderEntity paymentProviderEntity) throws Exception;


}
