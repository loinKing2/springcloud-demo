package com.imooc.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Controller {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    public AuthResponse login(@RequestParam String username,@RequestParam String password){
        Account acct = Account.builder()
                    .username(username)
                    .build();
        //TODO 验证username + password

        //假设username+password验证通过，生成token
        String token = jwtService.token(acct);
        acct.setToken(token);
        acct.setRefreshToken(UUID.randomUUID().toString());
        redisTemplate.opsForValue().set(acct.getRefreshToken(),acct);
        return AuthResponse.builder().account(acct).code(ResponseCode.SUCCESS).build();
    }

    @PostMapping("/refresh")
    public AuthResponse refreshToken(String refreshToken){
        Account o = (Account)redisTemplate.opsForValue().get(refreshToken);
        if (o == null){
            return AuthResponse.builder()
                    .code(ResponseCode.USER_NOT_FOUND)
                    .build();
        }
        String token = jwtService.token(o);
        o.setToken(token); //设置新的token
        o.setRefreshToken(UUID.randomUUID().toString()); //刷新refresh token
        redisTemplate.delete(refreshToken);
        return AuthResponse.builder().account(o).code(ResponseCode.SUCCESS).build();
    }

    @GetMapping("/verify")
    public AuthResponse verifyToken(String token,String username){
        boolean verifyRes = jwtService.verify(token, username);
        return AuthResponse.builder()
                .code(verifyRes ? ResponseCode.SUCCESS : ResponseCode.INVALID_TOKEN)
                .build();
    }

}
