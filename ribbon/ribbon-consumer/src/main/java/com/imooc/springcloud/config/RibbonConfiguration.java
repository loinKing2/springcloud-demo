package com.imooc.springcloud.config;

import com.imooc.springcloud.irule.ConsistenceHashRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClient(name = "EUREKA-CLIENT",configuration=ConsistenceHashRule.class)
public class RibbonConfiguration {


    @Bean
    public IRule consistenceHashRule(){
        return new ConsistenceHashRule();  //随机的负载均衡策略
    }


}
