package com.imooc.springcloud.event;

import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@RemoteApplicationEventScan(basePackageClasses = MyEvent.class)
public class BusExtConfiguration {

}
