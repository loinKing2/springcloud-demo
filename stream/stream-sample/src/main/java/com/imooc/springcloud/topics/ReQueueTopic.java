package com.imooc.springcloud.topics;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ReQueueTopic {

    String input = "requeue-topic-consumer";

    String output = "requeue-topic-producer";

    //消息的消费端
    @Input(input)
    SubscribableChannel input();

    @Output(output)
    MessageChannel output();

}
