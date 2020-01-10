package com.kkukielka.springrecipeproject.services;

import com.kkukielka.springrecipeproject.commands.RecipeCommand;
import com.kkukielka.springrecipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(String id);

    RecipeCommand findCommandById(String id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    void deleteById(String id);

}
