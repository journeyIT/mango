package com.louis.mango.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: journey
 * @Date: 2020/2/17
 * @Time: 2:24 下午
 * @Description:
 */
@RequestMapping
@RestController
public class HelloSpringBoot {
    @GetMapping(value= "/hello" )
    public String hello(){
        return "Hello SpringBoot";
    }
}
