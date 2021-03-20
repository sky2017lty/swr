package com.poshing.swr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

/**
 * @author LiTianyi
 */
@SpringBootApplication
@MapperScan("com.poshing.swr.dao")
public class SwrApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SwrApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SwrApplication.class, args);
    }

}
