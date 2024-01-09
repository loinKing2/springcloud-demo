package com.imooc.springcloud.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Slf4j
@EnableBinding(value = {SinkReceiver.class,SinkSender.class})
public class StreamConsumer {

    @StreamListener(SinkReceiver.name)
    public void consume(Object payload){
        log.info("message consumed successfully payload = {}",payload);
    }

}
