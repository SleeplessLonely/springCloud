package com.example.hystrix.model;

import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class UserCommand extends HystrixCommand<User> {
	private RestTemplate restTemplate;
	private Long id;
	
	public UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
		super(setter);
		this.id = id;
		this.restTemplate = restTemplate;
	}
	

	@Override
	protected User run() throws Exception {
		return restTemplate.getForObject("http://eureka-client/users", User.class, id);
	}
}
