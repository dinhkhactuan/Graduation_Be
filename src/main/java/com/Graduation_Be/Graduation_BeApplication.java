package com.Graduation_Be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Graduation_BeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Graduation_BeApplication.class, args);
    }

}
