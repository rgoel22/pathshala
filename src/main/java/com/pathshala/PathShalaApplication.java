package com.pathshala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
@EntityScan("com.pathshala.dao")
@ComponentScan("com.pathshala.config")
public class PathShalaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PathShalaApplication.class, args);
    }

}
