package com.cloud.service.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/rest")
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    @HystrixCommand(fallbackMethod="fallback")
    public String getEurekaServer(){

        System.out.print("Hello");

        return restTemplate.getForObject("http://cloud-microservices/rest/service",String.class);


    }

    public  String fallback(Throwable hystrixError){
        return "Unable to access server ";
    }









}
