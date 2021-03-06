package com.bupt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.bupt.mapper")
public class GoodCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodCommunityApplication.class, args);
    }

}
