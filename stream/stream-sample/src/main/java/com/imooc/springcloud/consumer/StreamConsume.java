package com.imooc.springcloud.consumer;

import com.imooc.springcloud.biz.MessageBean;
import com.imooc.springcloud.topics.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@EnableBinding({MyTopic.class,DLQTopic.class,FallbackTopic.class, GroupTopic.class, DelayedTopic.class, ReQueueTopic.class, ErrorTopic.class})
public class StreamConsume {

    private AtomicInteger count = new AtomicInteger(1);

    @StreamListener(MyTopic.topic_name)
    public void consumeMyMessage(Object payload){
        log.info("My message consumed successfully,payload={}",payload);
    }

    @StreamListener(GroupTopic.input)
    public void consumerGroupMessage(Object payload){
        log.info("My group message consumed successfully,payload={}",payload);
    }


    @StreamListener(DelayedTopic.input)
    public void consumeDelayMessage(MessageBean payload){
        log.info("delayed message consumed successfully,payload={}",payload);
    }



    /**
     * 异常重试 + 单机版 由当前客户端负责重试，超出重试次数还没有成功，则直接对外部抛出异常
     * @param payload
     */
    @StreamListener(ErrorTopic.input)
    public void consumeErrorMsg(MessageBean payload){
        log.info("Are you okay?");
        if (count.incrementAndGet() % 3 == 0){
            log.info("Fine,thank you. And you?");
            count.set(0);
        }else{
            log.info("What's your problem?");
            throw new RuntimeException("I am not okay!");
        }
    }


    /**
     * 异常重试 + 联机版 重新入队
     * @param payload
     */
    @StreamListener(ReQueueTopic.input)
    public void consumeRequeue(MessageBean payload) {
        log.info("Are you ok?");
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        throw new RuntimeException("I'm not ok!");
    }



    /**
     * DLQ死信队列测试
     * @param payload
     */
    @StreamListener(DLQTopic.input)
    public void dlqReceived(MessageBean payload){
        log.info("Are you okay?(dlq)");
        if (count.incrementAndGet() % 3 == 0){
            log.info("Fine,thank you. And you?(dlq)");
        }else{
            log.info("What's your problem?(dlq)");
            throw new RuntimeException("I am not okay!(dlq)");
        }
    }


    /**
     * fallback测试 + 升级版本
     * @param payload
     */
    @StreamListener(FallbackTopic.input)
    public void goodbyeBadGuy(MessageBean payload, @Header("version") String version){
        log.info("Are you okay?(fallback) : version = {}",version);
        if ("1.0".equalsIgnoreCase(version)){
            log.info("Fine,thank you. And you?(dlq) : version = {}",version);
        }else if ("2.0".equalsIgnoreCase(version)){
            log.info("unsupported version");
            throw new RuntimeException("unsupported version");
        }else{
            log.info("Fallback -version = {}",version);
        }
    }

    /**
     * 处理降级流程，在application.yml当中配置重试相关，如果在规定的次数内消费者仍然抛出异常，则会进入到此方法当中处理降级流程
     * @param message
     */
    @ServiceActivator(inputChannel = "fallback_topic.fallback-group.errors")
    public void fallback(Message<?> message){
        Object payload = message.getPayload();
        MessageHeaders headers = message.getHeaders();
        log.info("fallback entered");
    }
}
