package com.imooc.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;


@RestController
public class Controller {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    @Qualifier
    private List<LocalDate> localDateList;

    @GetMapping("sayHi")
    public String sayHi(@RequestParam("name") String name){
        return restTemplate.getForObject("http://EUREKA-CLIENT/sayHi",String.class);
    }

    @GetMapping("size")
    public Integer calcCollectionSize(){
        return localDateList.size();
    }

}
