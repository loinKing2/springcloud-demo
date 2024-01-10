package com.imooc.springcloud.topics;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ErrorTopic {

    String input = "error-retry-topic-consumer";

    String output = "error-retry-topic-producer";

    //消息的消费端
    @Input(input)
    SubscribableChannel input();

    @Output(output)
    MessageChannel output();

}
