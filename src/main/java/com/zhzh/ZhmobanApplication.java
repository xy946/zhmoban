package com.zhzh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhzh.mapper")
public class ZhmobanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhmobanApplication.class, args);
    }

}
