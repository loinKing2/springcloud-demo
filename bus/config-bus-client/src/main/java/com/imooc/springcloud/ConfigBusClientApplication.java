package com.imooc.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigBusClientApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigBusClientApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
    /*
           spring-cloud-bus 组件的使用步骤
           1. pom.xml引入依赖（如果是使用rabbitmq，则引入下面的依赖）
             <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-bus-amqp</artifactId>
             </dependency>
            如果是kafka 则引入kafka对应的依赖
            另外必须引入actuator相关依赖
            做高可用则要引入eureka作为注册中心

         2. 在application.yml文件新增mq相关配置，如果项目当中同时引入了rabbit和kafka，则需要指定default-binder使用哪个MQ

         spring:
              application:
                name: config-bus-client
              rabbitmq:
                host: localhost
                username: guest
                password: guest
                virtual-host: /
                port: 5672
              cloud:
                stream:
                  #如果项目同时引入了rabbit与kafka 则需要指出默认的binder是哪一个
                  default-binder: rabbit

        3. 如果配置属性发生变更，那么则可以在bus-server或者任意一台bus-client调用如下连接刷新配置属性
        POST http://localhost:60105/actuator/bus-refresh
            Accept: application/octet-stream
        返回204代码，表示从github拉取最新的配置成功

        4. 查看最新的配置
        相关属性注入的地方需要使用@RefreshScope的注解

     */

}
