package com.imooc.springcloud;

import com.imooc.springcloud.entity.Friend;
import com.imooc.springcloud.service.IService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RequestCacheService {
    @Autowired
    private MyService myService;

    @CacheResult
    @HystrixCommand(commandKey = "cacheKeyAAA")
    public Friend requestCache(@CacheKey String name){
        log.info("request cache : {}",name);
        Friend friend = new Friend();
        friend.setName(name);
        friend = myService.sayHiPost(friend);
        log.info("after requesting chache result :{}" ,friend);
        return friend;
    }

}
