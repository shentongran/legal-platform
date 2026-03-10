package com.example.legalplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.legalplatform.mapper")
@SpringBootApplication
public class LegalPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(LegalPlatformApplication.class, args);
    }
}