package com.example.legalplatform.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.legalplatform.mapper")
public class LegalDocumentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LegalDocumentServiceApplication.class, args);
    }
}