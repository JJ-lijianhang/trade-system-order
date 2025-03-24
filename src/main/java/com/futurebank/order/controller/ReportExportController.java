package com.futurebank.order.controller;


import com.alibaba.fastjson.JSON;
import com.futurebank.pojo.base.CommonResp;
import com.futurebank.rocketmq.NotifyEvent;
import com.futurebank.rocketmq.RocketMQProducer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/test")
public class ReportExportController {

    @Resource
    private RocketMQProducer rocketMQProducer;


    @PostMapping("/textMq")
    public CommonResp<String> export(@RequestParam("messageBody") String messageBody) {
        String topic = "RefundMessageTopic";
        NotifyEvent event = new NotifyEvent();
        event.setData(JSON.toJSONString(messageBody));
        event.setKey(topic);
        rocketMQProducer.sendMessage(event);

        return CommonResp.ok(true);
    }
}
