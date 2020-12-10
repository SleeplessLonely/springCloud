package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "eureka-client", contextId = "interface")
public interface RefactorHelloService extends HelloService {

}
