package com.imooc.controller;

import com.imooc.entity.Friend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class Controller {
    @Autowired
    private LoadBalancerClient client; //简易的负载均衡器
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello(){
        ServiceInstance instance = client.choose("eureka-client");
        if (instance == null){
            return "No available instances";
        }
        String target = String.format("http://%s:%d/sayHi",instance.getHost(),instance.getPort());
        log.info("url is {}",target);
        //发起真实的服务调用
        return restTemplate.getForObject(target,String.class);
    }


    @PostMapping("/hello")
    public Friend hello4Post(){
        ServiceInstance instance = client.choose("eureka-client");
        if (instance == null){
            return null;
        }
        String target = String.format("http://%s:%d/sayHi",instance.getHost(),instance.getPort());
        log.info("url is {}",target);
        //发起真实的服务调用
        Friend friend = new Friend();
        friend.setName("张三丰");
        friend.setPort(8996);
        return restTemplate.postForObject(target,friend,Friend.class);
    }

}
