package com.qxf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName BootVueChatApp
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2021/1/7 0:21
 **/
@SpringBootApplication
@MapperScan("com.qxf.mapper")
public class BootVueChatApp {
    public static void main(String[] args) {
        SpringApplication.run(BootVueChatApp.class,args);
    }
}
