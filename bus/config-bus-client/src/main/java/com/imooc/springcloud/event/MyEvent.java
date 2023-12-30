package com.imooc.springcloud.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class MyEvent extends RemoteApplicationEvent {

    public MyEvent() {
    }

    public MyEvent(Object source, String originService, String destinationService) {
        super(source, originService, destinationService);
    }

    public MyEvent(Object source, String originService) {
        super(source, originService);
    }

}
