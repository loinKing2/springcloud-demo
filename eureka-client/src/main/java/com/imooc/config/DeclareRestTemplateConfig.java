package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DeclareRestTemplateConfig {

    /**
     * 声明RestTemplate
     * @return
     */
    @Bean("restTemplate")
    public RestTemplate register(){
        return new RestTemplate();
    }

}
