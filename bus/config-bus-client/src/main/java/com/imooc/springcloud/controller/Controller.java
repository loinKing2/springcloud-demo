package com.imooc.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class Controller {
    @Value("${myWords}")
    private String words;

    @GetMapping("/words")
    public String getWords(){
        return words;
    }

}
