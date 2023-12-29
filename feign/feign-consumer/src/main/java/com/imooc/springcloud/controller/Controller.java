package com.imooc.springcloud.controller;

import com.imooc.springcloud.entity.Friend;
import com.imooc.springcloud.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private IService iService;

    @GetMapping("/sayHi")
    public String sayHi(){
        return iService.sayHi();
    }


    @PostMapping("/sayHi")
    public Friend sayHiForPost(@RequestBody Friend friend){
        friend.setPort(9999);
        return friend;
    }

    @GetMapping("/retry")
    public String retry(Long timeout){
        return iService.retry(timeout);
    }


}
