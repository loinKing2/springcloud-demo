package com.imooc.springcloud;

import com.imooc.springcloud.register.MyImportBeanDefinitionRegister;
import com.imooc.springcloud.service.IService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * Eureka注册中心
 */
@SpringBootApplication
@EnableEurekaServer
@Import(MyImportBeanDefinitionRegister.class)
public class EurekaServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EurekaServerApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);


        IService service = context.getBean(IService.class);
        service.index();
    }

}
