package com.imooc.springcloud.topics;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 死信队列
 */
public interface DLQTopic {

    String topic_name = "dlqTopic";

    //消息的消费端
    @Input(topic_name)
    SubscribableChannel input();

    @Output(topic_name)
    MessageChannel output();
}
