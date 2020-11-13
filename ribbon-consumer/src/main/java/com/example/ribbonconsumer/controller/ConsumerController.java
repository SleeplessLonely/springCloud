package com.example.ribbonconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <li>Title:</li>
 * <li>Description:</li>
 * <li>Copyright: Copyright (c) 2020/11/12</li>
 * <li>Company: Sunshine Insurance Group Co., Ltd.</li>
 *
 * @version 1.0 修改记录： 修改序号，修改日期，修改人，修改内容
 * @Author: anxindong
 */
@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping(value="/ribbonConsumer" )
    public String helloConsumer(){
    	ResponseEntity<String> forEntity = restTemplate.getForEntity("http://EUREKA-CLIENT/hello",String.class);
    	String body = forEntity.getBody();
    	System.out.println(body);
        return body;
    }
}
