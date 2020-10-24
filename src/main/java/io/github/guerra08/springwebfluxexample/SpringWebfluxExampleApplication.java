package io.github.guerra08.springwebfluxexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class SpringWebfluxExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxExampleApplication.class, args);
    }

}
