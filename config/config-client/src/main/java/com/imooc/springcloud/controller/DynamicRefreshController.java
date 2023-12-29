package com.imooc.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dynamic")
@RefreshScope  //配置文件的值动态刷新必须要添加此注解，另外还要在application.yml进行配置管理
public class DynamicRefreshController {

    @Value("${words}")
    private String words;
    @Value("${food}")
    private String food;

    @GetMapping("/getWords")
    public String getWords(){
        return words;
    }

    @GetMapping("/food")
    public String dinner(){
        return "May I have one " + food;
    }

}
