package com.imooc.springcloud.controller;

import com.imooc.springcloud.entity.Friend;
import com.imooc.springcloud.service.IService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Setter
@RestController
@Slf4j
@ConfigurationProperties(prefix = "server")
public class Controller implements IService {
    private Integer port;

    public String sayHi(){
        return "Hello eureka!  " + port;
    }

    public Friend sayHiPost(@RequestBody Friend friend){
        log.info("You are {}",friend.getName());
        friend.setPort(port);
        return friend;
    }

    @Override
    public String retry(Long timeout) {
        timeout = timeout == null ? 0 :timeout;
        while (--timeout >=0){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        log.info("retry : {}",port);
        return Objects.toString(port);
    }

    @Override
    public String error() {
        throw new RuntimeException("black sheep!");
    }
}
