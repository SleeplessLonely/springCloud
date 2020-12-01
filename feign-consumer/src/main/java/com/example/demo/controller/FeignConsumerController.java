package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.HelloService;

@RestController
public class FeignConsumerController {
	@Autowired
	HelloService helloService;
	@GetMapping(value = "/feign-consumer")
	public String helloConsumer() {
		return helloService.hello();
	}
	@GetMapping(value = "/feign-consumer2")
	public String helloConsumer2() {
		StringBuilder sb = new StringBuilder();
		sb.append(helloService.hello()).append("\r\n")
		.append(helloService.hello("DIDI")).append("\r\n")
		.append(helloService.hello("DIDI",30)).append("\r\n")
		.append(helloService.hello(new User("DIDI",24))).append("\r\n");
		System.out.println(sb.toString());
		return sb.toString();
	}
}
