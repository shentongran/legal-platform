package com.example.legalplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.cloud.autoconfigure.RefreshAutoConfiguration.class,
        org.springframework.cloud.client.discovery.simple.SimpleDiscoveryClientAutoConfiguration.class,
        com.alibaba.cloud.nacos.discovery.NacosDiscoveryClientConfiguration.class,
        com.alibaba.cloud.nacos.discovery.NacosDiscoveryAutoConfiguration.class,
        com.alibaba.cloud.nacos.registry.NacosServiceRegistryAutoConfiguration.class,
        org.springframework.cloud.client.serviceregistry.ServiceRegistryAutoConfiguration.class
})
public class CaseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CaseServiceApplication.class, args);
    }
}