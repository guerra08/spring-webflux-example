package io.github.guerra08.springwebfluxexample.repository;

import io.github.guerra08.springwebfluxexample.model.Food;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface FoodRepository extends R2dbcRepository<Food, Long> {

}
