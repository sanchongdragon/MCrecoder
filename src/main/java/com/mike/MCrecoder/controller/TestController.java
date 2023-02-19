package com.mike.MCrecoder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @GetMapping(value = "/")
    public String helloWorld(){
        log.info("hello world has been called !");
        return "Hello World!";
    }

}
