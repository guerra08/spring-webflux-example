package io.github.guerra08.springwebfluxexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class IndexController {

    @GetMapping("/")
    public Mono<String> getIndex(){
        return Mono.just("Welcome!");
    }

}
