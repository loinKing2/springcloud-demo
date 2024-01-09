package com.imooc.springcloud.biz;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface SinkReceiver {
    String name = "SinkSender";

    @Input(name)
    SubscribableChannel input();
}
