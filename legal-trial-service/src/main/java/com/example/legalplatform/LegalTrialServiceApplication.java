package com.example.legalplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.legalplatform.mapper")
public class LegalTrialServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LegalTrialServiceApplication.class, args);
    }
}
