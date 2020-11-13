package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.HelloService;

@RestController
public class CousumerController {
	@Autowired
	HelloService helloService;
	@GetMapping(value = "/ribbonConsumer")
	public String helloConsumer() {
		return helloService.helloService();
	}
}
