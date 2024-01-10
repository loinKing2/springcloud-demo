package com.imooc.springcloud.topics;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyTopic {

    String topic_name = "mytopic";

    //消息的消费端
    @Input(topic_name)
    SubscribableChannel input();

    @Output(topic_name)
    MessageChannel output();
}
