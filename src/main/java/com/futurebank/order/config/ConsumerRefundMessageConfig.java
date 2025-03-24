package com.futurebank.order.config;

import com.futurebank.order.service.payin.PayinRefundProcessor;
import com.futurebank.rocketmq.MqConsumerBinding;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author ljh
 * @date 2025/3/21 16:13
 * @description 消费退款消息
 **/

@Slf4j
@Configuration
@MqConsumerBinding(name ="RefundMessageTopic")
public class ConsumerRefundMessageConfig implements MessageListenerConcurrently{

    @Resource
    private PayinRefundProcessor payinRefundProcessor;


    @SneakyThrows
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for (MessageExt messageExt : list) {

            String body =new String(messageExt.getBody(), StandardCharsets.UTF_8);
            log.info("notify Received: body={} messageId={}",  body, messageExt.getMsgId());

            if (StringUtils.isBlank(body)) {
                continue;
            }

            try {
                payinRefundProcessor.processRefund(body);
            } catch (Exception e) {
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
