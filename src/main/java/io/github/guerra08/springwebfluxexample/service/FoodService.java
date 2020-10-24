package io.github.guerra08.springwebfluxexample.service;

import io.github.guerra08.springwebfluxexample.model.Food;
import io.github.guerra08.springwebfluxexample.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Mono<Food> saveFood(Food f){
        return foodRepository.save(f);
    }

    public Flux<Food> getAllFood(){
        return foodRepository.findAll();
    }

    public Mono<Food> getById(Long id){
        return foodRepository.findById(id);
    }
}
