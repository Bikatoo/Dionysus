package com.bikatoo.dionysus.dionysus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.bikatoo.dionysus.dionysus.infrastructure.mapper")
public class DionysusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DionysusApplication.class, args);
    }

}
