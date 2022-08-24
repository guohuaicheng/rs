package com.qiqi.rs.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

//@ComponentScan(
//        basePackages = {"com.qiqi.core.autoconfigure", "com.qiqi.rs.admin"}
//)
@SpringBootApplication
@ServletComponentScan
@EnableCaching
public class RsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RsApplication.class, args);
    }

}
