package io.github.guerra08.springwebfluxexample.controller;

import io.github.guerra08.springwebfluxexample.model.Food;
import io.github.guerra08.springwebfluxexample.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/food")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("")
    public Mono<Food> postCreateFood(@RequestBody Food f){
        return foodService.saveFood(f);
    }

    @GetMapping("")
    public Flux<Food> getFood(){
        return foodService.getAllFood();
    }
}
