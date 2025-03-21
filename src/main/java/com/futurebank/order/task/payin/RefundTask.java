package com.futurebank.order.task.payin;

import com.futurebank.order.service.payin.PayinRefundProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ben
 * @date 2024/3/31 23:18
 **/
@Slf4j




@Component
public class RefundTask {

    @Autowired
    private PayinRefundProcessor payinRefundProcessor;

    /**
     * 退款处理
     * @throws Exception
     */
    //@Scheduled(initialDelay = 5000L, fixedDelay = 30000L)
//    @Scheduled(cron = "0 05 13 * * ?")
    public void upStreamRefund() throws Exception {
        log.info("payinRefundProcessor.processRefund() start");
        try {
            payinRefundProcessor.processRefund("");
        } catch (Exception e) {
            log.error("payinRefundProcessor.processRefund() error", e);
        }
    }

    /**
     * 退款返还
     * @throws Exception
     */
    @Scheduled(initialDelay = 5000L, fixedDelay = 30000L)
    public void platRefundBack() throws Exception {
        try {
            payinRefundProcessor.refundBack();
        } catch (Exception e) {
            log.error("payinRefundProcessor.processRefund() error", e);
        }
    }
}
