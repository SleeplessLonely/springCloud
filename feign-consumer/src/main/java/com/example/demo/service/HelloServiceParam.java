package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;

@FeignClient(name="eureka-client", contextId = "hello")
public interface HelloServiceParam {
	@RequestMapping("/hello")
	String hello();

	@GetMapping(value = "/hello1")
	String hello(@RequestParam("name") String name);

	@GetMapping(value = "/hello2")
	User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

	@PostMapping(value = "/hello3")
	String hello(@RequestBody User user);
}
