package com.imooc.springcloud.controller;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/gateway")
public class GatewayController {

    Map<Long,Product> items = Maps.newConcurrentMap();

    @GetMapping("/details")
    public Product queryProduct(@RequestParam("pid") Long pid){
        items.putIfAbsent(pid, Product.builder().description("好吃不贵").stock(100L).productId(pid).build());
        return items.get(pid);
    }

    @PostMapping("/placeOrder")
    public String buy(@RequestParam Long pid){
        Product product = items.get(pid);
        if (product == null){
            return "Product not found";
        }else if (product.getStock() <= 0L){
            return "Sold out";
        }
        synchronized (this){
            if (product.getStock() <= 0L){
                return "Sold out";
            }
            product.setStock(product.getStock()-1);
        }
        return "Order Placed";
    }

}
