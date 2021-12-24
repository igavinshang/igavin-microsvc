package org.igavin.microsvc.canal.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.igavin.microsvc")
public class CanalJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanalJobApplication.class, args);
    }

}