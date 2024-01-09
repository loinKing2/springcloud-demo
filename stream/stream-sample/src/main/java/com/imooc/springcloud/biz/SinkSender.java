package com.imooc.springcloud.biz;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SinkSender {

    String name = "SinkSender";

    @Output(name)
    MessageChannel output();

}
