package com.imooc.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;

import java.time.ZonedDateTime;

@Configuration
public class GatewayConfiguration {
    @Autowired
    private TimerFilter timerFilter;
    @Autowired
    private AuthFilter authFilter;
    /**
     * 自定义断言规则
     * @param builder
     * @return
     */
    @Bean
    @Order
    public RouteLocator customizedRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/java/**")  //匹配这种路径的请求
                        .and()
                        .method(HttpMethod.GET) //必须是get请求
                        .and()
                        .header("name") //header必须有一个叫做name的属性
                        .filters(f -> f.stripPrefix(1) //会将java替换为具体的微服务名称
                                .addResponseHeader("java-param","gateway-config")
                                .filter(timerFilter)
                                .filter(authFilter)  //权限验证
                        )
                        .uri("lb://FEIGN-CLIENT")
                ).route(r -> r.path("/seckill/**")
                        .and()
                        .after(ZonedDateTime.now().plusMinutes(1)) //启动后一分钟后生效
//                        .and()
//                      .before()
//                      .between()
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://FEIGN-CLIENT")
                )
                .build();
    }

}
