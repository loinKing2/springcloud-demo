package com.imooc.springcloud;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("auth-service-api")
public interface IAuthService {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    AuthResponse login(@RequestParam("username") String username,@RequestParam("password") String password);

    @GetMapping("/verify")
    @ResponseBody
    AuthResponse authVerifyToken(@RequestParam("token") String token,@RequestParam("username") String username);


    @PostMapping("/refreshToken")
    @ResponseBody
    AuthResponse refreshToken(@RequestParam("refreshToken") String refreshToken);
}
