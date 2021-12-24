package org.igavin.microsvc.trace.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient(autoRegister = true)
@EnableFeignClients
@SpringBootApplication
public class TraceWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TraceWebApplication.class, args);
    }

}
