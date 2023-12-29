package com.imooc.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/property")
@RestController
public class Controller {
    @Value("${name}")
    private String name;
    @Value("${myWords}")
    private String words;

    @GetMapping(value = "/name")
    public  String getName(){
        return name;
    }

    @GetMapping(value = "/words")
    public  String getWords(){
        return words;
    }


}
