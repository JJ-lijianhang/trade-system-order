package com.futurebank.order.service;

import com.futurebank.order.service.base.BaseService;
import com.futurebank.order.mapper.PaymentBillMapper;
import com.futurebank.order.entity.PaymentBillEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 支付账单表(PaymentBill)表Service
 *
 * @author ben
 * @since 2024-05-22 16:01:01
 */
@Service
public class PaymentBillService extends BaseService<PaymentBillMapper, PaymentBillEntity> {
    @Autowired
    private PaymentBillMapper mapper;


    public PaymentBillEntity selectByBillNo(String txid) {
        return mapper.selectByBillNo(txid);
    }
}
