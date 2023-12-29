package com.imooc.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * config-server集成eureka
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigServerEurekaApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServerEurekaApp.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
