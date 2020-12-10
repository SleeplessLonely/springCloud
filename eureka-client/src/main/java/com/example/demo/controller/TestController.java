package com.example.demo.controller;
import java.util.List;
import java.util.Random;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;


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
public class TestController {
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	private Registration registration;
	@Autowired
	private DiscoveryClient client;
    @GetMapping("/hello")
    public String hello() throws InterruptedException{
    	ServiceInstance instance = serviceInstance();
    	// 测试超时
    	int sleepTime = new Random().nextInt(3000);
    	Thread.sleep(sleepTime);
    	logger.info("/hello,host:port= "+ instance.getUri() + ", service_id= " + instance.getServiceId());
        return "/hello,host:port= "+ instance.getUri() + ", service_id= " + instance.getServiceId();
    }
    @GetMapping(value = "hello1")
    public String hello(@RequestParam String name) {
    	return  "Hello " + name;
    }
    @GetMapping(value = "hello2")
    public User hello(@RequestHeader String name,@RequestHeader Integer age) {
    	return  new User(name,age);
    }
    @PostMapping(value = "hello3")
    public String hello(@RequestBody User user) {
    	return "Hello " + user.getName()+" , " + user.getAge();
    }
    public ServiceInstance serviceInstance() {
    	List<ServiceInstance> instances = client.getInstances(registration.getServiceId());
    	if(instances != null && instances.size() >0) {
    		for(ServiceInstance itm : instances) {
    			if(itm.getPort() == 8081) {
    				return itm;
    			}
    		}
    	}
		return null;
    }
}
