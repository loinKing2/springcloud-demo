package com.imooc.springcloud;

import com.imooc.springcloud.hystrix.Fallback;
import com.imooc.springcloud.service.IService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "feign-client",fallback = Fallback.class)
public interface MyService extends IService {
}
