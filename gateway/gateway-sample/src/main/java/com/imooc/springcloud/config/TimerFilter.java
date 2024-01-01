package com.imooc.springcloud.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class TimerFilter implements GatewayFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        StopWatch timer = new StopWatch();
        timer.start(exchange.getRequest().getURI().getRawPath());

        exchange.getAttributes().put("requestTimeBegin",System.currentTimeMillis());

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            timer.stop();
            System.out.println(timer.prettyPrint());

        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
