//package com.futurebank.order.config;
//
//import com.futurebank.order.service.payin.PayinRefundNotifyService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.rocketmq.acl.common.AclClientRPCHook;
//import org.apache.rocketmq.acl.common.SessionCredentials;
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.apache.rocketmq.remoting.RPCHook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.nio.charset.StandardCharsets;
//
///**
// * @author ben
// * @date 2024/4/8 16:13
// * @description 向商户同步退费状态 - 消费者配置
// **/
//
//@Slf4j
//@Configuration
//public class ConsumerRefundNotifyConfig {
//
//
//    @Autowired
//    private PayinRefundNotifyService payinRefundNotifyService;
//
//    private static final String TOPIC = "RefundNotifyTopic";
//    private static final String CONSUMER_GROUP = "RefundNotifyGroup";
//
//
//    @Value("${rocketmq.accessKey}")
//    private String accessKey;
//    @Value("${rocketmq.secretKey}")
//    private String secretKey;
//    @Value("${rocketmq.endpoints}")
//    private String endpoints;
//
//
//    @Bean(name = "refundNotifyConsumer")
//    public DefaultMQPushConsumer delayOrderConsumer() throws MQClientException {
//
//
//        RPCHook aclHook = null;
//        if (StringUtils.isNotBlank(accessKey) && StringUtils.isNotBlank(secretKey)) {
//            aclHook = new AclClientRPCHook(new SessionCredentials(accessKey,secretKey));
//        }
//
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(CONSUMER_GROUP, aclHook, new AllocateMessageQueueAveragely());
//        consumer.setNamesrvAddr(endpoints);
//        consumer.setConsumeTimeout(3000);
//        consumer.setConsumeThreadMin(1);
//        consumer.setConsumeThreadMax(2);
//        consumer.subscribe(TOPIC,"*");
//        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
//
//
//            for (MessageExt messageExt : list) {
//
//                String body =new String(messageExt.getBody(), StandardCharsets.UTF_8);
//                log.info("notify Received: body={} messageId={}",  body, messageExt.getMsgId());
//                if (StringUtils.isBlank(body)) {
//                    continue;
//                }
//
//                boolean b = payinRefundNotifyService.payinRefundNotify(body);
//                log.info("notify body = {}  result = {}", body, b);
//                if (!b) {
//                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//                }
//            }
//            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//        });
//
//        // 启动 Consumer
//        consumer.start();
//        return consumer;
//    }
//
//}
