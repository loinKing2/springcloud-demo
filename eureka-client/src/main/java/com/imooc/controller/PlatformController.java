package com.imooc.controller;

import com.imooc.entity.Friend;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Setter
@RestController
@ConfigurationProperties(prefix = "server")
public class PlatformController {
    private Integer port;

    @GetMapping(value = "/sayHi")
    public String sayHi(){
        return "Hello eureka!  " + port;
    }

    @PostMapping(value = "/sayHi")
    public Friend sayHiPost(@RequestBody Friend friend){
        log.info("You are {}",friend.getName());
        friend.setPort(port);
        return friend;
    }

}
