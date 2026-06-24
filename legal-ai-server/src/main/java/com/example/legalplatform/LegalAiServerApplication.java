package com.example.legalplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import com.example.legalplatform.config.AiConfig;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(AiConfig.class)
public class LegalAiServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LegalAiServerApplication.class, args);
    }
}
