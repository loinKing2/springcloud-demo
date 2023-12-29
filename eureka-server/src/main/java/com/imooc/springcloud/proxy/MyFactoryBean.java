package com.imooc.springcloud.proxy;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MyFactoryBean implements FactoryBean {
    private Class clz;

    public MyFactoryBean(Class clz){
        this.clz = clz;
    }

    @Override
    public Object getObject() throws Exception {
        InvocationHandler handler = new MyInvocationHandler();
        return Proxy.newProxyInstance(clz.getClassLoader(), new Class[]{clz}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return this.clz;
    }
}
