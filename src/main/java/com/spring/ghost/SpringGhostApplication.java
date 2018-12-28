package com.spring.ghost;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.spring.ghost.mapper")
@SpringBootApplication
public class SpringGhostApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGhostApplication.class, args);
    }

}

