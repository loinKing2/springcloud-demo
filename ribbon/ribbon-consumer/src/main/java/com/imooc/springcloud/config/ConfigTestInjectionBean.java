package com.imooc.springcloud.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;


@Configuration
public class ConfigTestInjectionBean {


    @Bean
    public LocalDate curDate(){
        return LocalDate.now();
    }

    @Bean
    @Qualifier
    public LocalDate nextYear(){
        return LocalDate.now().plusYears(1);
    }


}
