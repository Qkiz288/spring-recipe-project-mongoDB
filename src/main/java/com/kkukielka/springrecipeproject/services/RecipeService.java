package com.kkukielka.springrecipeproject.services;

import com.kkukielka.springrecipeproject.commands.RecipeCommand;
import com.kkukielka.springrecipeproject.domain.Recipe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeService {

    Flux<Recipe> getRecipes();

    Mono<Recipe> findById(String id);

    Mono<RecipeCommand> findCommandById(String id);

    Mono<RecipeCommand> saveRecipeCommand(RecipeCommand recipeCommand);

    void deleteById(String id);

}
