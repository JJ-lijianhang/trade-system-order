package com.futurebank.order.service.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author ben
 * @date 2024/3/23 11:49
 **/
@Slf4j
@Service
public class SendMQService {

//    @Autowired
//    private DefaultMQProducer refundNotify;

    /**
     * 订单状态同步
     *
     * @param messageBody
     */
    public void refundNotify(String messageBody) {
//
//        final ClientServiceProvider provider = ClientServiceProvider.loadService();
//
//        String topic = "RefundNotifyTopic";
//        byte[] body = messageBody.getBytes(StandardCharsets.UTF_8);
//
//        String tag = "*";
//        final Message message = provider.newMessageBuilder()
//                .setTopic(topic)
//                .setTag(tag)
//                .setBody(body)
//                .build();
//        try {
//            final SendReceipt sendReceipt = refundNotify.send(message);
//            log.info("Send message successfully, messageId={}", sendReceipt.getMessageId());
//        } catch (Throwable t) {
//            log.error("Failed to send message", t);
//        }
    }
}
