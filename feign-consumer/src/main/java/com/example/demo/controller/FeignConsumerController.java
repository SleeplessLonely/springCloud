package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.HelloService;
import com.example.demo.service.HelloServiceParam;
import com.example.demo.service.RefactorHelloService;

@RestController
public class FeignConsumerController {
	@Autowired
	HelloServiceParam helloServiceParam;
	@Autowired
	RefactorHelloService refactorHelloService;
	@GetMapping(value = "/feign-consumer")
	public String helloConsumer() {
		return helloServiceParam.hello();
	}
	@GetMapping(value = "/feign-consumer2")
	public String helloConsumer2() {
		StringBuilder sb = new StringBuilder();
		sb.append(helloServiceParam.hello()).append("\r\n")
		.append(helloServiceParam.hello("DIDI")).append("\r\n")
		.append(helloServiceParam.hello("DIDI",30)).append("\r\n")
		.append(helloServiceParam.hello(new User("DIDI",24))).append("\r\n");
		System.out.println(sb.toString());
		return sb.toString();
	}
	@GetMapping(value = "/feign-consumer3")
	public String helloConsumer3() {
		StringBuilder sb = new StringBuilder();
		sb.append(refactorHelloService.hello("MIMI")).append("\r\n")
		.append(refactorHelloService.hello("MIMI",30)).append("\r\n")
		.append(refactorHelloService.hello(new User("MIMI",24))).append("\r\n");
		System.out.println(sb.toString());
		return sb.toString();
	}
}
