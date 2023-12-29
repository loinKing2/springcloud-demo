package com.imooc.springcloud.hystrix;

import com.imooc.springcloud.MyService;
import com.imooc.springcloud.entity.Friend;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Component("myService")
public class Fallback implements MyService {


    @Override
    public String sayHi() {
        return null;
    }

    @Override
    public Friend sayHiPost(Friend friend) {
        return null;
    }

    @Override
    public String retry(@RequestParam("timeout") Long timeout) {
        return "You are late!";
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallback2") //二级容错
    public String error() {
        log.info("Fallback : I'm not a black sheep any more!");
//        return "Fallback : I'm not a black sheep any more!";
        throw new RuntimeException("first fallback!!!");
    }

    @HystrixCommand(fallbackMethod = "fallback3") //二级容错
    public String fallback2(){
        log.info("fallback again!!!");
        throw new RuntimeException("fallback again!");
    }

    public String fallback3(){
        log.info("fallback again and again!!!");
        return "successful!!!";
    }

}
