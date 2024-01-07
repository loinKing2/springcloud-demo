package com.imooc.springcloud.config;

import com.imooc.springcloud.proxy.BodyHackerFunction;
import com.imooc.springcloud.proxy.BodyHackerHttpResponseDecorator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ErrorFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        BodyHackerFunction function = (response,publisher) -> Flux.from(publisher).flatMap(o -> {
            byte[] orgContent = new byte[o.readableByteCount()];
            o.read(orgContent);
            String content = new String(orgContent);
            if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR){
                content = String.format("{\"status\":%d,\"path\":\"%s\"}",response.getStatusCode().value(),request.getPath().value());
            }
            // 告知客户端Body的长度，如果不设置的话客户端会一直处于等待状态不结束
            HttpHeaders headers = response.getHeaders();
            headers.setContentLength(content.length());
            return response.writeWith(Flux.just(content).map(bx -> response.bufferFactory().wrap(bx.getBytes())));
        }).then();

        BodyHackerHttpResponseDecorator decorator = new BodyHackerHttpResponseDecorator(function,exchange.getResponse());
        return chain.filter(exchange.mutate().response(decorator).build());
    }

    @Override
    public int getOrder() {
        return -2;
    }
}
