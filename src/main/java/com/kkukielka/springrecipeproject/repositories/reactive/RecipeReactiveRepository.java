package com.kkukielka.springrecipeproject.repositories.reactive;

import com.kkukielka.springrecipeproject.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
