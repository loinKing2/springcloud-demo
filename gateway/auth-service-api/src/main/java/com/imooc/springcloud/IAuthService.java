package com.imooc.springcloud;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("auth-service-api")
public interface IAuthService {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    AuthResponse login(@RequestParam String username,@RequestParam String password);

    @GetMapping("/verify")
    @ResponseBody
    AuthResponse authVerifyToken(@RequestParam String token,String username);


    @PostMapping("/refreshToken")
    @ResponseBody
    AuthResponse refreshToken(String refreshToken);
}
