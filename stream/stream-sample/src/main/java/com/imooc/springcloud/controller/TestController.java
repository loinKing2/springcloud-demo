package com.imooc.springcloud.controller;

import com.imooc.springcloud.biz.SinkSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private SinkSender sinkSender;

    @GetMapping("/send")
    public String sendMessage(String payload){
        sinkSender.output().send(MessageBuilder.withPayload(payload).build());
        return "successfully";
    }


}
