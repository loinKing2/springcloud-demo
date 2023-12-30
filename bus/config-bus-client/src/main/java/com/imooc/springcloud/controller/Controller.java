package com.imooc.springcloud.controller;


import com.imooc.springcloud.event.MyEvent;
import com.imooc.springcloud.pojo.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RefreshScope
@RestController
public class Controller {
    @Value("${myWords}")
    private String words;
    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/words")
    public String getWords(){
        return words;
    }


    @PostMapping("/pushEvent")
    public Boolean pushEvent(@RequestBody Payload payload){
        MyEvent event = new MyEvent(payload, UUID.randomUUID().toString());
        try {
            publisher.publishEvent(event);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
