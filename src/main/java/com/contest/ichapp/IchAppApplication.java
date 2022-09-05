package com.contest.ichapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
@MapperScan("com.contest.ichapp.mapper")
public class IchAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(IchAppApplication.class, args);
    }

}
