package com.imooc.springcloud.controller;

import com.imooc.springcloud.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private IService iService;
    @GetMapping("sayHi")
    public String sayHi(){
        return "Hi";
    }

}
