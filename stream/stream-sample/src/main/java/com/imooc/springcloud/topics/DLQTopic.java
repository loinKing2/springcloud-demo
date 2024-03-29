package com.imooc.springcloud.topics;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 死信队列
 */
public interface DLQTopic {

    String input = "dlqTopic-consumer";

    String output = "dlqTopic-producer";

    //消息的消费端
    @Input(input)
    SubscribableChannel input();

    @Output(output)
    MessageChannel output();
}
