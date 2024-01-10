package com.imooc.springcloud.controller;

import com.imooc.springcloud.biz.MessageBean;
import com.imooc.springcloud.biz.SinkSender;
import com.imooc.springcloud.topics.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    @Autowired
    private SinkSender sinkSender;
    @Autowired
    private MyTopic myTopic;
    @Autowired
    private GroupTopic groupTopic;
    @Autowired
    private DelayedTopic delayedTopic;
    @Autowired
    private ErrorTopic errorTopic;
    @Autowired
    private ReQueueTopic reQueueTopic;
    @Autowired
    private DLQTopic dlqTopic;
    @Autowired
    private FallbackTopic fallbackTopic;

    @GetMapping("/send")
    public String sendMessage(String payload){
        sinkSender.output().send(MessageBuilder.withPayload(payload).build());
        return "successfully";
    }


    @GetMapping("/publisher")
    public String publisher(String payload){
        myTopic.output().send(MessageBuilder.withPayload(payload).build());
        return "success";
    }

    @GetMapping("/publisherToGroup")
    public String publisherToGroup(String payload){
        groupTopic.output().send(MessageBuilder.withPayload(payload).build());
        return "success";
    }

    /**
     * 发送延迟消息
     * @param payload
     * @param seconds
     * @return
     */
    @GetMapping("/sendDelayedMessage")
    public String sendDelayedMessage(@RequestParam("payload") String payload,
                                     @RequestParam("seconds") Integer seconds){
        MessageBean bean = new MessageBean();
        bean.setPayload(payload);
        log.info("ready to send delayed message , currentTimeMillis={}",System.currentTimeMillis());
        delayedTopic.output().send(MessageBuilder.withPayload(bean)
                .setHeader("x-delay",seconds * 1000) //将延迟的秒数传递到header当中
                .build());
        return "success";
    }

    /**
     * 异常重试 + 单机版（客户端自己玩）
     * @param payload
     * @return
     */
    @GetMapping("/sendErrorMsg")
    public String sendErrorMsg(String payload){
        MessageBean bean = new MessageBean();
        bean.setPayload(payload);
        errorTopic.output().send(MessageBuilder.withPayload(bean).build());
        return "success";
    }

    /**
     * 异常重试 + 联机版*(重新入队，重新消费)
     * @param payload
     * @return
     */
    @GetMapping("/sendRequeueMsg")
    public String sendRequeueMsg(String payload){
        MessageBean bean = new MessageBean();
        bean.setPayload(payload);
        reQueueTopic.output().send(MessageBuilder.withPayload(bean).build());
        return "success";
    }


    /**
     * 死信队列测试
     * @param payload
     * @return
     */
    @GetMapping("/senDldMessage")
    public String senDldMessage(String payload){
        MessageBean bean = new MessageBean();
        bean.setPayload(payload);
        dlqTopic.output().send(MessageBuilder.withPayload(bean).build());
        return "success";
    }


    /**
     * fallback测试 + 升版
     * @param payload 有效载荷
     * @param version 版本
     * @return
     */
    @GetMapping("/sendMessageToFallback")
    public String sendMessageToFallback(@RequestParam String payload,@RequestParam(name = "version",defaultValue = "1.0") String version){
        MessageBean bean = new MessageBean();
        bean.setPayload(payload);
        //place order
        //placeOrderV1  placeOrderV2
        fallbackTopic.output().send(MessageBuilder.withPayload(bean)
                        .setHeader("version",version)
                        .build());
        return "success";
    }

}
