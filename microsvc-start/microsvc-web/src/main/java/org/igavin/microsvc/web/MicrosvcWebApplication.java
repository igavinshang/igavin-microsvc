package org.igavin.microsvc.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@MapperScan("org.igavin.microsvc.dao.mapper")
@ComponentScan(value = {"org.igavin.microsvc"})
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MicrosvcWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicrosvcWebApplication.class, args);
    }

}