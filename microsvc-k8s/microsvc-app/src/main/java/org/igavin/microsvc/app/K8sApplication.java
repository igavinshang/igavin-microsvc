package org.igavin.microsvc.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("org.igavin.microsvc.dao.mapper")
@ComponentScan(value = {"org.igavin.microsvc"})
@SpringBootApplication
public class K8sApplication {

    public static void main(String[] args) {
        SpringApplication.run(K8sApplication.class, args);
    }

}