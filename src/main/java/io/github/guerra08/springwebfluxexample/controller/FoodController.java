package io.github.guerra08.springwebfluxexample.controller;

import io.github.guerra08.springwebfluxexample.model.Food;
import io.github.guerra08.springwebfluxexample.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Validated
@RequestMapping(value = "/food")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public Mono<ResponseEntity<Food>> postCreateFood(@RequestBody @Valid Food f) {
        return foodService.saveFood(f).map(e ->
            ResponseEntity.created(URI.create("/food/" + e.getId()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .build()
        );
    }

    @GetMapping("")
    public Flux<Food> getFood() {
        return foodService.getAllFood();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Food>> getFoodById(@PathVariable Long id) {
        return foodService.getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
