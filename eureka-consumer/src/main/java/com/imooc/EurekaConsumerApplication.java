package com.imooc;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class EurekaConsumerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaConsumerApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
