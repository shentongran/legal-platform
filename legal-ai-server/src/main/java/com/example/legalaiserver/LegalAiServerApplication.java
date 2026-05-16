package com.example.legalaiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.example.legalaiserver.config.AiConfig;

@SpringBootApplication
@EnableConfigurationProperties(AiConfig.class)
public class LegalAiServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LegalAiServerApplication.class, args);
    }
}