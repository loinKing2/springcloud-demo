package com.imooc.springcloud.controller;

import com.imooc.springcloud.MyService;
import com.imooc.springcloud.RequestCacheService;
import com.imooc.springcloud.entity.Friend;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private MyService myService;
    @Autowired
    private RequestCacheService requestCacheService;

    @GetMapping("/fallback")
    public String fallback(){
        return myService.error();
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    },fallbackMethod = "timeoutFallback")
    @GetMapping("/timeout")
    public String timeout(Long timeout){
        return myService.retry(timeout);
    }

    public String timeoutFallback(Long timeout){;
        return "success - timeoutfallback!!!!!!";
    }

    @GetMapping("/cache")
    public Friend cache(String name){
        try (HystrixRequestContext context = HystrixRequestContext.initializeContext()){
            requestCacheService.requestCache(name);
            return requestCacheService.requestCache(name + "!");
        }
    }

}
