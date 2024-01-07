package com.imooc.springcloud.config;

import com.imooc.springcloud.AuthResponse;
import com.imooc.springcloud.IAuthService;
import com.imooc.springcloud.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthFilter implements GatewayFilter, Ordered {

    private static final String AUTH = "Authorization";
    private static final String USERNAME = "imooc-user-name";
    @Autowired
    private IAuthService authService;



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Auth start...");
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(AUTH);
        String username = headers.getFirst(USERNAME);
        ServerHttpResponse response = exchange.getResponse();
        if (StringUtils.isBlank(token)){
            log.error("token not found");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        AuthResponse resp = authService.authVerifyToken(token,username);
        if (resp.getCode() != ResponseCode.SUCCESS){
            log.error("invalid token");
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }
        //TODO 将用户信息存在header中传递给下游业务
        ServerHttpRequest newRequest = request.mutate().header(USERNAME, username).build();
        //TODO 如果响应中需要放数据，也可以直接放在response的header中
        response.getHeaders().add(USERNAME,username);
        return chain.filter(
                exchange.mutate()
                        .request(newRequest)
                        .response(response)
                        .build()
        );
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
