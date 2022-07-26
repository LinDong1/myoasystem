package com.lindong.myoasystem;

import  org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lindong.myoasystem.mapper")
public class ManageApp {
    public static void main(String[] args) {
        SpringApplication.run(ManageApp.class, args);
    }
}
