package com.imooc.springcloud.biz;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class MessageBean implements Serializable {

    private String payload;

}
