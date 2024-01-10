package com.imooc.springcloud.topics;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 死信队列
 */
public interface FallbackTopic {

    String input = "fallback-topic-consumer";

    String output = "fallback-topic-producer";

    //消息的消费端
    @Input(input)
    SubscribableChannel input();

    @Output(output)
    MessageChannel output();
}
