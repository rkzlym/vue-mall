package com.example.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mall.mapper")
public class VueMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueMallApplication.class, args);
    }

}
