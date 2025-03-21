package com.futurebank.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.futurebank.pojo.enums.PaymentStatusEnum;
import com.futurebank.pojo.utils.DateUtils;
import com.futurebank.pojo.utils.ValidationUtils;
//import com.futurebank.pojo.vo.NotifyResponse;
//import com.futurebank.pojo.vo.PayinNotifyMessage;
import com.futurebank.cache.RedisCache;
import com.futurebank.order.entity.PaymentOrderDownstreamEntity;
import com.futurebank.order.entity.PaymentOrderEntity;
import com.futurebank.order.entity.PaymentProviderEntity;
import com.futurebank.order.service.*;
import com.futurebank.order.service.payin.PayinOrderNotifyService;
import com.futurebank.order.service.payin.PayinRefundQueryService;
import com.futurebank.order.service.producer.SendMQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ben
 * @date 2024/3/21 17:31
 **/
@Slf4j
@Service
public class PayinRefundQueryServiceImpl implements PayinRefundQueryService {

    @Autowired
    private PaymentOrderService paymentOrderService;

    @Autowired
    private PaymentOrderDownstreamService paymentOrderDownstreamService;

    @Autowired
    private HttpClientService httpClientService;


    @Autowired
    private RedisCache redisCache;

    @Override
    public void refundQuery(PaymentOrderEntity paymentOrderEntity, PaymentProviderEntity paymentProviderEntity) throws Exception {

    }



}
