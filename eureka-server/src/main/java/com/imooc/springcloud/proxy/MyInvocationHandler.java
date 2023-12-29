package com.imooc.springcloud.proxy;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理方法");
        System.out.println(method.getName());
        RequestMapping annotation = method.getDeclaringClass().getAnnotation(RequestMapping.class);
        System.out.println(annotation);
        return null;
    }
}
