package com.example.legaltrialservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.legaltrialservice.mapper")
public class LegalTrialServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LegalTrialServiceApplication.class, args);
    }
}