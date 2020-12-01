package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("eureka-client")
public interface RefactorHelloService extends HelloService {

}
