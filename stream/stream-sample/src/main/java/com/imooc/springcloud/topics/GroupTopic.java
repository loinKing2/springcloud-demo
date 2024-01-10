package com.imooc.springcloud.topics;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface GroupTopic {

    String input = "group-topic-consumer";

    String output = "group-topic-producer";

    //消息的消费端
    @Input(input)
    SubscribableChannel input();

    @Output(output)
    MessageChannel output();

}
