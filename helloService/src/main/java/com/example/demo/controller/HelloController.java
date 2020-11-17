package com.example.demo.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	private Registration registration;
	@Autowired
	private DiscoveryClient client;
    @GetMapping("/hello")
    public String hello(){
    	ServiceInstance instance = serviceInstance();
    	logger.info("/hello,host:port= "+ instance.getUri() + ", service_id= " + instance.getServiceId());
        return "/hello,host:port= "+ instance.getUri() + ", service_id= " + instance.getServiceId();
    }
    public ServiceInstance serviceInstance() {
    	List<ServiceInstance> instances = client.getInstances(registration.getServiceId());
    	if(instances != null && instances.size() >0) {
    		for(ServiceInstance itm : instances) {
    			if(itm.getPort() == 8082) {
    				return itm;
    			}
    		}
    	}
		return null;
    }
}
