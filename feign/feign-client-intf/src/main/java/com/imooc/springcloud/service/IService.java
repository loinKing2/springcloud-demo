package com.imooc.springcloud.service;

import com.imooc.springcloud.entity.Friend;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "feign-client")
public interface IService {

    @GetMapping(value = "/sayHi")
    String sayHi();

    @PostMapping(value = "/sayHi")
    Friend sayHiPost(@RequestBody Friend friend);

    @GetMapping("/retry")
    String retry(@RequestParam("timeout") Long timeout);

    @GetMapping("/error")
    String error();
}
