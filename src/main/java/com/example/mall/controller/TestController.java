package com.example.mall.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("log")
    public ResponseEntity<String> log(){
        return ResponseEntity.ok("Hello World");
    }
}
