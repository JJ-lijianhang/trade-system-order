package com.futurebank.order.controller;

import com.futurebank.order.service.payin.PayinRefundProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settlement")
@Slf4j
public class SettlementController {


    @Autowired
    private PayinRefundProcessor payinRefundProcessor;


    @GetMapping("/payinRefundProcessor")
    public void payinRefundProcessor() throws Exception {
        log.info("payinRefundProcessor.processRefund() start");
        try {
            payinRefundProcessor.processRefund();
        } catch (Exception e) {
            log.error("payinRefundProcessor.processRefund() error", e);
        }
    }
    /**
     * 本地退款返还
     * @throws Exception
     */
    @GetMapping("/refundBackProcess")
    public void refundBackProcess() throws Exception {
        log.info("local service process refundBack start");
        try {
            payinRefundProcessor.refundBack();
        } catch (Exception e) {
            log.error("payinRefundProcessor.processRefund() error", e);
        }
    }

}
