package com.imooc.springcloud.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/base-api")
public interface IService {

    @GetMapping("/index")
    String index();

}
