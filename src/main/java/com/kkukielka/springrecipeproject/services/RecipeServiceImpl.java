package com.kkukielka.springrecipeproject.services;

import com.kkukielka.springrecipeproject.commands.RecipeCommand;
import com.kkukielka.springrecipeproject.converters.RecipeCommandToRecipe;
import com.kkukielka.springrecipeproject.converters.RecipeToRecipeCommand;
import com.kkukielka.springrecipeproject.domain.Recipe;
import com.kkukielka.springrecipeproject.repositories.reactive.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeReactiveRepository recipeReactiveRepository;
    private RecipeCommandToRecipe recipeCommandToRecipe;
    private RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeReactiveRepository recipeReactiveRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Flux<Recipe> getRecipes() {
        log.debug("I'm in the service");

        return recipeReactiveRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id) {
        return recipeReactiveRepository.findById(id);
    }

    @Override
    public Mono<RecipeCommand> findCommandById(String id) {

        return recipeReactiveRepository.findById(id)
                .map(recipe -> {
                    RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);

                    recipeCommand.getIngredients()
                            .forEach(ingredientCommand -> ingredientCommand.setRecipeId(recipeCommand.getId()));

                    return recipeCommand;
                });
    }

    @Override
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand recipeCommand) {
        return recipeReactiveRepository.save(recipeCommandToRecipe.convert(recipeCommand))
                .map(recipeToRecipeCommand::convert);
    }

    @Override
    public void deleteById(String id) {
        recipeReactiveRepository.deleteById(id).block();
    }
}
